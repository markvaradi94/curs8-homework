package ro.fasttrackit.curs8homework.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs8homework.model.entity.Room;
import ro.fasttrackit.curs8homework.model.filters.RoomFilters;
import ro.fasttrackit.curs8homework.repository.RoomDao;
import ro.fasttrackit.curs8homework.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomDao dao;
    private final RoomRepository roomRepository;
    private final ObjectMapper mapper;

    public List<Room> getAll(RoomFilters filters) {
        return dao.getAll(filters);
    }

    public Optional<Room> getById(Long roomId) {
        return roomRepository.findById(roomId);
    }

    public Room replaceRoom(Long roomId, Room newRoom) {
        newRoom.setId(roomId);
        Room roomToUpdate = getOrThrow(roomId);
        copyRoom(newRoom, roomToUpdate);
        return roomRepository.save(roomToUpdate);
    }

    @SneakyThrows
    public Room patchRoom(Long roomId, JsonPatch patch) {
        Room roomToPatch = getOrThrow(roomId);
        JsonNode patchedRoomJson = patch.apply(mapper.valueToTree(roomToPatch));
        Room patchedRoom = mapper.treeToValue(patchedRoomJson, Room.class);
        return replaceRoom(roomId, patchedRoom);
    }

    public Room deleteRoom(Long roomId) {
        Room roomToDelete = getOrThrow(roomId);
        roomRepository.delete(roomToDelete);
        return roomToDelete;
    }

    private Room getOrThrow(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find room with id "));
    }

    private void copyRoom(Room newRoom, Room dbRoom) {
        dbRoom.setFacilities(newRoom.getFacilities());
        dbRoom.setFloor(newRoom.getFloor());
        dbRoom.setHotelName(newRoom.getHotelName());
        dbRoom.setNumber(newRoom.getNumber());
    }
}
