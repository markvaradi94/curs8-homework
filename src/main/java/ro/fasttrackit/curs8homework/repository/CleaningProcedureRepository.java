package ro.fasttrackit.curs8homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs8homework.model.entity.CleaningProcedure;

import java.util.List;

public interface CleaningProcedureRepository extends JpaRepository<CleaningProcedure, Long> {
    List<CleaningProcedure> findByCleanupLog_Id(Long cleanupLogId);
}
