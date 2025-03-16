package ru.yandex.practicum.intershop.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.intershop.dto.ItemResponseDto;
import ru.yandex.practicum.intershop.service.ItemService;

@Controller
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class MainController {

    private final ItemService itemService;

    @GetMapping
    public String index(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "NO") String sort,
            @RequestParam(defaultValue = "10") Integer pageSize,
            HttpSession session,
            Model model
    ) {
        Page<ItemResponseDto> itemDtos = itemService.getBySearchPageable(search, sort, pageSize, session.getId());
        model.addAttribute("sort", sort);
        model.addAttribute("items", itemDtos);
        return "main";
    }

    @PostMapping("/main/items/{itemId}")
    public void addToCart(@PathVariable int itemId) {
        log.info("Add to cart {}", itemId);
    }

    @GetMapping("/items/{itemId}")
    public String getItem(@PathVariable int itemId, Model model, HttpSession session) {
        ItemResponseDto itemResponseDto = itemService.getById(itemId, session.getId());
        model.addAttribute("item", itemResponseDto);
        return "item";
    }
}
