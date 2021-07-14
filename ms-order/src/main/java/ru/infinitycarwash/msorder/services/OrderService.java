package ru.infinitycarwash.msorder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.infinitycarwash.msorder.entities.Order;
import ru.infinitycarwash.msorder.repositories.OrderRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    LocalTime workDayStart = LocalTime.of(9,00,00);
    LocalTime workDayEnd = LocalTime.of(21,00,00);



    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

//    public void createOrder(String userName,String phoneNumber, String productName, int month, int day, String time) {
//        String[] hourAndMinute = time.split(":",2);
//        int hour = Integer.parseInt(hourAndMinute[0]);
//        int minute = Integer.parseInt(hourAndMinute[1]);
//        LocalDate serviceDate = LocalDate.of(LocalDate.now().getYear(),month,day);
//        LocalTime serviceTime = LocalTime.of(hour,minute);
//        orderRepository.save(new Order(userName, phoneNumber, productName, serviceDate, serviceTime));
//    }

//    public List<Order> getFreeTime(String productName, int month, int day) {
//        LocalDate serviceDate = LocalDate.of(LocalDate.now().getYear(),month,day);
//        return orderRepository.findAllByDate(serviceDate);
//    }

    public List<LocalTime> getFreeTime(String productName, int month,int day) {
        List<LocalTime> freeTime = new ArrayList<>();
        List<Order> listOrderCurrentDay = orderRepository.findAllByDateAndProductName(
                                            LocalDate.of(LocalDate.now().getYear(),month,day),
                                            productName);
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
            localTimeStart = localTimeStart.plusMinutes(30);
        }
        for(Order order: listOrderCurrentDay){
            freeTime.removeIf(time -> order.getTime().compareTo(time) == 0);
        }
        return freeTime;
    }

    public synchronized void createOrder(Order order) {
        Optional<Order> orderCheck = orderRepository.findOrderByDateAndTime(order.getDate(),order.getTime());
        if(orderCheck.isPresent()){
            throw new RuntimeException();
        }else{
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
}
