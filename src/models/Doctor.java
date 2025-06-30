package models;

import java.util.ArrayList;

public class Doctor extends Person {
    private String specialization;
    private ArrayList<Patient> patients;

    public Doctor(int id, String name, int age, String phoneNumber, String specialization) {
        super(id, name, age, phoneNumber);
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }

}
