package sk.gw.jo2o.petshop.rest.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.rest.product.ProductResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
class AuthResource {

    @GetMapping
    public List<ProductResponse> getProducts() {
        return new ArrayList<>();
    }
}
