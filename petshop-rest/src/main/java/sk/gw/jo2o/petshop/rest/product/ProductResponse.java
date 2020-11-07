package sk.gw.jo2o.petshop.rest.product;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    long id;

    String name;

    String animalCategory;

    String price;

}
