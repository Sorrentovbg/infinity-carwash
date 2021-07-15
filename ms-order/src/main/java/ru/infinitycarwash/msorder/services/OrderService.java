package ru.infinitycarwash.msorder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.infinitycarwash.corelib.entities.dto.OrderDto;


import ru.infinitycarwash.corelib.entities.dto.OrderView;
import ru.infinitycarwash.corelib.entities.dto.ProductDto;
import ru.infinitycarwash.eurekafeign.product.ProductFeign;
import ru.infinitycarwash.msorder.entities.Order;
import ru.infinitycarwash.msorder.entities.Status;
import ru.infinitycarwash.msorder.repositories.OrderRepository;
import ru.infinitycarwash.corelib.entities.UserInfo;
import ru.infinitycarwash.msorder.repositories.StatusRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    LocalTime workDayStart = LocalTime.of(9,00,00);
    LocalTime workDayEnd = LocalTime.of(21,00,00);


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductFeign productClientFeign;

    @Autowired
    private StatusRepository statusRepository;


    public List<LocalTime> getFreeTime(Long productId, int month, int day) {
        List<LocalTime> freeTime = new ArrayList<>();
        ProductDto productDto = productClientFeign.getProductDto(productId);
        List<Order> listOrderCurrentDay = orderRepository.findAllByDateAndProductId(
                                            LocalDate.of(LocalDate.now().getYear(),month,day),
                                            productId);
        LocalTime localTimeStart = workDayStart;

        while((localTimeStart.compareTo(workDayEnd)) != 0){
            if(LocalDate.of(LocalDate.now().getYear(),month,day).isAfter(LocalDate.now())){
                freeTime.add(localTimeStart);
            }else if(LocalDate.of(LocalDate.now().getYear(),month,day).isBefore(LocalDate.now())){
                throw  new RuntimeException("Wrong date");
            }else{
                if(localTimeStart.isAfter(LocalTime.now())){
                    freeTime.add(localTimeStart);
                }
            }
            localTimeStart = localTimeStart.plusMinutes(productDto.getJobDuration());
        }
        for(Order order: listOrderCurrentDay){
            freeTime.removeIf(time -> order.getTime().compareTo(time) == 0);
        }
        return freeTime;
    }

    @Transactional
    public synchronized void createOrder(UserInfo userInfo, OrderDto orderDto) {
        Optional<Order> orderCheck = orderRepository.findOrderByDateAndTime(orderDto.getDate(),orderDto.getTime());
        if(orderCheck.isPresent()){
            throw new RuntimeException();
        }else{
            Order order = new Order(userInfo.getUserId(),
                    orderDto.getProductId(),
                    orderDto.getCarNumber(),
                    orderDto.getDate(),
                    orderDto.getTime());
            orderRepository.save(order);
        }
    }

    public String getLeftTime(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        LocalDateTime localDateTime = LocalDateTime.of(order.get().getDate(),order.get().getTime());
        Duration leftTime = Duration.between(LocalDateTime.now(), localDateTime);
        return String.format("%02d часов %02d минут",
                leftTime.toHours(),
                leftTime.toMinutesPart());
    }

    public List<OrderView> getOrders(UserInfo userInfo) {
        List<OrderView> orders = orderRepository.findAllByUserId(userInfo.getUserId())
                .stream().map(this::toOrderView)
                .collect(Collectors.toList());
        orders.removeIf(orderView -> orderView.getDate().isBefore(LocalDate.now()));
        return orders;
    }

    public OrderView toOrderView(Order order){
        ProductDto productDto = productClientFeign.getProductDto(order.getId());


        return new OrderView(order.getId(),
                productDto.getProductName(),
                order.getCarNumber(),
                order.getDate(),
                order.getTime());
    }

    @Transactional
    public void deleteOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.delete(order.get());
        }else{
            throw new RuntimeException("Order not exists");
        }
    }

    public Page<OrderView> getAllOrders(Integer page, String sort, int size, Integer day) {
        Page<OrderView> orders = orderRepository.findAll(PageRequest.of(page -1, size,
                     Sort.by(Sort.Direction.DESC, "data"))).map(this::toOrderView);
        return orders;
    }
}
