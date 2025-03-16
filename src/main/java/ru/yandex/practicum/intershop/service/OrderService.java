package ru.yandex.practicum.intershop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.intershop.dto.OrderDto;
import ru.yandex.practicum.intershop.dto.OrderItemDto;
import ru.yandex.practicum.intershop.mapper.OrderMapper;
import ru.yandex.practicum.intershop.repository.OrderRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

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
