package sk.gw.jo2o.petshop.rest.orders;

import java.util.List;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private List<OrderItemRequest> orderedItemRequests;

}
