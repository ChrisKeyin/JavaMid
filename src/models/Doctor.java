package models;

import java.util.ArrayList;

/**
 * Represents a doctor in the pharma system.
 * A doctor is a person with a specialization and a list of patients.
 */
public class Doctor extends Person {
    private String specialization;
    private ArrayList<Patient> patients;

    /**
     * Constructs a Doctor object.
     *
     * @param id             the doctor's ID
     * @param name           the doctor's name
     * @param age            the doctor's age
     * @param phoneNumber    the doctor's phone number
     * @param specialization the doctor's medical specialization
     */
    public Doctor(int id, String name, int age, String phoneNumber, String specialization) {
        super(id, name, age, phoneNumber);
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }

    /**
     * Sets the doctor's specialization.
     *
     * @param specialization the new specialization
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Gets the doctor's specialization.
     *
     * @return the specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Adds a patient to the doctor's list of patients.
     *
     * @param patient the patient to add
     */
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Gets the list of patients assigned to the doctor.
     *
     * @return the list of patients
     */
    public ArrayList<Patient> getPatients() {
        return patients;
    }
}
