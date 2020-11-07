package sk.gw.jo2o.petshop.rest.product;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
class ProductResource {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productMapper.toResponsePage(productService.getProducts());
    }
}
