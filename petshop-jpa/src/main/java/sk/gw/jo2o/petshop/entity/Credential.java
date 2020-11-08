package sk.gw.jo2o.petshop.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Credential {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "credential_seq_gen")
    @SequenceGenerator(name = "credential_seq_gen", sequenceName = "credential_id_seq", allocationSize = 1)
    private long id;

    private String password;

    private String roles;

}
