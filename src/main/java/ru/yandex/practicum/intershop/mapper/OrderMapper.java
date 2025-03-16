package ru.yandex.practicum.intershop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.practicum.intershop.dto.OrderDto;
import ru.yandex.practicum.intershop.dto.OrderItemDto;
import ru.yandex.practicum.intershop.entity.Order;
import ru.yandex.practicum.intershop.entity.OrderItem;

@Mapper
public interface OrderMapper {

    OrderDto toDto(Order source);

    @Mapping(target = "itemId", source = "item.id")
    OrderItemDto toDto(OrderItem source);
}
