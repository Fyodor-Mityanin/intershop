package ru.yandex.practicum.intershop.mapper;

import org.mapstruct.Mapper;
import ru.yandex.practicum.intershop.dto.ItemDto;
import ru.yandex.practicum.intershop.entity.Item;

@Mapper
public interface ItemMapper {

    ItemDto toDto(Item source);
}
