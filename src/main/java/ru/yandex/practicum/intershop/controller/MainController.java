package ru.yandex.practicum.intershop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping
    public String users(Model model) {
        return "main";
    }
}
