package com.pixel.recordsequality;

import com.pixel.csvutils.handler.PatientFile;
import com.pixel.model.Patient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Maciej Muzyka
 * on 10.07.2021
 */

class PatientRecords extends RecordsEqualityTest{
    @Test
    @DisplayName("Both CSV Record and Model List for Patient should have an equal number of records")
    @Override
    void numberOfCSVRecordsListShouldBeEqualToModelListSize() throws IOException {
        PatientFile patientFile = new PatientFile();
        File file = new File(filesPaths.get(0));

        Path path = Paths.get(file.getPath());
        byte[] bytes = Files.readAllBytes(path);

        MultipartFile multipartFile = new MockMultipartFile(file.getName(), bytes);

        List<CSVRecord> recordsList = getCSVRecordsList(multipartFile);
        List<Patient> patients = patientFile.fromFileToRecordsList(multipartFile.getInputStream());

        assertEquals(recordsList.size(), patients.size());
    }

    private List<CSVRecord> getCSVRecordsList(final MultipartFile multipartFile) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), StandardCharsets.UTF_8));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

        return csvParser.getRecords();
    }
}
