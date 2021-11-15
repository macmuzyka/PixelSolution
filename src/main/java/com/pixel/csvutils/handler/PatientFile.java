package com.pixel.csvutils.handler;

import com.pixel.model.Patient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

@Component
public class PatientFile implements CSVTransformer {

    private static final String[] HEADERS = {"patientId", "firstName", "lastName", "city", "createdAt"};
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<Patient> fromFileToRecordsList(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {
            List<Patient> patientList = new ArrayList<>();
            Iterable<CSVRecord> records = csvParser.getRecords();

            populatingList(patientList,
                    records);
            return patientList;

        } catch (IOException uee) {
            System.out.println("Failed to parse patient CSV file: " + uee.getMessage());
            return Collections.emptyList();
        }

    }

    private static void populatingList(final List<Patient> patientList, final Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            Patient patient = new Patient(
                    Integer.parseInt(record.get(HEADERS[0])),
                    record.get(HEADERS[1]),
                    record.get(HEADERS[2]),
                    record.get(HEADERS[3]),
                    LocalDate.parse(record.get(HEADERS[4]), dtf));

            patientList.add(patient);
        }
    }
}
