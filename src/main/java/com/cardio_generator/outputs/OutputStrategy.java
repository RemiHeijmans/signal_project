package com.cardio_generator.outputs;

/**
 * The OutputStrategy interface  defines the contract for classes that handle output  
 * of patient data in all of the health monitoring simulations.
 */
public interface OutputStrategy {

    /**
     * it makes sure that it outputs patient data
     * all the output methods use this interface
     * @param patientId The ID of the patient associated with the data.
     * @param timestamp The timestamp indicating when the data was generated.
     * @param label A label describing the type or category of the data.
     * @param data The actual data to be output.
     */
    void output(int patientId, long timestamp, String label, String data);
}
