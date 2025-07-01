Pharmacy Management System. 

This is a console-based Pharmacy Management System developed using Java and Object-Oriented Programming principle. It gives pharmacy staff access to manage patients, doctors, medications, and prescriptions for them.
Main features include assigning prescriptions to paitents, managing inventory, tracking expiary dates, and generating reports for doctors.

Overview of features:

Add/edit/delete/search for patients, doctors, and medications

Assign patients to doctors

Accept and track prescriptions

Check for expired medications

Restock medications

Generate full system reports

Generate prescription summary for a specific patient (past year)

View prescriptions by doctor


Programs classes:

Person: Superclass for shared atributes between patients and their doctors.

Patient: Hold lists of prescriptions from Person.

Doctor: Has specialization and manages a list of patients from Person.

Prescription: Connects a Doctor, Patient, and Medication with issue and expiry dates.

MedicationTrckingSystem: Central manager for all entities. Handles CRUD operations, reporting, searching, and linking data.

Main: Provides the interactive menu-based CLI for users to operate the system.



In order to run the project, you must do the following steps:

Step 1: Enter this commmand to compile all java files through terminal:  javac models/*.java Main.java

Step 2: Run the application through this command in the terminal: java Main


To get this project, you must download it from its directory at the following address: https://github.com/ChrisKeyin/JavaMid
Click the green Code button and download the ZIP file.


Directory documentation:

project-root/
│__ BIN
│__ Src ├── models/
│       ├── Person.java
│       ├── Patient.java
│       ├── Doctor.java
│       ├── Medication.java
│       ├── Prescription.java
│       └── MedicationTrackingSystem.java
│
├── Main.java
└── README.md
