package models;

import java.time.LocalDate;

/**
 * Represents a prescription in the pharma system.
 * A prescription links a doctor, patient, medication, and issue/expiry dates.
 */
public class Prescription {
    private int id;
    private Doctor doctor;
    private Patient patient;
    private Medication medication;
    private LocalDate issueDate;
    private LocalDate expiryDate;

    /**
     * Constructs a Prescription object.
     *
     * @param id         the prescription ID
     * @param doctor     the doctor issuing the prescription
     * @param patient    the patient receiving the prescription
     * @param medication the medication prescribed
     * @param issueDate  the date the prescription was issued
     */
    public Prescription(int id, Doctor doctor, Patient patient, Medication medication, LocalDate issueDate) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.issueDate = issueDate;
        this.expiryDate = issueDate.plusYears(1);
    }

    /**
     * Gets the prescription ID.
     *
     * @return the prescription ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the doctor who issued the prescription.
     *
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Gets the patient who received the prescription.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Gets the medication prescribed.
     *
     * @return the medication
     */
    public Medication getMedication() {
        return medication;
    }

    /**
     * Gets the date the prescription was issued.
     *
     * @return the issue date
     */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * Gets the expiry date of the prescription.
     *
     * @return the expiry date
     */
    public LocalDate getPrescriptionExpiry() {
        return expiryDate;
    }
}
