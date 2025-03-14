package ru.yandex.practicum.intershop.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ItemDto {
    private final Integer id;
    private final String title;
    private final BigDecimal price;
    private final String description;
    private final String imgPath;
}
