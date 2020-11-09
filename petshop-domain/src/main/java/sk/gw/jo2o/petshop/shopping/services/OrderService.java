package sk.gw.jo2o.petshop.shopping.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.Order;
import sk.gw.jo2o.petshop.repo.OrderRepository;
import sk.gw.jo2o.petshop.repo.OrderedItemRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderedItemRepository orderedItemRepository;

    public Page<Order> getPagedOrders(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getUserOrders(long userId) {
        return orderRepository.findByUser_Id(userId);
    }

}
