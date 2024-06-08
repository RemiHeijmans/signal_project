package data_management;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.data_management.Patient;
public class bigTest {

    // Test case for evaluating data with cholesterol below normal
    @Test
    public void testEvaluateDataCholesterolBelowNormal() {
        // Create a mock Patient with cholesterol below normal
        Patient patient = new Patient(0);
        patient.addRecord(100, "Cholesterol", System.currentTimeMillis()); // Cholesterol below normal

        // Get patient's cholesterol level
        int cholesterol = patient.getCholesterol();

        // Assert that cholesterol level is below normal
        assertTrue(cholesterol < 200); // Normal range: 200-239 mg/dL
    }

    // Test case for evaluating data with cholesterol above normal
    @Test
    public void testEvaluateDataCholesterolAboveNormal() {
        // Create a mock Patient with cholesterol above normal
        Patient patient = new Patient(0);
        patient.addRecord(200, "Cholesterol", System.currentTimeMillis()); // Cholesterol above normal

        // Get patient's cholesterol level
        int cholesterol = patient.getCholesterol();

        // Assert that cholesterol level is above normal
        assertTrue(cholesterol >= 200); // Normal range: 200-239 mg/dL
    }

    // Test case for evaluating data with systolic blood pressure below normal
    @Test
    public void testEvaluateDataSystolicBloodPressureBelowNormal() {
        // Create a mock Patient with systolic blood pressure below normal
        Patient patient = new Patient(0);
        patient.addRecord(90, "SystolicBloodPressure", System.currentTimeMillis()); // Systolic blood pressure below normal

        // Get patient's systolic blood pressure
        int systolicBloodPressure = patient.getSystolicBloodPressure();

        // Assert that systolic blood pressure is below normal
        assertTrue(systolicBloodPressure < 120); // Normal range: 90-120 mmHg
    }

    // Test case for evaluating data with systolic blood pressure above normal
    @Test
    public void testEvaluateDataSystolicBloodPressureAboveNormal() {
        // Create a mock Patient with systolic blood pressure above normal
        Patient patient = new Patient(0);
        patient.addRecord(160, "SystolicBloodPressure", System.currentTimeMillis()); // Systolic blood pressure above normal

        // Get patient's systolic blood pressure
        int systolicBloodPressure = patient.getSystolicBloodPressure();

        // Assert that systolic blood pressure is above normal
        assertTrue(systolicBloodPressure >= 120); // Normal range: 90-120 mmHg
    }

    // Test case for evaluating data with white blood cell count below normal
    @Test
    public void testEvaluateDataWhiteBloodCellCountBelowNormal() {
        // Create a mock Patient with white blood cell count below normal
        Patient patient = new Patient(0);
        patient.addRecord(3000, "WhiteBloodCellCount", System.currentTimeMillis()); // White blood cell count below normal

        // Get patient's white blood cell count
        double whiteBloodCellCount = patient.getWhiteBloodCellCount();

        // Assert that white blood cell count is below normal
        assertTrue(whiteBloodCellCount < 4000); // Normal range: 4000-10000 cells/mm^3
    }

    // Test case for evaluating data with white blood cell count above normal
    @Test
    public void testEvaluateDataWhiteBloodCellCountAboveNormal() {
        // Create a mock Patient with white blood cell count above normal
        Patient patient = new Patient(0);
        patient.addRecord(12000, "WhiteBloodCellCount", System.currentTimeMillis()); // White blood cell count above normal

        // Get patient's white blood cell count
        double whiteBloodCellCount = patient.getWhiteBloodCellCount();

        // Assert that white blood cell count is above normal
        assertTrue(whiteBloodCellCount >= 4000); // Normal range: 4000-10000 cells/mm^3
    }

    // Test case for evaluating data with diastolic blood pressure below normal
    @Test
    public void testEvaluateDataDiastolicBloodPressureBelowNormal() {
        // Create a mock Patient with diastolic blood pressure below normal
        Patient patient = new Patient(0);
        patient.addRecord(60, "DiastolicBloodPressure", System.currentTimeMillis()); // Diastolic blood pressure below normal

        // Get patient's diastolic blood pressure
        int diastolicBloodPressure = patient.getDiastolicBloodPressure();

        // Assert that diastolic blood pressure is below normal
        assertTrue(diastolicBloodPressure < 80); // Normal range: 60-80 mmHg
    }

    // Test case for evaluating data with diastolic blood pressure above normal
    @Test
    public void testEvaluateDataDiastolicBloodPressureAboveNormal() {
        // Create a mock Patient with diastolic blood pressure above normal
        Patient patient = new Patient(0);
        patient.addRecord(100, "DiastolicBloodPressure", System.currentTimeMillis()); // Diastolic blood pressure above normal

        // Get patient's diastolic blood pressure
        int diastolicBloodPressure = patient.getDiastolicBloodPressure();

        // Assert that diastolic blood pressure is above normal
        assertTrue(diastolicBloodPressure >= 80); // Normal range: 60-80 mmHg
    }

    // Test case for evaluating data with blood oxygen saturation below normal
    @Test
    public void testEvaluateDataBloodOxygenSaturationBelowNormal() {
        // Create a mock Patient with blood oxygen saturation below normal
        Patient patient = new Patient(0);
        patient.addRecord(90, "BloodOxygenSaturation", System.currentTimeMillis()); // Blood oxygen saturation below normal

        // Get patient's blood oxygen saturation
        int bloodOxygenSaturation = patient.getBloodOxygenSaturation();

        // Assert that blood oxygen saturation is below normal
        assertTrue(bloodOxygenSaturation < 95); // Normal range: 95-100%
    }

    // Test case for evaluating data with blood oxygen saturation above normal
    @Test
    public void testEvaluateDataBloodOxygenSaturationAboveNormal() {
        // Create a mock Patient with blood oxygen saturation above normal
        Patient patient = new Patient(0);
        patient.addRecord(98, "BloodOxygenSaturation", System.currentTimeMillis()); // Blood oxygen saturation above normal

        // Get patient's blood oxygen saturation
        int bloodOxygenSaturation = patient.getBloodOxygenSaturation();

        // Assert that blood oxygen saturation is above normal
        assertTrue(bloodOxygenSaturation >= 95); // Normal range: 95-100%
    }

    // Test case for evaluating data with heart rate below normal
    @Test
    public void testEvaluateDataHeartRateBelowNormal() {
        // Create a mock Patient with heart rate below normal
        Patient patient = new Patient(0);
        patient.addRecord(50, "HeartRate", System.currentTimeMillis()); // Heart rate below normal

        // Get patient's heart rate
        int heartRate = patient.getHeartRate();

        // Assert that heart rate is below normal
        assertTrue(heartRate < 60); // Normal range: 60-100 bpm
    }

    // Test case for evaluating data with heart rate above normal
    @Test
    public void testEvaluateDataHeartRateAboveNormal() {
        // Create a mock Patient with heart rate above normal
        Patient patient = new Patient(0);
        patient.addRecord(120, "HeartRate", System.currentTimeMillis()); // Heart rate above normal

        // Get patient's heart rate
        int heartRate = patient.getHeartRate();

        // Assert that heart rate is above normal
        assertTrue(heartRate >= 100); // Normal range: 60-100 bpm
    }

    // Additional test cases for other vital signs can be added similarly
}
