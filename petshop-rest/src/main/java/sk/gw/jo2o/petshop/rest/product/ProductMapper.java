package sk.gw.jo2o.petshop.rest.product;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.hasText;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.Product;
import sk.gw.jo2o.petshop.exception.PetShopNotValidException;
import sk.gw.jo2o.petshop.rest.common.PriceMapper;
import sk.gw.jo2o.petshop.shopping.enums.AnimalCategory;

@Service
@RequiredArgsConstructor
class ProductMapper {

    private final PriceMapper priceMapper;

    public Page<ProductAdminResponse> toAdminPageResponseList(Page<Product> productsPage) {
        List<ProductAdminResponse> productAdminResponses = productsPage.getContent().stream()
                .map(this::toAdminResponse)
                .collect(toList());

        return new PageImpl<>(productAdminResponses, productsPage.getPageable(), productsPage.getTotalPages());
    }

    public Page<ProductPublicResponse> toPublicPagedResponse(Page<Product> productsPage) {
        List<ProductPublicResponse> productPublicResponses = productsPage.getContent().stream()
                .map(this::toPublicResponse)
                .collect(toList());

        return new PageImpl<>(productPublicResponses, productsPage.getPageable(), productsPage.getTotalPages());
    }

    public ProductAdminResponse toAdminResponse(Product product) {
        return ProductAdminResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories())
                .price(priceMapper.validateAndMapToString(product.getPrice()))
                .description(product.getDescription())
                .gallery(product.getImageUrls())
                .build();
    }

    private ProductPublicResponse toPublicResponse(Product product) {
        return ProductPublicResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories())
                .price(priceMapper.validateAndMapToString(product.getPrice()))
                .build();
    }

    public Product toEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .categories(mapCategories(productRequest.getCategories()))
                .price(priceMapper.validateAndMapToInt(productRequest.getPrice()))
                .description(productRequest.getDescription())
                .imageUrls(productRequest.getGallery())
                .build();
    }

    private String mapCategories(String categories) {
        if (!hasText(categories)) {
            return "";
        }
        String trimmedCategories = categories.replace(" ", "");
        String[] requestCategoriesArray = trimmedCategories.split("[,|;]");

        String commaConcatenatedCategories = Arrays.stream(AnimalCategory.values())
                .map(AnimalCategory::name)
                .collect(joining(","));

        StringBuilder stringBuilder = new StringBuilder();

        for (String requestCategory : requestCategoriesArray) {
            if (("," + commaConcatenatedCategories + ",").contains("," + requestCategory.toUpperCase() + ",")) {
                stringBuilder.append(requestCategory.toUpperCase());
                stringBuilder.append(",");
            } else {
                throw new PetShopNotValidException("Invalid category: " + requestCategory);
            }
        }

        return stringBuilder.toString().replaceAll(",$","");
    }

}
