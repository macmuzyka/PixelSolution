package com.pixel.persiststrategy;

import com.pixel.csvutils.handler.PatientFile;
import com.pixel.csvutils.handler.PatientToPractitionerFile;
import com.pixel.csvutils.handler.PractitionerFile;
import com.pixel.csvutils.handler.VisitFile;
import com.pixel.csvutils.service.PersistStrategyResolver;
import com.pixel.model.repository.PatientRepository;
import com.pixel.model.repository.PatientToPractitionerRepository;
import com.pixel.model.repository.PractitionerRepository;
import com.pixel.model.repository.VisitRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by Maciej Muzyka
 * on 10.07.2021
 */

abstract class BaseStrategyResolverTest {
    protected static PersistStrategyResolver resolver;

    @BeforeAll
    static void setUp() {
        resolver = new PersistStrategyResolver(mock(PatientFile.class), mock(PatientRepository.class),
                mock(VisitFile.class), mock(VisitRepository.class),
                mock(PractitionerFile.class), mock(PractitionerRepository.class),
                mock(PatientToPractitionerFile.class), mock(PatientToPractitionerRepository.class));
    }

    @Test
    abstract void shouldThrowIllegalPersistStrategyException();

    @Test
    abstract void shouldReturnOneOfPersistStrategies(String file);
}
