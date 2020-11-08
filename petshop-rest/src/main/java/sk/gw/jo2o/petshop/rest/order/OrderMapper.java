package sk.gw.jo2o.petshop.rest.order;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.Product;
import sk.gw.jo2o.petshop.rest.common.PriceMapper;
import sk.gw.jo2o.petshop.rest.product.*;

@Service
@RequiredArgsConstructor
class OrderMapper {

    private final PriceMapper priceMapper;

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories())
                .price(priceMapper.validateAndMapToString(product.getPrice()))
                .description(product.getDescription())
                .gallery(product.getImageUrls())
                .build();
    }

    public Product toEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .categories(productRequest.getCategories())
                .price(priceMapper.validateAndMapToInt(productRequest.getPrice()))
                .description(productRequest.getDescription())
                .imageUrls(productRequest.getGallery())
                .build();
    }

    public List<ProductPublicResponse> toResponseList(List<Product> products) {
        return products.stream()
                .map(this::toPublicResponse)
                .collect(toList());
    }

    private ProductPublicResponse toPublicResponse(Product product) {
        return ProductPublicResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories())
                .price(priceMapper.validateAndMapToString(product.getPrice()))
                .build();
    }

}