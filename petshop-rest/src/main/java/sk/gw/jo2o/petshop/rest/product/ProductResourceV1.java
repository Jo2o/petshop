package sk.gw.jo2o.petshop.rest.product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.shopping.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
class ProductResourceV1 {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public List<ProductPublicResponse> getPublicProducts() {
        return productMapper.toResponseList(productService.findAll());
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") long id) {
        return productMapper.toResponse(productService.find(id));
    }

    @PostMapping("/product")
    public ResponseEntity createProduct(@RequestBody ProductRequest productRequest) {
        productService.save(productMapper.toEntity(productRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
