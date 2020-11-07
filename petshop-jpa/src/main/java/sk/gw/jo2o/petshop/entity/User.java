package sk.gw.jo2o.petshop.entity;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    private long id;

    private String username;

    private String email;

}
