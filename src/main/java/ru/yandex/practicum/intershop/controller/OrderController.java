package ru.yandex.practicum.intershop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yandex.practicum.intershop.dto.OrderResponseDto;
import ru.yandex.practicum.intershop.service.OrderService;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable int orderId, Model model) {
        OrderResponseDto orderResponseDto = orderService.getById(orderId);
        BigDecimal total = orderResponseDto.getItems().stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("order", orderResponseDto);
        model.addAttribute("total", total);
        return "order";
    }
}
