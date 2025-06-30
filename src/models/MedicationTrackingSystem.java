package models;

import java.util.ArrayList;

public class MedicationTrackingSystem {
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Medication> medications;
    private ArrayList<Prescription> prescriptions;

    public MedicationTrackingSystem() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addMedication(Medication med) {
        medications.add(med);
    }

    public void addPrescription(Prescription pres) {
        prescriptions.add(pres);
        pres.getPatient().addPrescription(pres);
        pres.getPatient().addMedication(pres.getMedication());

        Medication med = pres.getMedication();
        med.setQuantityInStock(med.getQuantityInStock() - 1);
    }

    public Patient findPatientByName(String name) {
        for (Patient p : patients) {
            if (p.name.equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
}
