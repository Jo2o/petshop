package sk.gw.jo2o.petshop.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.gw.jo2o.petshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User findByEmail(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
