package ru.yandex.practicum.intershop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.practicum.intershop.dto.OrderDto;
import ru.yandex.practicum.intershop.dto.OrderItemDto;
import ru.yandex.practicum.intershop.dto.OrderResponseDto;
import ru.yandex.practicum.intershop.entity.Order;
import ru.yandex.practicum.intershop.entity.OrderItem;

@Mapper(uses = {ItemMapper.class})
public interface OrderMapper {

    OrderDto toDto(Order source);

    @Mapping(target = "items", source = "orderItems")
    OrderResponseDto toResponseDto(Order source);

    @Mapping(target = "itemId", source = "item.id")
    OrderItemDto toDto(OrderItem source);
}
