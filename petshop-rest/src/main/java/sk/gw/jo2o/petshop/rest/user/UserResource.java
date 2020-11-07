package sk.gw.jo2o.petshop.rest.user;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserResource {

    @GetMapping
    public void getProducts() {
//        return productMapper.toResponsePage(productService.getProducts());
    }
}
