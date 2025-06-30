package models;

import java.time.LocalDate;

public class Prescription {
    private int id;
    private Doctor doctor;
    private Patient patient;
    private Medication medication;
    private LocalDate issueDate;
    private LocalDate expiryDate;

    public Prescription(int id, Doctor doctor, Patient patient, Medication medication, LocalDate issueDate) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.issueDate = issueDate;
        this.expiryDate = issueDate.plusYears(1);
    }

    public Patient getPatient() {
        return patient;
    }

    public Medication getMedication() {
        return medication;
    }
}
