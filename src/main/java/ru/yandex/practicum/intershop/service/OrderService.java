package ru.yandex.practicum.intershop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.intershop.dto.OrderDto;
import ru.yandex.practicum.intershop.dto.OrderItemDto;
import ru.yandex.practicum.intershop.dto.OrderStatus;
import ru.yandex.practicum.intershop.entity.Item;
import ru.yandex.practicum.intershop.entity.Order;
import ru.yandex.practicum.intershop.entity.OrderItem;
import ru.yandex.practicum.intershop.mapper.OrderMapper;
import ru.yandex.practicum.intershop.repository.ItemRepository;
import ru.yandex.practicum.intershop.repository.OrderRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final static String ANONYMOUS_CUSTOMER = "anonymousCustomer";

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public void addToOrder(int itemId, String action, String sessionId) {
        Item item = itemRepository.getReferenceById(itemId);
        Order order = getOrCreateBySession(sessionId);
        OrderItem orderItem = order.getOrderItems().stream()
                .filter(i -> i.getItem().getId().equals(item.getId()))
                .findFirst()
                .orElse(new OrderItem(item, order, 0));
        switch (action) {
            case "plus" -> orderItem.setQuantity(orderItem.getQuantity() + 1);
            case "minus" -> orderItem.setQuantity(orderItem.getQuantity() == 0 ? 0 : orderItem.getQuantity() - 1);
        }
        order.getOrderItems().add(orderItem);
        orderRepository.save(order);
    }

    public Order getOrCreateBySession(String session) {
        Optional<Order> order = orderRepository.findBySession(session);
        if (order.isPresent()) {
            return order.get();
        }
        Order newOrder = new Order();
        newOrder.setSession(session);
        newOrder.setCustomer(ANONYMOUS_CUSTOMER);
        newOrder.setStatus(OrderStatus.NEW.name());
        return orderRepository.save(newOrder);
    }

    public Map<Integer, Integer> findOrderItemsMapBySession(String session) {
        return orderRepository.findBySession(session)
                .map(orderMapper::toDto)
                .map(OrderDto::getOrderItems)
                .map(this::getOrderItemMap)
                .orElse(null);
    }

    private Map<Integer, Integer> getOrderItemMap(List<OrderItemDto> orderItemDto) {
        return orderItemDto.stream()
                .collect(
                        Collectors.toMap(
                                OrderItemDto::getItemId,
                                OrderItemDto::getQuantity
                        )
                );
    }
}
