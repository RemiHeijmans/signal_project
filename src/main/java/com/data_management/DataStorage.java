package com.data_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.alerts.AlertGenerator;

public class DataStorage {
    private static DataStorage instance; // Singleton instance
    private final Map<Integer, Patient> patientMap;
    private final ReentrantReadWriteLock rwLock;
    private final Lock readLock;
    private final Lock writeLock;

    public DataStorage() { // Private constructor
        this.patientMap = new HashMap<>();
        this.rwLock = new ReentrantReadWriteLock();
        this.readLock = rwLock.readLock();
        this.writeLock = rwLock.writeLock();
    }

    // Synchronized method to control simultaneous access
    public static synchronized DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public void addPatientData(int patientId, double measurementValue, String recordType, long timestamp) {
        writeLock.lock();
        try {
            Patient patient = patientMap.get(patientId);
            if (patient == null) {
                patient = new Patient(patientId);
                patientMap.put(patientId, patient);
            }
            patient.addRecord(measurementValue, recordType, timestamp);
        } finally {
            writeLock.unlock();
        }
    }

    public List<PatientRecord> getRecords(int patientId, long startTime, long endTime) {
        readLock.lock();
        try {
            Patient patient = patientMap.get(patientId);
            if (patient != null) {
                return patient.getRecords(startTime, endTime);
            }
            return new ArrayList<>();
        } finally {
            readLock.unlock();
        }
    }

    public List<Patient> getAllPatients() {
        readLock.lock();
        try {
            return new ArrayList<>(patientMap.values());
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        DataStorage storage = DataStorage.getInstance();

        List<PatientRecord> records = storage.getRecords(1, 1700000000000L, 1800000000000L);
        for (PatientRecord record : records) {
            System.out.println("Record for Patient ID: " + record.getPatientId() +
                    ", Type: " + record.getRecordType() +
                    ", Data: " + record.getMeasurementValue() +
                    ", Timestamp: " + record.getTimestamp());
        }

        AlertGenerator alertGenerator = new AlertGenerator(storage);

        for (Patient patient : storage.getAllPatients()) {
            alertGenerator.evaluateData(patient);
        }
    }
}
