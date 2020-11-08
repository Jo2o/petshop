package sk.gw.jo2o.petshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.gw.jo2o.petshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
