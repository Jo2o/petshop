package sk.gw.jo2o.petshop.shopping.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.Order;
import sk.gw.jo2o.petshop.entity.OrderedItem;
import sk.gw.jo2o.petshop.repo.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderedItemService orderedItemService;

    public Page<Order> getPagedOrders(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void save(List<OrderedItem> orderedItems, long userId) {
        Order order = save(Order.builder()
                .created(LocalDateTime.now().withNano(0))
                .price(orderedItems.stream().mapToInt(item -> item.getPrice() * item.getCount()).sum())
                .user(userService.find(userId))
                .build());

        for (OrderedItem orderedItem : orderedItems) {
            orderedItem.setOrder(order);
            orderedItemService.save(orderedItem);
        }
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(long userId) {
        List<Order> orders = orderRepository.findByUser_Id(userId);

        for (Order order : orders) {
            List<OrderedItem> items = orderedItemService.findByOrderId(order.getId());
        }

        return orders;
    }

}
