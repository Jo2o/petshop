package sk.gw.jo2o.petshop.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_id_seq", allocationSize = 1)
    private long id;

    private String username;

    private String email;

    @OneToOne
    @JoinColumn(name = "credential_id")
    private Credential credential;

}
