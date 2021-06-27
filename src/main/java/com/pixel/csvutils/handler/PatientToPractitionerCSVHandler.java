package com.pixel.csvutils.handler;

import com.pixel.model.PatientToPractitioner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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

public class PatientToPractitionerCSVHandler {
    static String[] HEADERS = {"patientId", "practitionerId"};

    public static List<PatientToPractitioner> fromCSVFileToPractitioners(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            List<PatientToPractitioner> p2pList = new ArrayList<>();
            Iterable<CSVRecord> records = csvParser.getRecords();

            listPopulating(p2pList, records);
            return p2pList;

        } catch (IOException uee) {
            System.out.println("Failed to parse patient to practitioner CSV file: " + uee.getMessage());
            return Collections.emptyList();
        }
    }

    private static void listPopulating(final List<PatientToPractitioner> p2pList, final Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            PatientToPractitioner p2p = new PatientToPractitioner(
                    Integer.parseInt(record.get(HEADERS[0])),
                    Integer.parseInt(record.get((HEADERS[1])))
            );

            p2pList.add(p2p);
        }
    }
}
