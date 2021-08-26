package com.pixel.recordsequality;

import com.pixel.CSVFileNameProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

abstract class RecordsEqualityTest {
    protected static CSVFileNameProvider provider;
    protected static List<String> filesPaths;

    @BeforeAll
    static void setUp() {
        provider = new CSVFileNameProvider();
        filesPaths = provider.execute();
    }

    @Test
    abstract void numberOfCSVRecordsListShouldBeEqualToModelListSize() throws IOException;
}