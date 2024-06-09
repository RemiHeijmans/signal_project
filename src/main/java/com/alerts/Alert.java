package com.alerts;

public class Alert implements AlertInterface {
    private int patientId;
    private String condition;
    private long timestamp;
    private String type;

    public Alert(int patientId, String type, long timestamp) {
        this.patientId = patientId;
        this.type = type;
        this.timestamp = timestamp;
        this.condition = type;  // Assuming condition and type are the same here
    }

    @Override
    public int getPatientId() {
        return patientId;
    }

    @Override
    public String getCondition() {
        return condition;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public void trigger() {
        // Trigger logic
    }

    @Override
    public String getType() {
        return type;
    }
}
