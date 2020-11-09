package sk.gw.jo2o.petshop.rest.product;

import static sk.gw.jo2o.petshop.auth.model.Role.ADMIN;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.service.AuthService;
import sk.gw.jo2o.petshop.rest.common.PageService;
import sk.gw.jo2o.petshop.rest.common.PriceMapper;
import sk.gw.jo2o.petshop.shopping.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
class ProductResourceV1 {

    private final PriceMapper priceMapper;
    private final PageService pageService;
    private final AuthService authService;
    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping("/products")
    public Page<ProductPublicResponse> getProductsForPublic(
            @RequestParam(required = false) String priceFrom,
            @RequestParam(required = false) String priceTo,
            @RequestParam(required = false) String nameStartsWith,
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize) {
        return productMapper.toPublicPagedResponse(
                productService.getPagedProducts(nameStartsWith,
                        priceMapper.validateAndMapToInt(priceFrom),
                        priceMapper.validateAndMapToInt(priceTo),
                        pageService.createPageRequest(pageIndex, pageSize)));
    }

    @GetMapping("/admin-products")
    public Page<ProductAdminResponse> getProductsForAdmin(
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize) {
        authService.checkRole(ADMIN);
        return productMapper.toAdminPageResponseList(
                productService.getPagedProducts(pageService.createPageRequest(pageIndex, pageSize)));
    }

    @GetMapping("/products/{id}")
    public ProductAdminResponse getPublicProduct(@PathVariable("id") long id) {
        return productMapper.toAdminResponse(productService.find(id));
    }

    @PostMapping("/products/product")
    public ResponseEntity createProduct(@RequestBody ProductRequest productRequest) {
        authService.checkRole(ADMIN);
        productService.save(productMapper.toEntity(productRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
