package ui;

import model.ListOfPatientData;
import model.PatientData;

import java.util.Scanner;

//Patient Application
public class PatientApp {
    private Scanner input = new Scanner(System.in);
    private PatientData patient;
    private final ListOfPatientData buildlist = new ListOfPatientData("patientList");

    public PatientApp() {
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
//    private void runPatient() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tadd -> add person");
//        System.out.println("\tnext -> view next person");
//        System.out.println("\tview -> view list of people");
//        System.out.println("\tclear -> clear list of people");
//        System.out.println("\tquit -> quit");
//        options();
//    }

//    private void options() {
//        String options = input.next();
//        while (options != "quit") {
//            if (options == "add") {
//                doAddPerson();
//                options = input.next();
//            }
//            if (options == "next") {
//                doViewNextPerson();
//                options = input.next();
//            }
//            if (options == "view") {
//                doViewListOfPersons();
//                options = input.next();
//            }
//            if (options == "clear") {
//                doClearList();
//                options = input.next();
//            }
//        }
//    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> add person");
        System.out.println("\tnext -> view next person");
        System.out.println("\tview -> view list of people");
        System.out.println("\tclear -> clear list of people");
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
        } else {

            System.out.println("Invalid Selection...");
        }
    }


    // MODIFIES: this
    // EFFECTS: outputs list format of inputted patient data
    private void doAddPerson() {
//        System.out.print("Add the Patient's Name: ");
//        String name = input.next();
//        System.out.print("Add the Patient's Age: ");
//        int age = Integer.parseInt(input.next());
//        System.out.print("Add the Patient's Duration of Stay: ");
//        int stayTime = Integer.parseInt(input.next());
//        System.out.print("Add the Patient's Treatment Received: ");
//        String treatmentRecieved = input.next();
//        System.out.print("Add the Patient's Condition: ");
//        String condition = input.next();
//        System.out.print("Add the Patient's Total Cost: ");
//        int cost = Integer.parseInt(input.next());
//        PatientData patient = new PatientData(name, age, stayTime, treatmentRecieved, condition, cost);
//        ListOfPatientData buildlist = new ListOfPatientData("today");
////        buildlist.addPatientData(patient);
////        System.out.println("(" + name + " " + age
////                + " " + stayTime + " " + treatmentRecieved + " " + condition + " " + cost + ")");
//
//        buildlist.addPatientData(name, age, stayTime, treatmentRecieved, condition, cost);

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

    public void doViewNextPerson() {
        buildlist.getNextPatientData();
        System.out.println("First Patient Cleared Successfully");

    }

    public void doViewListOfPersons() {
        for (PatientData patientData : buildlist.getList()) {
            System.out.println(patientData.getName());
        }


    }

    public void doClearList() {
        buildlist.clearList();

    }


}
