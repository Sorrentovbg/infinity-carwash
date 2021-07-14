package ru.infinitycarwash.msorder.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.infinitycarwash.msorder.entities.Order;
import ru.infinitycarwash.msorder.services.OrderService;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/infinity/order")
@RequiredArgsConstructor
public class OrderController {


    @Autowired
    private OrderService orderService;

    @GetMapping("/getTime")
    public List<LocalTime> getFreeTimeTest(@RequestParam(name = "prod") String productName,
                                           @RequestParam(name = "month") int month,
                                           @RequestParam(name = "day") int day){
        return orderService.getFreeTime(productName,month,day);
    }

    @PutMapping("/createOrder")
    public void Order(@RequestBody Order order){
        orderService.createOrder(order);
    }

    @GetMapping("/getLeftTime")
    public String getLeftTime(@RequestParam(name = "id") Long id){
        return orderService.getLeftTime(id);
    }
}
