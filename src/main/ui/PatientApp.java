package ui;

import model.ListOfPatientData;
import model.PatientData;
import java.util.Scanner;

//Patient Application
public class PatientApp {
    private Scanner input;
    private PatientData patient;

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

            if (command.equals("q")) {
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
        System.out.println("\ta -> add person");
        System.out.println("\tq -> quit");

    }

    // MODIFIES: this
    // EFFECTS: initializes PatientData
    private void init() {
        //patient = new PatientData();
        //Inititalse patient is an issue
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddPerson();

        } else {
            System.out.println("Invalid Selection...");
        }
    }


    // MODIFIES: this
    // EFFECTS: outputs list format of inputted patient data
    private void doAddPerson() {
        System.out.print("Add the Patient's Name: ");
        String name = input.next();
        System.out.print("Add the Patient's Age: ");
        int age = Integer.parseInt(input.next());
        System.out.print("Add the Patient's Duration of Stay: ");
        int stayTime = Integer.parseInt(input.next());
        System.out.print("Add the Patient's Treatment Received: ");
        String treatmentRecieved = input.next();
        System.out.print("Add the Patient's Condition: ");
        String condition = input.next();
        System.out.print("Add the Patient's Total Cost: ");
        int cost = Integer.parseInt(input.next());
        PatientData p1 = new PatientData(name, age, stayTime, treatmentRecieved, condition, cost);
        ListOfPatientData buildlist = new ListOfPatientData("today");
        buildlist.addPatientData(p1);
        System.out.println("(" + name + " " + age
                + " " + stayTime + " " + treatmentRecieved + " " + condition + " " + cost + ")");


    }


}
