package com.pixel.csvutils.handler;

import com.pixel.model.Practitioner;
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

public class PractitionerCSVHandler {

    static String[] HEADERS = {"practitionerId", "specialization"};

    public static List<Practitioner> fromCSVFileToPractitioners(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            List<Practitioner> practitionersList = new ArrayList<>();
            Iterable<CSVRecord> records = csvParser.getRecords();

            listPopulating(practitionersList, records);
            return practitionersList;

        } catch (IOException uee) {
            System.out.println("Failed to parse practitioner CSV file: " + uee.getMessage());
            return Collections.emptyList();
        }
    }

    private static void listPopulating(final List<Practitioner> practitionersList, final Iterable<CSVRecord> records) {
        for (CSVRecord record : records) {
            Practitioner practitioner = new Practitioner(
                    Integer.parseInt(record.get(HEADERS[0])),
                    record.get(HEADERS[1])
            );

            practitionersList.add(practitioner);
        }
    }
}
