package models;

import java.util.ArrayList;

public class Patient extends Person {
    private ArrayList<Medication> medications;
    private ArrayList<Prescription> prescriptions;

    public Patient(int id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public void addMedication(Medication med) {
        if (!medications.contains(med)) {
            medications.add(med);
        }
    }

    public void addPrescription(Prescription pres) {
        prescriptions.add(pres);
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }
}
