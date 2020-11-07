package sk.gw.jo2o.petshop.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("sk.gw.jo2o.petshop.repo")
@EntityScan("sk.gw.jo2o.petshop.entity")
public class JpaConfig {

}
