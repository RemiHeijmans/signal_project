package com.alerts;

// Represents an alert
public class Alert {
    private int patientId;
    private String condition;
    private long timestamp;

    public Alert(int patientId2, String condition, long timestamp) {
        this.patientId = patientId2;
        this.condition = condition;
        this.timestamp = timestamp;
    }



    public int getPatientId() {
        return patientId;
    }

    public String getCondition() {
        return condition;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
