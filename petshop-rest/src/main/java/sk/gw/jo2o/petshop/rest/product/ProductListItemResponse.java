package sk.gw.jo2o.petshop.rest.product;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListItemResponse {

    long id;

    String name;

    String categories;

    String price;

}
