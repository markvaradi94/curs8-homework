package ro.fasttrackit.curs8homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs8homework.model.entity.CleaningProcedure;
import ro.fasttrackit.curs8homework.model.entity.CleanupLog;
import ro.fasttrackit.curs8homework.repository.CleanupLogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CleanupLogService {
    private final CleanupLogRepository cleanupLogRepository;
    private final CleaningProcedureService cleaningProcedureService;

    public List<CleaningProcedure> getCleaningProceduresForRoom(Long roomId) {
        CleanupLog logToFind = getOrThrow(roomId);
        return cleaningProcedureService.getAllCleaningProceduresForLog(logToFind.getId());
    }

    private CleanupLog getOrThrow(Long roomId) {
        return cleanupLogRepository.findCleanupLogByHotelRoom_Id(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find room with id "));
    }
}
