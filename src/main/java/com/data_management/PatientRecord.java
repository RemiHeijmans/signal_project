package com.data_management;

public class PatientRecord {
    private int patientId;
    private double measurementValue;
    private String recordType;
    private long timestamp;

    public PatientRecord(int patientId, double measurementValue, String recordType, long timestamp) {
        this.patientId = patientId;
        this.measurementValue = measurementValue;
        this.recordType = recordType;
        this.timestamp = timestamp;
    }

    public int getPatientId() {
        return patientId;
    }

    public double getMeasurementValue() {
        return measurementValue;
    }

    public String getRecordType() {
        return recordType;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
