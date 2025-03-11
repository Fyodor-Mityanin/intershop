package ru.yandex.practicum.intershop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.intershop.service.ItemService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @RequestMapping
    public String index(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Integer pageSize,
            Model model
    ) {
        itemService.getBySearchPageable(search, sort, pageSize);
        model.addAttribute("search", search);
        return "main";
    }
}
