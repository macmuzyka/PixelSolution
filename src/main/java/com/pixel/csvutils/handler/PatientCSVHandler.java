package com.pixel.csvutils.handler;

import com.pixel.model.Patient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 27.06.2021
 */

public class PatientCSVHandler {

    static String[] HEADERS = {"patientId", "firstName", "lastName", "city", "createdAt"};

    public static List<Patient> fromCSVFileToPatients(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim())) {

            List<Patient> patientList = new ArrayList<>();
            Iterable<CSVRecord> records = csvParser.getRecords();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            populatingList(patientList, records, dtf);
            return patientList;

        } catch (IOException uee) {
            System.out.println("Failed to parse patient CSV file: " + uee.getMessage());
            return Collections.emptyList();
        }

    }

    private static void populatingList(final List<Patient> patientList, final Iterable<CSVRecord> records, final DateTimeFormatter dtf) {
        for (CSVRecord record : records) {
            Patient patient = new Patient(
                    Integer.parseInt(record.get(HEADERS[0])),
                    record.get(HEADERS[1]),
                    record.get(HEADERS[2]),
                    record.get(HEADERS[3]),
                    LocalDateTime.parse(record.get(HEADERS[4]), dtf).truncatedTo(ChronoUnit.SECONDS)
            );

            patientList.add(patient);
        }
    }
}
