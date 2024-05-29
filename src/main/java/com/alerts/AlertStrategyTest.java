package com.alerts;

import com.data_management.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AlertStrategyTest {
    private AlertGenerator alertGenerator;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        alertGenerator = mock(AlertGenerator.class);
        patient = mock(Patient.class);
    }

    @Test
    public void testBloodPressureStrategy() {
        when(patient.getId()).thenReturn(1);
        when(patient.getSystolicBloodPressure()).thenReturn(190);
        when(patient.getDiastolicBloodPressure()).thenReturn(130);
        when(alertGenerator.getSystolicReadings()).thenReturn(new HashMap<>());
        when(alertGenerator.getDiastolicReadings()).thenReturn(new HashMap<>());
        when(alertGenerator.getLastReadingTimestamps()).thenReturn(new HashMap<>());

        BloodPressureStrategy strategy = new BloodPressureStrategy();
        strategy.checkAlert(patient, alertGenerator);

        verify(alertGenerator, times(1)).triggerAlert(any(Alert.class));
    }

    @Test
    public void testHeartRateStrategy() {
        when(patient.getId()).thenReturn(1);
        when(patient.getHeartRate()).thenReturn(120);

        HeartRateStrategy strategy = new HeartRateStrategy();
        strategy.checkAlert(patient, alertGenerator);

        verify(alertGenerator, times(1)).triggerAlert(any(Alert.class));
    }

    @Test
    public void testOxygenSaturationStrategy() {
        when(patient.getId()).thenReturn(1);
        when(patient.getBloodOxygenSaturation()).thenReturn(85);
        when(alertGenerator.getLastSaturationReadings()).thenReturn(new HashMap<>());
        when(alertGenerator.getLastSaturationTimestamps()).thenReturn(new HashMap<>());

        OxygenSaturationStrategy strategy = new OxygenSaturationStrategy();
        strategy.checkAlert(patient, alertGenerator);

        verify(alertGenerator, times(1)).triggerAlert(any(Alert.class));
    }
}
