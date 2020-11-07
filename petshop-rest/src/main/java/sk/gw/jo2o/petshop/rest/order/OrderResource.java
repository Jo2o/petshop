package sk.gw.jo2o.petshop.rest.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.rest.product.ProductResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
class OrderResource {

    @GetMapping
    public List<ProductResponse> getProducts() {
        return new ArrayList<>();
    }
}
