package sk.gw.jo2o.petshop.entity;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    private long id;

    private int count;

    private int price;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Order order;

}
