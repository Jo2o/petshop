package sk.gw.jo2o.petshop.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.gw.jo2o.petshop.entity.OrderedItem;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {

    List<OrderedItem> findByOrder_Id(long orderId);

}
