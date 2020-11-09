package sk.gw.jo2o.petshop.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.gw.jo2o.petshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_Id(long userId);

}
