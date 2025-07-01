package models;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashSet;

public class MedicationTrackingSystem {
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Medication> medications = new ArrayList<>();
    private ArrayList<Prescription> prescriptions = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
        prescription.getPatient().addPrescription(prescription);
        prescription.getPatient().addMedication(prescription.getMedication());
        prescription.getMedication().reduceStock(1);
    }

    public Patient findPatientByName(String name) {
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public boolean deletePatientByName(String name) {
        return patients.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public boolean deleteDoctorByName(String name) {
        return doctors.removeIf(d -> d.getName().equalsIgnoreCase(name));
    }

    public boolean deleteMedicationByName(String name) {
        return medications.removeIf(m -> m.getName().equalsIgnoreCase(name));
    }

    public boolean editPatientName(String currentName, String newName) {
        for (Patient p : patients) {
            if (p.getName().equalsIgnoreCase(currentName)) {
                p.setName(newName);
                return true;
            }
        }
        return false;
    }

    public boolean editDoctorSpecialization(String name, String newSpec) {
        for (Doctor d : doctors) {
            if (d.getName().equalsIgnoreCase(name)) {
                d.setSpecialization(newSpec);
                return true;
            }
        }
        return false;
    }

    public boolean editMedicationStock(String name, int newStock) {
        for (Medication m : medications) {
            if (m.getName().equalsIgnoreCase(name)) {
                m.setQuantityInStock(newStock);
                return true;
            }
        }
        return false;
    }

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

    public void restockMedications(int amount) {
        for (Medication med : medications) {
            int newStock = med.getQuantityInStock() + amount;
            med.setQuantityInStock(newStock);
        }
    }

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
