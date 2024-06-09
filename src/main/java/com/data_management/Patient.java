package com.data_management;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int id;
    private List<PatientRecord> records;

    public Patient(int id) {
        this.id = id;
        this.records = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void addRecord(double measurementValue, String recordType, long timestamp) {
        records.add(new PatientRecord(id, measurementValue, recordType, timestamp));
    }

    public List<PatientRecord> getRecords(long startTime, long endTime) {
        List<PatientRecord> result = new ArrayList<>();
        for (PatientRecord record : records) {
            if (record.getTimestamp() >= startTime && record.getTimestamp() <= endTime) {
                result.add(record);
            }
        }
        return result;
    }

    public int getHeartRate() {
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("HeartRate")) {
                return (int) record.getMeasurementValue();
            }
        }
        return 0; // Default value if no heart rate record is found
    }

    public int getBloodOxygenSaturation() {
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("BloodOxygenSaturation")) {
                return (int) record.getMeasurementValue();
            }
        }
        return 0; // Default value if no blood oxygen saturation record is found
    }

    public int getDiastolicBloodPressure() {
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("DiastolicBloodPressure")) {
                return (int) record.getMeasurementValue();
            }
        }
        return 0; // Default value if no diastolic blood pressure record is found
    }

    public int getSystolicBloodPressure() {
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("SystolicBloodPressure")) {
                return (int) record.getMeasurementValue();
            }
        }
        return 0; // Default value if no systolic blood pressure record is found
    }

    public int getCholesterol() {
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("Cholesterol")) {
                return (int) record.getMeasurementValue();
            }
        }
        return 0; // Default value if no cholesterol record is found
    }

    public double getWhiteBloodCellCount() {
        for (PatientRecord record : records) {
            if (record.getRecordType().equals("WhiteBloodCellCount")) {
                return record.getMeasurementValue();
            }
        }
        return 0; // Default value if no white blood cell count record is found
    }

    public void setSystolicBloodPressure(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSystolicBloodPressure'");
    }

    public void setHeartRate(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHeartRate'");
    }

    public void setBloodOxygenSaturation(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBloodOxygenSaturation'");
    }

    public void setDiastolicBloodPressure(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDiastolicBloodPressure'");
    }
}
