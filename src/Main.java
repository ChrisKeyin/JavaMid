import models.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MedicationTrackingSystem system = new MedicationTrackingSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Pharmacy Management System ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Add Medication");
            System.out.println("4. Prescribe Medication");
            System.out.println("5. View All Patients");
            System.out.println("6. Delete Patient");
            System.out.println("7. Delete Doctor");
            System.out.println("8. Delete Medication");
            System.out.println("9. Edit Patient Name");
            System.out.println("10. Edit Doctor Specialization");
            System.out.println("11. Edit Medication Stock");
            System.out.println("12. Search Patient by Name");
            System.out.println("13. Search Doctor by Name");
            System.out.println("14. Search Medication by Name");
            System.out.println("15. Assign Patient to Doctor");
            System.out.println("16. View Doctor's Patients");
            System.out.println("17. View Expired Medications");
            System.out.println("18. Restock All Medications");
            System.out.println("19. Print Prescriptions by Doctor");
            System.out.println("20. Print Patient Prescription Summary (Past Year)");
            System.out.println("21. Print Full System Report");
            System.out.println("22. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            switch (input) {                
                case "1":
                    System.out.print("Enter patient ID: ");
                    int pid = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter name: ");
                    String pname = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int page = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    String pphone = scanner.nextLine();
                    system.addPatient(new Patient(pid, pname, page, pphone));
                    System.out.println("Patient added.");
                    break;

                case "2":
                    System.out.print("Enter doctor ID: ");
                    int did = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter name: ");
                    String dname = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int dage = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    String dphone = scanner.nextLine();
                    System.out.print("Enter specialization: ");
                    String spec = scanner.nextLine();
                    system.addDoctor(new Doctor(did, dname, dage, dphone, spec));
                    System.out.println("Doctor added.");
                    break;

                case "3":
                    System.out.print("Enter medication ID: ");
                    int mid = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter name: ");
                    String mname = scanner.nextLine();
                    System.out.print("Enter dose: ");
                    String mdose = scanner.nextLine();
                    System.out.print("Enter quantity in stock: ");
                    int mstock = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter expiry year (YYYY): ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter expiry month (1-12): ");
                    int month = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter expiry day: ");
                    int day = Integer.parseInt(scanner.nextLine());
                    LocalDate expiry = LocalDate.of(year, month, day);
                    system.addMedication(new Medication(mid, mname, mdose, mstock, expiry));
                    System.out.println("Medication added.");
                    break;

                case "4":
                    System.out.print("Enter prescription ID: ");
                    int prid = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter patient name: ");
                    String presPatientName = scanner.nextLine();
                    Patient presPatient = system.findPatientByName(presPatientName);
                    System.out.print("Enter doctor name: ");
                    String presDocName = scanner.nextLine();
                    Doctor presDoctor = null;
                    for (Doctor doc : system.getDoctors()) {
                        if (doc.getName().equalsIgnoreCase(presDocName)) {
                            presDoctor = doc;
                            break;
                        }
                    }
                    System.out.print("Enter medication name: ");
                    String presMedName = scanner.nextLine();
                    Medication presMed = null;
                    for (Medication med : system.getMedications()) {
                        if (med.getName().equalsIgnoreCase(presMedName)) {
                            presMed = med;
                            break;
                        }
                    }
                    if (presPatient != null && presDoctor != null && presMed != null) {
                        Prescription newPres = new Prescription(prid, presDoctor, presPatient, presMed, LocalDate.now());
                        system.addPrescription(newPres);
                        System.out.println("Prescription created.");
                    } else {
                        System.out.println("Failed to create prescription. Check that the patient, doctor, and medication exist.");
                    }
                    break;

                case "5":
                    for (Patient p : system.getPatients()) {
                        System.out.println("Patient: " + p.getName() + ", Medications: " + p.getMedications().size());
                    }
                    break;

                case "6":
                    System.out.print("Enter patient name to delete: ");
                    String delPatName = scanner.nextLine();
                    if (system.deletePatientByName(delPatName)) {
                        System.out.println("Patient deleted.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case "7":
                    System.out.print("Enter doctor name to delete: ");
                    String delDocName = scanner.nextLine();
                    if (system.deleteDoctorByName(delDocName)) {
                        System.out.println("Doctor deleted.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;

                case "8":
                    System.out.print("Enter medication name to delete: ");
                    String delMedName = scanner.nextLine();
                    if (system.deleteMedicationByName(delMedName)) {
                        System.out.println("Medication deleted.");
                    } else {
                        System.out.println("Medication not found.");
                    }
                    break;

                case "9":
                    System.out.print("Enter current patient name: ");
                    String oldPatName = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newPatName = scanner.nextLine();
                    if (system.editPatientName(oldPatName, newPatName)) {
                        System.out.println("Patient name updated.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case "10":
                    System.out.print("Enter doctor name: ");
                    String docName = scanner.nextLine();
                    System.out.print("Enter new specialization: ");
                    String newSpec = scanner.nextLine();
                    if (system.editDoctorSpecialization(docName, newSpec)) {
                        System.out.println("Doctor specialization updated.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;

                case "11":
                    System.out.print("Enter medication name: ");
                    String medName = scanner.nextLine();
                    System.out.print("Enter new stock quantity: ");
                    int newStock = Integer.parseInt(scanner.nextLine());
                    if (system.editMedicationStock(medName, newStock)) {
                        System.out.println("Medication stock updated.");
                    } else {
                        System.out.println("Medication not found.");
                    }
                    break;

                case "12":
                    System.out.print("Enter patient name to search: ");
                    String searchPat = scanner.nextLine();
                    Patient foundPat = system.findPatientByName(searchPat);
                    if (foundPat != null) {
                        System.out.println("Found patient: " + foundPat.getName());
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;

                case "13":
                    System.out.print("Enter doctor name to search: ");
                    String searchDoc = scanner.nextLine();
                    boolean foundDoctor = false;
                    for (Doctor d : system.getDoctors()) {
                        if (d.getName().equalsIgnoreCase(searchDoc)) {
                            System.out.println("Found doctor: " + d.getName() + ", Specialization: " + d.getSpecialization());
                            foundDoctor = true;
                            break;
                        }
                    }
                    if (!foundDoctor) System.out.println("Doctor not found.");
                    break;

                case "14":
                    System.out.print("Enter medication name to search: ");
                    String searchMed = scanner.nextLine();
                    boolean foundMed = false;
                    for (Medication m : system.getMedications()) {
                        if (m.getName().equalsIgnoreCase(searchMed)) {
                            System.out.println("Found medication: " + m.getName() + ", Dose: " + m.getDose() + ", Stock: " + m.getQuantityInStock());
                            foundMed = true;
                            break;
                        }
                    }
                    if (!foundMed) System.out.println("Medication not found.");
                    break;

                case "15":
                    System.out.print("Enter patient name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter doctor name: ");
                    String doctorName = scanner.nextLine();
                    if (system.assignPatientToDoctor(patientName, doctorName)) {
                        System.out.println("Patient assigned to doctor.");
                    } else {
                        System.out.println("Assignment failed. Make sure both names are correct.");
                    }
                    break;

                case "16":
                    System.out.print("Enter doctor name: ");
                    String viewDocName = scanner.nextLine();
                    for (Doctor d : system.getDoctors()) {
                        if (d.getName().equalsIgnoreCase(viewDocName)) {
                            System.out.println("Patients for Dr. " + d.getName() + ":");
                            for (Patient p : d.getPatients()) {
                                System.out.println("- " + p.getName());
                            }
                            break;
                        }
                    }
                    break;
                    
                case "17":
                    System.out.println("\n=== Expired Medications ===");
                    boolean hasExpired = false;
                    for (Medication med : system.getMedications()) {
                        if (med.getExpiryDate().isBefore(LocalDate.now())) {
                            System.out.println("- " + med.getName() + " (expired on " + med.getExpiryDate() + ")");
                            hasExpired = true;
                        }
                    }
                    if (!hasExpired) {
                        System.out.println("No expired medications found.");
                    }
                    break;

                case "18":
                    System.out.print("Enter restock amount: ");
                    int amount = Integer.parseInt(scanner.nextLine());
                    system.restockMedications(amount);
                    System.out.println("All medications restocked by " + amount + " units.");
                    break;
                
                case "19":
                    System.out.print("Enter doctor's name: ");
                    String docNameReport = scanner.nextLine();
                    system.printPrescriptionsByDoctor(docNameReport);
                    break;

                case "20":
                    System.out.print("Enter patient name: ");
                    String patientNameForSummary = scanner.nextLine();
                    system.printPatientPrescriptionSummary(patientNameForSummary);
                    break;

                case "21":
                    system.printFullSystemReport();
                    break;

                case "22":
                    running = false;
                    System.out.println("Exiting system.");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}