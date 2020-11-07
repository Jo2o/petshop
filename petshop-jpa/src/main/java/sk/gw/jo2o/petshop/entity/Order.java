package sk.gw.jo2o.petshop.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ORDER")
public class Order {

    @Id
    private long id;

    private int totalPrice;

    private LocalDateTime created;

}
