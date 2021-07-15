package ru.infinitycarwash.msorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.infinitycarwash.msorder.entities.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByDateAndProductId(LocalDate currentDate, Long productId);

    Optional<Order> findOrderByDateAndTime(LocalDate currentDate, LocalTime localTime);

    List<Order> findAllByUserId(Long id);

}
