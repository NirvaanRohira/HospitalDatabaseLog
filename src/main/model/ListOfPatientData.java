package model;

import java.util.LinkedList;

public class ListOfPatientData {
    private String nameList;
    private LinkedList<PatientData> patientData;

    //EFFECTS: Creates a new List of Patient Data
    public ListOfPatientData(String nameList) {
        this.nameList = nameList;
        patientData = new LinkedList<>();

    }

    //Effects: Returns the name of the list
    public String getNameList() {
        return this.nameList;
    }

    //MODIFIES: this
    //EFFECTS: adds a Patient Profile and returns true
    public boolean addPatientData(PatientData p) {
        return patientData.add(p);

    }

    //REQUIRES: A non empty list
    //MODIFIES: this
    //EFFECTS: removes a Patient Profile and returns true
    public boolean removesPatientData(PatientData p) {
        return patientData.remove(p);



    }

    //REQUIRES: A non empty list
    //MODIFIES: this
    //Effects: removes the first entry and returns the updated list;
    public PatientData getNextPatientData() {
        if (!patientData.isEmpty()) {
            return patientData.removeFirst();
        }
        return null;
    }

    //EFFECTS: returns the number of entries in the list
    public int length() {
        return patientData.size();
    }

    //EFFECTS: returns true if the list is empty, false otherwise
    public boolean isEmpty() {
        return patientData.size() == 0;
    }


}
