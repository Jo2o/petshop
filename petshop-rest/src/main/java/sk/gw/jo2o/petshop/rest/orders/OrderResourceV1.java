package sk.gw.jo2o.petshop.rest.orders;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.service.AuthService;

import sk.gw.jo2o.petshop.rest.PageService;
import sk.gw.jo2o.petshop.rest.common.PriceMapper;
import sk.gw.jo2o.petshop.shopping.services.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
class OrderResourceV1 {

    private final PriceMapper priceMapper;
    private final PageService pageService;
    private final AuthService authService;
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @GetMapping("/user-orders")
    public List<OrderResponse> getOrdersForUser(@RequestParam long userId) {
//        authService.checkRole(ADMIN);
        return orderMapper.toListResponse(orderService.getUserOrders(userId));
    }

    @PostMapping("/user-orders/create-order")
    public ResponseEntity createOrder(@RequestParam long userId, @RequestBody OrderRequest orderRequest) {
//        authService.checkRole(ADMIN);
        orderService.save(orderMapper.toEntity(orderRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/admin-orders")
    public Page<OrderResponse> getOrdersForAdmin(
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize) {
//        authService.checkRole(ADMIN);
        return orderMapper.toPageResponse(
                orderService.getPagedOrders(pageService.createPageRequest(pageIndex, pageSize)));
    }

}
