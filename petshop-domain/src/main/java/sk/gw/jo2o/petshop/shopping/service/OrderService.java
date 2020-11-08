package sk.gw.jo2o.petshop.shopping.service;

import java.util.List;

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


    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

}
