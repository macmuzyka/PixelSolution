package com.pixel.recordsequality;

import com.pixel.CSVFileNameProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

abstract class RecordsEqualityTest {
    protected CSVFileNameProvider provider;
    protected List<String> filesPaths;

    @BeforeEach
    void setUp() {
        provider = new CSVFileNameProvider();
        filesPaths = provider.execute();
    }

    @Test
    abstract void numberOfCSVRecordsListShouldBeEqualToModelListSize() throws IOException;
}