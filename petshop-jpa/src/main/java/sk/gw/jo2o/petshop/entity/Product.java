package sk.gw.jo2o.petshop.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "product_seq_gen")
    @SequenceGenerator(name = "product_seq_gen", sequenceName = "product_id_seq", allocationSize = 1)
    private long id;

    private String name;

    private String categories;

    private int price;

    private String description;

    private String imageUrls;

}
