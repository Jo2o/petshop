package sk.gw.jo2o.petshop.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ordereditem_seq_gen")
    @SequenceGenerator(name = "ordereditem_seq_gen", sequenceName = "ordered_item_id_seq", allocationSize = 1)
    private long id;

    private short count;

    private int price;

    private long productId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
