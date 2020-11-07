package sk.gw.jo2o.petshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.gw.jo2o.petshop.entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long> {


}
