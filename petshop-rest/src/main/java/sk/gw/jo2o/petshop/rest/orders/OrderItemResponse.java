package sk.gw.jo2o.petshop.rest.orders;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {

    private long productId;

    private String price;

    private short count;

}
