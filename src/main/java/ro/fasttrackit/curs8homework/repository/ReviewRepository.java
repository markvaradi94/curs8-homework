package ro.fasttrackit.curs8homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs8homework.model.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
