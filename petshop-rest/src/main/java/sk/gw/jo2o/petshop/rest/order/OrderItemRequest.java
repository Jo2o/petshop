package sk.gw.jo2o.petshop.rest.order;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    private long productId;

    private short count;

    private int price;

}
