package sk.gw.jo2o.petshop.rest.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.rest.common.PageService;
import sk.gw.jo2o.petshop.rest.product.ProductResponse;
import sk.gw.jo2o.petshop.shopping.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
class OrderResourceV1 {

    private final PageService pageService;
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @GetMapping("/admin-orders")
    public Page<OrderResponse> getPagedOrdersForAdmin(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return Page.empty();
//                orderMapper.toResponse(orderService.getPagedOrders(pageService.createPageRequest(pageIndex, pageSize)));
    }
}
