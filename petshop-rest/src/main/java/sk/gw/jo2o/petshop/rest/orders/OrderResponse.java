package sk.gw.jo2o.petshop.rest.orders;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private long id;

    private String totalPrice;

    private LocalDateTime created;

    private List<OrderItemResponse> orderedItems;

}
