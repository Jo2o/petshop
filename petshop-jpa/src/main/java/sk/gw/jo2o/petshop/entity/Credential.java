package sk.gw.jo2o.petshop.entity;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Credential {

    @Id
    private long id;

    private String password;

    private String roles;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
