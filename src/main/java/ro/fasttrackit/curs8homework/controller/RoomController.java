package ro.fasttrackit.curs8homework.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs8homework.model.entity.CleaningProcedure;
import ro.fasttrackit.curs8homework.model.entity.Room;
import ro.fasttrackit.curs8homework.model.filters.RoomFilters;
import ro.fasttrackit.curs8homework.service.CleanupLogService;
import ro.fasttrackit.curs8homework.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final CleanupLogService cleanupLogService;

    @GetMapping
    List<Room> getAllRooms(RoomFilters filters) {
        return roomService.getAll(filters);
    }

    @GetMapping("{roomId}")
    Room getRoom(@PathVariable Long roomId) {
        return roomService.getById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find room with ID " + roomId));
    }

    @GetMapping("{roomId}/cleanups")
    List<CleaningProcedure> getRoomCleanups(@PathVariable Long roomId) {
        return cleanupLogService.getCleaningProceduresForRoom(roomId);
    }

    @PatchMapping("{roomId}")
    Room patchRoom(@RequestBody JsonPatch patch, @PathVariable Long roomId) {
        return roomService.patchRoom(roomId, patch);
    }

    @DeleteMapping("{roomId}")
    Room deleteRoom(@PathVariable Long roomId) {
        return roomService.deleteRoom(roomId);
    }
}
