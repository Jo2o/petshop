package sk.gw.jo2o.petshop.shopping.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import sk.gw.jo2o.petshop.entity.*;
import sk.gw.jo2o.petshop.repo.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private static final int PRICE_1 = 402;
    private static final int PRICE_2 = 6856;
    private static final short COUNT_1 = 10;
    private static final short COUNT_2 = 2;
    private static final String USERNAME = "username";
    private static final long SAVED_ORDER_ID = 1111;

    @Mock
    private UserService userService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderedItemService orderedItemService;

    @Captor
    private ArgumentCaptor<Order> orderCaptor;

    @Captor
    private ArgumentCaptor<OrderedItem> orderedItemCaptor;

    @InjectMocks
    private OrderService orderService;

    @Test
    void save() {
        // given
        User user = createUser();
        Order order = createOrder();
        List<OrderedItem> orderedItems = createOrderedItems();
        when(userService.find(anyLong())).thenReturn(user);
        when(orderRepository.save(any())).thenReturn(order);

        // when
        orderService.save(orderedItems, 5);

        // then
        verify(orderRepository).save(orderCaptor.capture());
        assertThat(orderCaptor.getValue())
                .isEqualToIgnoringGivenFields(
                        Order.builder()
                                .user(user)
                                .price(PRICE_1 * COUNT_1 + PRICE_2 * COUNT_2)
                                .build(),
                        "id", "created");

        verify(orderedItemService, times(2)).save(orderedItemCaptor.capture());
        List<OrderedItem> savedOrderedItems = orderedItemCaptor.getAllValues();
        assertThat(savedOrderedItems)
                .hasSize(2)
                .containsExactly(
                        OrderedItem.builder()
                                .count(COUNT_1)
                                .price(PRICE_1)
                                .order(order)
                                .build(),
                        OrderedItem.builder()
                                .count(COUNT_2)
                                .price(PRICE_2)
                                .order(order)
                                .build());
    }

    private List<OrderedItem> createOrderedItems() {
        return List.of(
                OrderedItem.builder()
                        .price(PRICE_1)
                        .count(COUNT_1)
                        .build(),
                OrderedItem.builder()
                        .price(PRICE_2)
                        .count(COUNT_2)
                        .build());
    }

    private User createUser() {
        return User.builder()
                .username(USERNAME)
                .build();
    }

    private Order createOrder() {
        return Order.builder()
                .id(SAVED_ORDER_ID)
                .build();
    }

}
