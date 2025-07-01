package models;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashSet;

/**
 * MedicationTrackingSystem manages patients, doctors, medications, and prescriptions
 * in a pharmaceutical system. It provides methods to add, edit, delete, and report
 * on these entities.
 */
public class MedicationTrackingSystem {
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Medication> medications = new ArrayList<>();
    private ArrayList<Prescription> prescriptions = new ArrayList<>();

    /**
     * Adds a patient to the system.
     * @param patient the patient to add
     */
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Adds a doctor to the system.
     * @param doctor the doctor to add
     */
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    /**
     * Adds a medication to the system.
     * @param medication the medication to add
     */
    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    /**
     * Adds a prescription to the system, updates the patient's prescriptions and medications,
     * and reduces the medication stock by 1.
     * @param prescription the prescription to add
     */
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
        prescription.getPatient().addPrescription(prescription);
        prescription.getPatient().addMedication(prescription.getMedication());
        prescription.getMedication().reduceStock(1);
    }

    /**
     * Finds a patient by name (case-insensitive).
     * @param name the name of the patient
     * @return the Patient object if found, otherwise null
     */
    public Patient findPatientByName(String name) {
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Gets the list of patients in the system.
     * @return the list of patients
     */
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    /**
     * Gets the list of doctors in the system.
     * @return the list of doctors
     */
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * Gets the list of medications in the system.
     * @return the list of medications
     */
    public ArrayList<Medication> getMedications() {
        return medications;
    }

    /**
     * Deletes a patient by name (case-insensitive).
     * @param name the name of the patient to delete
     * @return true if a patient was deleted, false otherwise
     */
    public boolean deletePatientByName(String name) {
        return patients.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    /**
     * Deletes a doctor by name (case-insensitive).
     * @param name the name of the doctor to delete
     * @return true if a doctor was deleted, false otherwise
     */
    public boolean deleteDoctorByName(String name) {
        return doctors.removeIf(d -> d.getName().equalsIgnoreCase(name));
    }

    /**
     * Deletes a medication by name (case-insensitive).
     * @param name the name of the medication to delete
     * @return true if a medication was deleted, false otherwise
     */
    public boolean deleteMedicationByName(String name) {
        return medications.removeIf(m -> m.getName().equalsIgnoreCase(name));
    }

    /**
     * Edits a patient's name.
     * @param currentName the current name of the patient
     * @param newName the new name to set
     * @return true if the patient's name was updated, false otherwise
     */
    public boolean editPatientName(String currentName, String newName) {
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(currentName)) {
                p.setName(newName);
                return true;
            }
        }
        return false;
    }

    /**
     * Edits a doctor's specialization.
     * @param name the name of the doctor
     * @param newSpec the new specialization to set
     * @return true if the doctor's specialization was updated, false otherwise
     */
    public boolean editDoctorSpecialization(String name, String newSpec) {
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(name)) {
                d.setSpecialization(newSpec);
                return true;
            }
        }
        return false;
    }

    /**
     * Edits the stock quantity of a medication.
     * @param name the name of the medication
     * @param newStock the new stock quantity to set
     * @return true if the medication stock was updated, false otherwise
     */
    public boolean editMedicationStock(String name, int newStock) {
        for (Medication m : medications) {
            if (m.getName().equalsIgnoreCase(name)) {
                m.setQuantityInStock(newStock);
                return true;
            }
        }
        return false;
    }

    /**
     * Assigns a patient to a doctor by their names.
     * @param patientName the name of the patient
     * @param doctorName the name of the doctor
     * @return true if the patient was assigned, false otherwise
     */
    public boolean assignPatientToDoctor(String patientName, String doctorName) {
        Patient patient = findPatientByName(patientName);
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(doctorName)) {
                d.addPatient(patient);
                return true;
            }
        }
        return false;
    }

    /**
     * Restocks all medications by a specified amount.
     * @param amount the amount to add to each medication's stock
     */
    public void restockMedications(int amount) {
        for (Medication med : medications) {
            int newStock = med.getQuantityInStock() + amount;
            med.setQuantityInStock(newStock);
        }
    }

    /**
     * Prints all prescriptions issued by a specific doctor.
     * @param doctorName the name of the doctor
     */
    public void printPrescriptionsByDoctor(String doctorName) {
        boolean found = false;
        for (Prescription p : prescriptions) {
            if (p.getDoctor().getName().equalsIgnoreCase(doctorName)) {
                if (!found) {
                    System.out.println("Prescriptions issued by Dr. " + doctorName + ":");
                    found = true;
                }
                System.out.println("- Prescription ID: " + p.getId() + ", Patient: " + p.getPatient().getName() + ", Medication: " + p.getMedication().getName() + ", Expiry: " + p.getPrescriptionExpiry());
            }
        }
        if (!found) {
            System.out.println("No prescriptions found for Dr. " + doctorName + ".");
        }
    }

    /**
     * Prints a summary of prescriptions for a patient in the past year.
     * @param patientName the name of the patient
     */
    public void printPatientPrescriptionSummary(String patientName) {
        Patient patient = findPatientByName(patientName);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        HashSet<String> medsInPastYear = new HashSet<>();

        for (Prescription p : prescriptions) {
            if (p.getPatient().getName().equalsIgnoreCase(patientName)
                    && !p.getPrescriptionExpiry().isBefore(oneYearAgo)) {
                medsInPastYear.add(p.getMedication().getName());
            }
        }

        if (medsInPastYear.isEmpty()) {
            System.out.println("No prescriptions found for patient " + patientName + " in the past year.");
        } else {
            System.out.println("Prescriptions for patient " + patientName + " in the past year:");
            for (String medName : medsInPastYear) {
                System.out.println("- " + medName);
            }
        }
    }
    
    /**
     * Prints a full system report including all patients, doctors, medications, and prescriptions.
     */
    public void printFullSystemReport() {
        System.out.println("\n=== FULL SYSTEM REPORT ===\n");

        System.out.println("Patients:");
        if (patients.isEmpty()) {
            System.out.println("  No patients in system.");
        } else {
            for (Patient p : patients) {
                System.out.println("  ID: " + p.getId() + ", Name: " + p.getName() + ", Age: " + p.getAge() + ", Phone: " + p.getPhoneNumber());
            }
        }

        System.out.println("\nDoctors:");
        if (doctors.isEmpty()) {
            System.out.println("  No doctors in system.");
        } else {
            for (Doctor d : doctors) {
                System.out.println("  ID: " + d.getId() + ", Name: " + d.getName() + ", Specialization: " + d.getSpecialization() + ", Phone: " + d.getPhoneNumber());
            }
        }

        System.out.println("\nMedications:");
        if (medications.isEmpty()) {
            System.out.println("  No medications in system.");
        } else {
            for (Medication m : medications) {
                System.out.println("  ID: " + m.getId() + ", Name: " + m.getName() + ", Dose: " + m.getDose() +
                    ", Stock: " + m.getQuantityInStock() + ", Expiry: " + m.getExpiryDate());
            }
        }

        System.out.println("\nPrescriptions:");
        if (prescriptions.isEmpty()) {
            System.out.println("  No prescriptions issued.");
        } else {
            for (Prescription p : prescriptions) {
                System.out.println("  ID: " + p.getId() + ", Doctor: " + p.getDoctor().getName() +
                        ", Patient: " + p.getPatient().getName() + ", Medication: " + p.getMedication().getName() +
                        ", Expiry: " + p.getPrescriptionExpiry());
            }
        }

        System.out.println("\n=== END OF REPORT ===");
    }

}
