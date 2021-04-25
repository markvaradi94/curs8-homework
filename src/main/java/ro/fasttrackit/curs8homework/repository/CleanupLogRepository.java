package ro.fasttrackit.curs8homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs8homework.model.entity.CleanupLog;

import java.util.Optional;

public interface CleanupLogRepository extends JpaRepository<CleanupLog, Long> {
    Optional<CleanupLog> findCleanupLogByHotelRoom_Id(Long roomId);
}
