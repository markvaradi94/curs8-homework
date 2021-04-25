package ro.fasttrackit.curs8homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.curs8homework.model.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
