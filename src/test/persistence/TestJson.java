package persistence;


import model.PatientData;

import static org.junit.jupiter.api.Assertions.assertEquals;


//Tests for checking the patient attributes
public class TestJson {
    protected void checkPatient(String name, int age, int dayS, String tr, String cond, int tc,
                                PatientData patient) {
        assertEquals(name, patient.getName());
        assertEquals(age, patient.getAge());
        assertEquals(dayS, patient.getDaysStayed());
        assertEquals(tr, patient.getTreatmentReceived());
        assertEquals(cond, patient.getCondition());
        assertEquals(tc, patient.getTreatmentCost());
    }
}
