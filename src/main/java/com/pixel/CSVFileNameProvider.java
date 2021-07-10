package com.pixel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 07.07.2021
 */

public class CSVFileNameProvider {
    private static final String baseResourcesPath = "src/main/resources/";
    public static final String csvFileExtension = ".csv";
    private static final String[] filesNames = {"patients", "practitioners", "visits", "patient2practitioner"};

    public List<String> execute() {
        return populateListWithFilesNames();
    }

    private List<String> populateListWithFilesNames() {
        List<String> filesURLs = new ArrayList<>();
        for (String file : filesNames) {
            filesURLs.add(baseResourcesPath + file + csvFileExtension);
        }
        return filesURLs;
    }
}
