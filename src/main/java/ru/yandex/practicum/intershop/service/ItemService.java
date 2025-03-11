package ru.yandex.practicum.intershop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.yandex.practicum.intershop.entity.Item;
import ru.yandex.practicum.intershop.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void getBySearchPageable(String search, String sortRaw, Integer pageSize) {
        Sort sort = switch (sortRaw.toUpperCase()) {
            case "ALFA" -> Sort.by("title");
            case "PRICE" -> Sort.by("price");
            default -> Sort.unsorted();
        };
        Pageable pageable = PageRequest.of(pageSize, pageSize, sort);
        Page<Item> items;
        if (StringUtils.hasLength(search)) {
            items = itemRepository.findByTitleContainsIgnoreCase(search, pageable);
        } else {
            items = itemRepository.findAll(pageable);
        }
    }
}

