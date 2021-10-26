package ui;

import model.ListOfPatientData;
import model.PatientData;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Patient Application
public class PatientApp {
    private Scanner input = new Scanner(System.in);
    private PatientData patient;
    private ListOfPatientData buildlist = new ListOfPatientData("patientList");
    private static final String JSON_STORE = "./data/patients.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    public PatientApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPatient();
    }

    //MODIFIES: this
    //EFFECTS: Takes and processes user inputs
    private void runPatient() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");

    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> add person");
        System.out.println("\tnext -> view next person");
        System.out.println("\tview -> view list of people");
        System.out.println("\tclear -> clear list of people");
        System.out.println("\tsave -> save given list of people");
        System.out.println("\tload -> load saved list of people");
        System.out.println("\tquit -> quit");

    }

    // MODIFIES: this
    // EFFECTS: initializes PatientData
    private void init() {
//        patient = new PatientData(patient.getName(), patient.getAge(), patient.getDaysStayed(),
//                patient.getTreatmentReceived(), patient.getCondition(), patient.getTreatmentCost());
//        buildlist = new ListOfPatientData("patientList");
        //Inititalse patient is an issue
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            doAddPerson();

        } else if (command.equals("next")) {
            doViewNextPerson();
        } else if (command.equals("view")) {
            doViewListOfPersons();
        } else if (command.equals("clear")) {
            doClearList();
        } else if (command.equals("save")) {
            dosavePatientData();
        } else if (command.equals("load")) {
            doLoadPatientData();
        } else {

            System.out.println("Invalid Selection...");
        }
    }


    // MODIFIES: this
    // EFFECTS: outputs list format of inputted patient data
    private void doAddPerson() {
        System.out.println("Please enter the Patient's Name: ");
        String name = input.next();
        System.out.println("Please enter the Patient's Age: ");
        int age = input.nextInt();
        System.out.println("Please enter the Patient's Duration of Stay: ");
        int stayTime = input.nextInt();
        System.out.println("Please enter the Treatment Given to the Patient: ");
        String treatmentRecieved = input.next();
        System.out.println("Please enter the Patient's Condition: ");
        String condition = input.next();
        System.out.println("Please enter the Patient's Total Stay Cost: ");
        int cost = input.nextInt();

        buildlist.addPatientData(name, age, stayTime, treatmentRecieved, condition, cost);
        System.out.println("Patient Added Successfully");


    }

    //MODIFIES: this
    //Effects: Clears the first patient and outputs the data of the rest of the patients
    public void doViewNextPerson() {
        buildlist.getNextPatientData();
        System.out.println("First Patient Cleared Successfully");

    }

    //EFFECTS: Outputs the current list of patients as is
    public void doViewListOfPersons() {
        for (PatientData patientData : buildlist.getList()) {
            System.out.println(patientData.getName() + " " + "Cost = " + patientData.getTreatmentCost() + "CAD");
        }


    }

    //MODIFIES: this
    //EFFECTS: deletes all elements from the list and turns it into an empty list
    public void doClearList() {
        buildlist.clearList();
        System.out.println("All Patient Data has been cleared out successfully");

    }

    // EFFECTS: saves the patientData to file
    private void dosavePatientData() {
        try {
            jsonWriter.open();
            jsonWriter.write(buildlist);
            jsonWriter.close();
            System.out.println("Saved " + buildlist.getNameList() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads patientData from file
    private void doLoadPatientData() {
        try {
            buildlist = jsonReader.read();
            System.out.println("Loaded " + buildlist.getNameList() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
