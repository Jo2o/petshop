package sk.gw.jo2o.petshop.rest.order;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import sk.gw.jo2o.petshop.entity.OrderedItem;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private long id;

    private String totalPrice;

    private LocalDateTime created;

    private List<OrderedItem> orderedItems;

}
