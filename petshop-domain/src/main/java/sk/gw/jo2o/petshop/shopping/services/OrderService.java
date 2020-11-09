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

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;
    private final OrderedItemService orderedItemService;

    public Page<Order> getPagedOrders(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void save(List<OrderedItem> orderedItems, long userId) {
        Order order = Order.builder()
                .created(LocalDateTime.now())
                .price(orderedItems.stream().mapToInt(OrderedItem::getPrice).sum())
                .user(userService.find(userId))
                .build();

        for (OrderedItem orderedItem : orderedItems) {
            orderedItem.setOrder(order);
            orderedItemService.save(orderedItem);
        }

       save(order);
    }

    public List<Order> getUserOrders(long userId) {
        return orderRepository.findByUser_Id(userId);
    }

}
