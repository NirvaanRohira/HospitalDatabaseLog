package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

//Functions for a list of Patient data which will be made and operated with a database
public class ListOfPatientData implements Writable {
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
    public boolean addPatientData(String name, int age, int daysStayed, String treatmentReceived,
                                  String condition, int treatmentCost) {
        return patientData.offer(new PatientData(name, age, daysStayed, treatmentReceived, condition, treatmentCost));
//        this.name = name;
//        this.age = age;
//        this.daysStayed = daysStayed;
//        this.treatmentReceived = treatmentReceived;
//        this.condition = condition;
//        this.treatmentCost = treatmentCost;

    }

    //REQUIRES: A non empty list
    //MODIFIES: this
    //EFFECTS: removes a Patient Profile and returns list
    public PatientData removesPatientData(String name, int age, int daysStayed, String treatmentReceived,
                                          String condition, int treatmentCost) {
        return patientData.poll();


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

    //Effects: takes a Patient data from a list of patientdata  and returns the elements in the list of patient data
    // else it just returns the list of patient data
    public List<PatientData> viewList() {
        for (PatientData patient : patientData) {
            System.out.println(patient.toString() + " ");
        }
        return patientData;
    }

    //MODIFIES: this
    //Effects: removes all the elements in the list and returns an empty list
    public void clearList() {
        patientData.clear();
    }

    //Effects: returns the list of patient data
    public LinkedList<PatientData> getList() {
        return patientData;
    }

    //EFFECTS: returns the number of entries in the list
    public int length() {
        return patientData.size();
    }

    //EFFECTS: returns true if the list is empty, false otherwise
    public boolean isEmpty() {
        return patientData.size() == 0;
    }

    // EFFECTS: creates a json object for student list
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", nameList);
        json.put("patients", patientsToJson());
        return json;
    }

    // EFFECTS: returns students in this studentList as a JSON array
    private JSONArray patientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (PatientData s : patientData) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}
