package sk.gw.jo2o.petshop.rest.order;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.Order;
import sk.gw.jo2o.petshop.entity.Product;
import sk.gw.jo2o.petshop.rest.common.PriceMapper;
import sk.gw.jo2o.petshop.rest.product.*;

@Service
@RequiredArgsConstructor
class OrderMapper {

    private final PriceMapper priceMapper;

    public OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .created(order.getCreated())
                .totalPrice(priceMapper.validateAndMapToString(order.getPrice()))
//                .orderedItems(order)
                .build();
    }

    public Product toEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .categories(productRequest.getCategories())
                .price(priceMapper.validateAndMapToInt(productRequest.getPrice()))
                .description(productRequest.getDescription())
                .imageUrls(productRequest.getGallery())
                .build();
    }

    public Page<OrderResponse> toPageResponseList(Page<Order> ordersPage) {
        List<OrderResponse> orderResponses = ordersPage.getContent().stream()
                .map(this::toResponse)
                .collect(toList());

        return new PageImpl<>(orderResponses, ordersPage.getPageable(), ordersPage.getTotalPages());
    }

}
