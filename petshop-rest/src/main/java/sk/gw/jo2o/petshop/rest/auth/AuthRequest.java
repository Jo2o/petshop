package sk.gw.jo2o.petshop.rest.auth;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    private String username;

    private String email;

    private String password;

}
