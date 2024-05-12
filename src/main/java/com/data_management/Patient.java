package com.data_management;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a patient and manages their medical records.
 * This class stores patient-specific data, allowing for the addition and
 * retrieval
 * of medical records based on specified criteria.
 */
public class Patient {
    private int patientId;
    private List<PatientRecord> patientRecords;

    /**
     * Constructs a new Patient with a specified ID.
     * Initializes an empty list of patient records.
     *
     * @param patientId the unique identifier for the patient
     */
    public Patient(int patientId) {
        this.patientId = patientId;
        this.patientRecords = new ArrayList<>();
    }

    /**
     * Adds a new record to this patient's list of medical records.
     * The record is created with the specified measurement value, record type, and
     * timestamp.
     *
     * @param measurementValue the measurement value to store in the record
     * @param recordType       the type of record, e.g., "HeartRate",
     *                         "BloodPressure"
     * @param timestamp        the time at which the measurement was taken, in
     *                         milliseconds since UNIX epoch
     */
    public void addRecord(double measurementValue, String recordType, long timestamp) {
        PatientRecord record = new PatientRecord(this.patientId, measurementValue, recordType, timestamp);
        this.patientRecords.add(record);
    }

    /**
     * Retrieves a list of PatientRecord objects for this patient that fall within a
     * specified time range.
     * The method filters records based on the start and end times provided.
     *
     * @param startTime the start of the time range, in milliseconds since UNIX
     *                  epoch
     * @param endTime   the end of the time range, in milliseconds since UNIX epoch
     * @return a list of PatientRecord objects that fall within the specified time
     *         range
     */
    public List<PatientRecord> getRecords(long startTime, long endTime) {// this gets the records from a start time till a endt
        List<PatientRecord> recordsInRange = new ArrayList<>();
        for (PatientRecord record : patientRecords) {
            long recordTimestamp = record.getTimestamp();
            if (recordTimestamp >= startTime && recordTimestamp <= endTime) {
                recordsInRange.add(record);
            }
        }
        return recordsInRange;
    }
                    

    public int getCholesterol() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCholesterol'");
    }

    public int getSystolicBloodPressure() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSystolicBloodPressure'");
    }

    public double getWhiteBloodCellCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWhiteBloodCellCount'");
    }

    public int getDiastolicBloodPressure() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDiastolicBloodPressure'");
    }

    public int getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    public int getBloodOxygenSaturation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBloodOxygenSaturation'");
    }

    public int getHeartRate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHeartRate'");
    }

    public boolean isTriggeredAlert() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isTriggeredAlert'");
    }
}