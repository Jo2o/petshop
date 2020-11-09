package sk.gw.jo2o.petshop.rest.orders;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    private long productId;

    private short count;

}
