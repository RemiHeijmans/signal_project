package com.alerts;

public class ECGAlertFactory extends AlertFactory {
    @Override
    public Alert createAlert(int patientId, String condition, long timestamp) {
        return new Alert(patientId, condition, timestamp);
    }
}
