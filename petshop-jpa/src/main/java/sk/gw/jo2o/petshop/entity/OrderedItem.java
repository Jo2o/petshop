package sk.gw.jo2o.petshop.entity;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderedItem {

    @Id
    private long id;

    private short count;

    private int price;

    private long productId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
