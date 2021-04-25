package ro.fasttrackit.curs8homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs8homework.model.entity.Tourist;

public interface TouristRepository extends JpaRepository<Tourist, Long> {
}
