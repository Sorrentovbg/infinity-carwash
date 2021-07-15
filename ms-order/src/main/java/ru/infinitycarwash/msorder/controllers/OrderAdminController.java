package ru.infinitycarwash.msorder.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.infinitycarwash.corelib.entities.dto.OrderView;
import ru.infinitycarwash.msorder.services.OrderService;

@RestController
@RequestMapping("/infinity/order/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Api(value = "/OrderAdmin", tags = {"Получение всех заказов(Для администратора)"})
public class OrderAdminController {

    @Autowired
    private OrderService orderService;

    @GetMapping("getAllOrder")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(
            value = "Получение все заказов(Сортировку сделать не успел)",
            httpMethod = "GET",
            response = Page.class
    )
    public Page<OrderView> getAllProduct(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                         @RequestParam(name = "sort", required = false) String sort,
                                         @RequestParam(name = "day", required = false) Integer day
                                         ){
        int size = 5;
        if(page < 1){
            page = 1;
        }
        return orderService.getAllOrders(page, sort, size, day);
    }
}
