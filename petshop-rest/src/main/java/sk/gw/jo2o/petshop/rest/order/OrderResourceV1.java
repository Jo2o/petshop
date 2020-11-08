package sk.gw.jo2o.petshop.rest.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.rest.product.ProductResponse;
import sk.gw.jo2o.petshop.shopping.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
class OrderResourceV1 {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @GetMapping
    public List<ProductResponse> getProducts() {
        return new ArrayList<>();
    }
}
