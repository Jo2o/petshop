package sk.gw.jo2o.petshop.rest.order;

import static sk.gw.jo2o.petshop.auth.model.Role.ADMIN;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.service.AuthService;
import sk.gw.jo2o.petshop.rest.common.PageService;
import sk.gw.jo2o.petshop.rest.common.PriceMapper;
import sk.gw.jo2o.petshop.shopping.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
class OrderResourceV1 {

    private final PriceMapper priceMapper;
    private final PageService pageService;
    private final AuthService authService;
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @GetMapping("/admin-orders")
    public Page<OrderResponse> getOrdersForAdmin(
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize) {
        authService.checkRole(ADMIN);
        return orderMapper.toPageResponseList(
                orderService.getPagedOrders(pageService.createPageRequest(pageIndex, pageSize)));
    }
}
