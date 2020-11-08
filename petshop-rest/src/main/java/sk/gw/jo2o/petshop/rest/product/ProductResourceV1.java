package sk.gw.jo2o.petshop.rest.product;

import static sk.gw.jo2o.petshop.auth.model.Role.ADMIN;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.service.AuthService;
import sk.gw.jo2o.petshop.shopping.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
class ProductResourceV1 {

    private final AuthService authService;
    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductListItemResponse> getPublicProducts(@RequestParam String priceFrom, @RequestParam String priceTo, @RequestParam String nameStartsWith) {
        return productMapper.toPublicResponseList(productService.find(priceFrom, priceTo, nameStartsWith));
    }

    @GetMapping("/admin-products")
    public List<ProductResponse> getAdminProducts(@RequestParam String priceFrom, @RequestParam String priceTo, @RequestParam String nameStartsWith) {
        authService.checkRole(ADMIN);
        return productMapper.toAdminResponseList(productService.find(priceFrom, priceTo, nameStartsWith));
    }

    @GetMapping("/products/{id}")
    public ProductResponse getPublicProduct(@PathVariable("id") long id) {
        return productMapper.toResponse(productService.find(id));
    }

    @PostMapping("/products/product")
    public ResponseEntity createProduct(@RequestBody ProductRequest productRequest) {
        productService.save(productMapper.toEntity(productRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
