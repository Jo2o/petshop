package sk.gw.jo2o.petshop.rest.products;

import static sk.gw.jo2o.petshop.auth.model.Role.ADMIN;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.service.AuthService;
import sk.gw.jo2o.petshop.rest.PageService;
import sk.gw.jo2o.petshop.rest.common.PriceMapper;
import sk.gw.jo2o.petshop.shopping.services.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
class ProductResourceV1 {

    private final PriceMapper priceMapper;
    private final PageService pageService;
    private final AuthService authService;
    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping("/public-products")
    public Page<ProductShortResponse> getProductsForPublic(
            @RequestParam(required = false) String priceFrom,
            @RequestParam(required = false) String priceTo,
            @RequestParam(required = false) String nameStartsWith,
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize) {
        return productMapper.toPublicPageResponse(
                productService.getPagedProducts(nameStartsWith,
                        priceMapper.validateAndMapToInt(priceFrom),
                        priceMapper.validateAndMapToInt(priceTo),
                        pageService.createPageRequest(pageIndex, pageSize)));
    }

    @GetMapping("/admin-products")
    public Page<ProductResponse> getProductsForAdmin(
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize) {
        authService.checkRole(ADMIN);
        return productMapper.toAdminPageResponse(
                productService.getPagedProducts(pageService.createPageRequest(pageIndex, pageSize)));
    }

    @GetMapping("/public-products/{id}")
    public ProductResponse getProductForPublic(@PathVariable("id") long id) {
        return productMapper.toAdminResponse(productService.find(id));
    }

    @PostMapping("/admin-products/product")
    public ResponseEntity createProduct(@RequestBody ProductRequest productRequest) {
        authService.checkRole(ADMIN);
        productService.save(productMapper.toEntity(productRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
