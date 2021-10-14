package model;

public class PatientData {
    private String name; //First Name of the Patient
    private int age; //Age of the Patient
    private int daysStayed; //Number of Days Stayed
    private String treatmentReceived;
    private String condition;
    private int treatmentCost;
    private final int costPerDay = 100; //Cost of Stay Per Day in CAD


    public PatientData(String name, int age, int daysStayed, String treatmentReceived, String condition,
                       int treatmentCost) {
        this.name = name;
        this.age = age;
        this.daysStayed = daysStayed;
        this.treatmentReceived = treatmentReceived;
        this.condition = condition;
        this.treatmentCost = treatmentCost;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getDaysStayed() {
        return this.daysStayed;
    }

    public String getTreatmentReceived() {
        return this.treatmentReceived;
    }

    public String getCondition() {
        return this.condition;
    }

    public int getTreatmentCost() {
        return this.treatmentCost;
    }
}
