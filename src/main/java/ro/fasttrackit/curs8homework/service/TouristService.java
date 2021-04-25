package ro.fasttrackit.curs8homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs8homework.model.entity.Tourist;
import ro.fasttrackit.curs8homework.repository.TouristRepository;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

@Service
@RequiredArgsConstructor
public class TouristService {
    private final TouristRepository touristRepository;

    public List<Tourist> getAll() {
        return unmodifiableList(touristRepository.findAll());
    }
}
