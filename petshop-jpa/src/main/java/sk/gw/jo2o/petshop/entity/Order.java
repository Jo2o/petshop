package sk.gw.jo2o.petshop.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Order {

    @Id
    private long id;

    private int price;

    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
