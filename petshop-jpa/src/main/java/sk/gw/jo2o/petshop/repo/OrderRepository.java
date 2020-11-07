package sk.gw.jo2o.petshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.gw.jo2o.petshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
