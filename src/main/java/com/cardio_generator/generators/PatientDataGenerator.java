package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * The PatientDataGenerator interface defines the contract for classes that generate
 * patient data for all of the health monitoring simulations.
 * all the classes that generate data use this interface
 */
public interface PatientDataGenerator {

    /**
     * Generates patient data for the specified patient ID and outputs it using the given output strategy.
     *
     * @param patientId The ID of the patient for which to generate data  for.
     * @param outputStrategy The output strategy to use for displaing or storing the generated data.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
