package models;

import java.util.ArrayList;

/**
 * Represents a patient in the pharma system.
 * A patient has a list of medications and prescriptions.
 */
public class Patient extends Person {
    private ArrayList<Medication> medications;
    private ArrayList<Prescription> prescriptions;

    /**
     * Constructs a Patient object.
     *
     * @param id          the patient's ID
     * @param name        the patient's name
     * @param age         the patient's age
     * @param phoneNumber the patient's phone number
     */
    public Patient(int id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    /**
     * Adds a medication to the patient's list if not already present.
     *
     * @param med the medication to add
     */
    public void addMedication(Medication med) {
        if (!medications.contains(med)) {
            medications.add(med);
        }
    }

    /**
     * Adds a prescription to the patient's list.
     *
     * @param pres the prescription to add
     */
    public void addPrescription(Prescription pres) {
        prescriptions.add(pres);
    }

    /**
     * Gets the list of prescriptions for the patient.
     *
     * @return the list of prescriptions
     */
    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    /**
     * Gets the list of medications for the patient.
     *
     * @return the list of medications
     */
    public ArrayList<Medication> getMedications() {
        return medications;
    }
}
