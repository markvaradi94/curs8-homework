package ro.fasttrackit.curs8homework.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs8homework.model.entity.CleaningProcedure;
import ro.fasttrackit.curs8homework.model.entity.CleanupLog;
import ro.fasttrackit.curs8homework.model.entity.Room;
import ro.fasttrackit.curs8homework.model.entity.RoomFacilities;
import ro.fasttrackit.curs8homework.repository.CleaningProcedureRepository;
import ro.fasttrackit.curs8homework.repository.CleanupLogRepository;
import ro.fasttrackit.curs8homework.repository.RoomRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoomRepository roomRepository;
    private final CleanupLogRepository cleanupLogRepository;
    private final CleaningProcedureRepository cleaningProcedureRepository;


    @Override
    public void run(String... args) throws Exception {
        List<Room> rooms = roomRepository.saveAll(List.of(
                Room.builder()
                        .hotelName("Nevis")
                        .floor(2)
                        .number("205")
                        .facilities(
                                RoomFacilities.builder()
                                        .tv(true)
                                        .doubleBed(true)
                                        .build()
                        )
                        .build(),
                Room.builder()
                        .hotelName("Ramada")
                        .floor(5)
                        .number("510")
                        .facilities(
                                RoomFacilities.builder()
                                        .tv(true)
                                        .doubleBed(true)
                                        .build()
                        )
                        .build(),
                Room.builder()
                        .hotelName("Iris")
                        .floor(1)
                        .number("112")
                        .facilities(
                                RoomFacilities.builder()
                                        .tv(false)
                                        .doubleBed(true)
                                        .build()
                        )
                        .build(),
                Room.builder()
                        .hotelName("Continental")
                        .floor(7)
                        .number("705")
                        .facilities(
                                RoomFacilities.builder()
                                        .tv(true)
                                        .doubleBed(false)
                                        .build()
                        )
                        .build(),
                Room.builder()
                        .hotelName("Felix")
                        .floor(3)
                        .number("307")
                        .facilities(
                                RoomFacilities.builder()
                                        .tv(false)
                                        .doubleBed(false)
                                        .build()
                        )
                        .build()
        ));

        List<CleanupLog> logs = cleanupLogRepository.saveAll(List.of(
                new CleanupLog(rooms.get(0)),
                new CleanupLog(rooms.get(1)),
                new CleanupLog(rooms.get(2))
                )
        );

        cleaningProcedureRepository.saveAll(List.of(
                new CleaningProcedure("vacuuming", 2115, logs.get(0)),
                new CleaningProcedure("change sheets", 1739, logs.get(1)),
                new CleaningProcedure("clean toilet", 1130, logs.get(1)),
                new CleaningProcedure("change towels", 2015, logs.get(2))
                )
        );
    }
}
