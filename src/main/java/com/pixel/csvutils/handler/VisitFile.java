package com.pixel.csvutils.handler;

import com.pixel.model.Visit;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Component
public class VisitFile implements CSVTransformer {

    static String[] HEADERS = {"visitId", "practitionerId", "patientId"};

    @Override
    public List<Visit> fromFileToRecordsList(final InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            List<Visit> visitList = new ArrayList<>();
            Iterable<CSVRecord> records = csvParser.getRecords();

            listPopulating(visitList,
                    records);
            return visitList;

        } catch (IOException uee) {
            System.out.println("Failed to parse patient CSV file: " + uee.getMessage());
            return Collections.emptyList();
        }
    }

    private static void listPopulating(final List<Visit> visitList, final Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            Visit visit = new Visit(
                    Integer.parseInt(record.get(HEADERS[0])),
                    Integer.parseInt(record.get(HEADERS[1])),
                    Integer.parseInt(record.get(HEADERS[2]))
            );

            visitList.add(visit);
        }
    }
}
