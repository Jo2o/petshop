package sk.gw.jo2o.petshop.rest.products;

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
import sk.gw.jo2o.petshop.shopping.model.AnimalCategory;

@Service
@RequiredArgsConstructor
class ProductMapper {

    private final PriceMapper priceMapper;

    public Page<ProductResponse> toAdminPageResponse(Page<Product> productsPage) {
        List<ProductResponse> productResponse = productsPage.getContent().stream()
                .map(this::toAdminResponse)
                .collect(toList());

        return new PageImpl<>(productResponse, productsPage.getPageable(), productsPage.getTotalPages());
    }

    public Page<ProductShortResponse> toPublicPageResponse(Page<Product> productsPage) {
        List<ProductShortResponse> productShortResponse = productsPage.getContent().stream()
                .map(this::toPublicResponse)
                .collect(toList());

        return new PageImpl<>(productShortResponse, productsPage.getPageable(), productsPage.getTotalPages());
    }

    public ProductResponse toAdminResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories())
                .price(priceMapper.validateAndMapToString(product.getPrice()))
                .description(product.getDescription())
                .gallery(product.getImageUrls())
                .build();
    }

    private ProductShortResponse toPublicResponse(Product product) {
        return ProductShortResponse.builder()
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
