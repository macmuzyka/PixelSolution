package com.pixel.csvutils.service;

import com.pixel.csvutils.handler.VisitCSVHandler;
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
public class VisitsFile implements CSVService{

    VisitRepository visitRepository;

    VisitsFile(final VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public void save(MultipartFile file) throws IOException {
        try {
            List<Visit> visits = VisitCSVHandler.fromCSVFileToVisits(file.getInputStream());
            visitRepository.saveAll(visits);
        } catch (IOException ioe) {
            System.out.println("Failed to get visit csv data from file: " + ioe.getMessage());
        }
    }
}
