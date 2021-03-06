package sk.gw.jo2o.petshop.rest.products;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    String name;

    String categories;

    String price;

    String description;

    String gallery;

}
