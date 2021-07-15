package ru.infinitycarwash.msorder.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.infinitycarwash.corelib.entities.UserInfo;
import ru.infinitycarwash.corelib.entities.dto.OrderDto;
import ru.infinitycarwash.corelib.entities.dto.OrderView;
import ru.infinitycarwash.corelib.services.JWTTokenService;
import ru.infinitycarwash.msorder.services.OrderService;

import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/infinity/order/home")
@RequiredArgsConstructor
@Api(value = "/Order", tags = {"Работа с заказами"})
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private JWTTokenService jwtTokenService;

    @GetMapping("/getTime")
    @ApiOperation(
            value = "Получение свободного времени по ID продукта, Месяцу и Дню",
            httpMethod = "GET",
            response = List.class
    )
    public List<LocalTime> getFreeTimeTest(@RequestParam(name = "productId") Long productId,
                                           @RequestParam(name = "month") int month,
                                           @RequestParam(name = "day") int day){
        return orderService.getFreeTime(productId,month,day);
    }

    @PostMapping("/createOrder")
    @ApiOperation(
            value = "Создание заказа",
            httpMethod = "POST"
    )
    public void Order(@RequestHeader String authorization,
                      @RequestBody OrderDto orderDto){
        UserInfo userInfo = jwtTokenService.parseToken(authorization);
        orderService.createOrder(userInfo, orderDto);
    }

    @GetMapping("/getLeftTime")
    @ApiOperation(
            value = "Получение оставшегося времени",
            httpMethod = "GET",
            response = String.class
    )
    public String getLeftTime(@RequestParam(name = "id") Long id){
        return orderService.getLeftTime(id);
    }

    @GetMapping("/getOrders")
    @ApiOperation(
            value = "Получение списка заказов для клиента",
            httpMethod = "GET",
            response = String.class
    )
    public List<OrderView> getOrders(@RequestHeader String authorization){
        UserInfo userInfo = jwtTokenService.parseToken(authorization);
        return orderService.getOrders(userInfo);
    }

    @PostMapping("/delOrder")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Удаление заказа для клиента",
            httpMethod = "POST"
    )
    public void delOrder(Long id){
        orderService.deleteOrder(id);
    }
}
