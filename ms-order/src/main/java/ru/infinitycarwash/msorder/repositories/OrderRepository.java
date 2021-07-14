package ru.infinitycarwash.msorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.infinitycarwash.msorder.entities.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByDateAndProductName(LocalDate currentDate, String productName);

    Optional<Order> findOrderByDateAndTime(LocalDate currentDate, LocalTime localTime);

}
