package ro.fasttrackit.curs8homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs8homework.model.entity.CleaningProcedure;
import ro.fasttrackit.curs8homework.repository.CleaningProcedureRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CleaningProcedureService {
    private final CleaningProcedureRepository cleaningProcedureRepository;

    public List<CleaningProcedure> getAllCleaningProceduresForLog(Long cleanupId) {
        return cleaningProcedureRepository.findByCleanupLog_Id(cleanupId);
    }
}
