package sk.gw.jo2o.petshop.rest.orders;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.Order;
import sk.gw.jo2o.petshop.rest.common.PriceMapper;
import sk.gw.jo2o.petshop.shopping.services.*;

@Service
@RequiredArgsConstructor
class OrderMapper {

    private final PriceMapper priceMapper;
    private final UserService userService;
    private final ProductService productService;
    private final OrderedItemService orderedItemService;

    public Page<OrderResponse> toPageResponse(Page<Order> ordersPage) {
        List<OrderResponse> orderResponses = ordersPage.getContent().stream()
                .map(this::toResponse)
                .collect(toList());

        return new PageImpl<>(orderResponses, ordersPage.getPageable(), ordersPage.getTotalPages());
    }

    public List<OrderResponse> toListResponse(List<Order> userOrders) {
        return userOrders.stream()
                .map(this::toResponse)
                .collect(toList());
    }

    public OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .created(order.getCreated())
                .totalPrice(priceMapper.validateAndMapToString(order.getPrice()))
//                .orderedItems(order)
                .build();
    }

//    public Order toEntity(OrderRequest orderRequest) {
//
//
//        Order order = Order.builder()
//                .created(LocalDateTime.now())
//
//                .build();
//
//        return Order.builder()
//                .created(LocalDateTime.now())
////                .name(productRequest.getName())
////                .categories(productRequest.getCategories())
////                .price(priceMapper.validateAndMapToInt(productRequest.getPrice()))
//                .build();
//    }

}
