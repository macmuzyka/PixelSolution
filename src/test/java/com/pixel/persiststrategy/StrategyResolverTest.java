package com.pixel.persiststrategy;

import com.pixel.advice.IllegalPersistStrategy;
import com.pixel.csvutils.service.P2PRecords;
import com.pixel.csvutils.service.PatientRecords;
import com.pixel.csvutils.service.PractitionerRecords;
import com.pixel.csvutils.service.VisitRecords;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

/**
 * Created by Maciej Muzyka
 * on 10.07.2021
 */

class StrategyResolverTest extends BaseStrategyResolverTest {
    @Test
    @Override
    @DisplayName("Persist strategy should not be resolved and custom runtime exception should be thrown")
    void shouldThrowIllegalPersistStrategyException() {
        var exception = catchThrowable(() -> resolver.resolve("path/to/a/file/that/does/not/exist"));
        assertThat(exception)
                .isInstanceOf(IllegalPersistStrategy.class)
                .hasMessageContaining("Persist strategy not resolved!");
    }

    @ParameterizedTest
    @Override
    @ValueSource(strings = {"src/main/resources/patient2practitioner.csv",
            "src/main/resources/patients.csv",
            "src/main/resources/practitioners.csv",
            "src/main/resources/visits.csv"})
    @DisplayName("Persist strategy should be resolved with proper files paths")
    void shouldReturnOneOfPersistStrategies(String file) {
        assertThat(resolver.resolve(file))
                .isInstanceOfAny(P2PRecords.class,
                        PatientRecords.class,
                        PractitionerRecords.class,
                        VisitRecords.class);
    }
}
