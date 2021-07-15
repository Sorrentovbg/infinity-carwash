package ru.infinitycarwash.msorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.infinitycarwash.msorder.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
