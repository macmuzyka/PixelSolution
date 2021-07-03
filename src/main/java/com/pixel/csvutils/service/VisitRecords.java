package com.pixel.csvutils.service;

import com.pixel.csvutils.handler.VisitFile;
import com.pixel.model.Visit;
import com.pixel.model.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Service
public class VisitRecords implements CSVPersistence {

    private final VisitRepository visitRepository;
    private final VisitFile visitFile;

    VisitRecords(final VisitRepository visitRepository, final VisitFile visitFile) {
        this.visitRepository = visitRepository;
        this.visitFile = visitFile;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Visit> visits = visitFile.fromFileToRecordsList(file.getInputStream());
            visitRepository.saveAll(visits);
        } catch (IOException ioe) {
            System.out.println("Failed to get visit csv data from file: " + ioe.getMessage());
        }
    }
}
