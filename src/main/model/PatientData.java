package model;

import org.json.JSONObject;
import persistence.Writable;

//Specific data related to creating a patient profile for each person in the database
public class PatientData implements Writable {
    private String name; //First Name of the Patient
    private int age; //Age of the Patient
    private int daysStayed; //Number of Days Stayed
    private String treatmentReceived;
    private String condition;
    private int treatmentCost;
    private final int costPerDay = 100; //Cost of Stay Per Day in CAD


    //EFFECTS: Creates a patient with given data (name, age, etc)
    public PatientData(String name, int age, int daysStayed, String treatmentReceived, String condition,
                       int treatmentCost) {
        this.name = name;
        this.age = age;
        this.daysStayed = daysStayed;
        this.treatmentReceived = treatmentReceived;
        this.condition = condition;
        this.treatmentCost = treatmentCost;
    }


    //EFFECTS: Gets the name of a given patient
    public String getName() {
        return this.name;
    }

    //EFFECTS: Gets the name of a given patient
    public int getAge() {
        return this.age;
    }

    //EFFECTS: Gets the name of a given patient
    public int getDaysStayed() {
        return this.daysStayed;
    }

    //EFFECTS: Gets the name of a given patient
    public String getTreatmentReceived() {
        return this.treatmentReceived;
    }

    //EFFECTS: Gets the name of a given patient
    public String getCondition() {
        return this.condition;
    }

    //EFFECTS: Gets the name of a given patient
    public int getTreatmentCost() {
        return this.treatmentCost;
    }

    // EFFECTS: Creates a json object of student profile
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("daysStayed", daysStayed);
        json.put("treatmentRecieved", treatmentReceived);
        json.put("condition", condition);
        json.put("treatmentCost", treatmentCost);
        return json;
    }
}

