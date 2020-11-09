package sk.gw.jo2o.petshop.rest.products;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductShortResponse {

    long id;

    String name;

    String categories;

    String price;

}
