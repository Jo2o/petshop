package sk.gw.jo2o.petshop.rest.orders;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.OrderedItem;
import sk.gw.jo2o.petshop.shopping.services.ProductService;

@Service
@RequiredArgsConstructor
class OrderedItemMapper {

    private final ProductService productService;

    public List<OrderedItem> toEntityList(OrderRequest orderRequest) {
        return orderRequest.getOrderedItemRequests().stream()
                .map(this::toEntity)
                .collect(toList());
    }

    private OrderedItem toEntity(OrderItemRequest orderItemRequest) {
        return OrderedItem.builder()
                .productId(orderItemRequest.getProductId())
                .count(orderItemRequest.getCount())
                .price(productService.find(orderItemRequest.getProductId()).getPrice())
                .build();
    }

}
