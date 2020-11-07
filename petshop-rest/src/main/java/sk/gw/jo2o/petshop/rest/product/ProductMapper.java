package sk.gw.jo2o.petshop.rest.product;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.Product;

@Service
@RequiredArgsConstructor
class ProductMapper {

    private final PriceMapper priceMapper;

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories())
                .price(priceMapper.toStr(product.getPrice()))
                .description(product.getDescription())
                .gallery(product.getImageUrls())
                .build();
    }

    public ProductResponse toPublicResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .categories(product.getCategories())
                .price(priceMapper.toStr(product.getPrice()))
                .description(product.getDescription())
                .gallery(product.getImageUrls())
                .build();
    }

    public Product toEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .categories(productRequest.getCategories())
                .price(priceMapper.toInt(productRequest.getPrice()))
                .build();
    }

}
