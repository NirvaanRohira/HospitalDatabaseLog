package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Tests for the different setters and getters in the Patient Data CLass
public class PatientDataTest {
    PatientData p1 = new PatientData("Nirvaan", 19, 2, "Stitches",
            "Deep Cuts", 2);

    @Test
    void testGetName() {
        assertEquals("Nirvaan", p1.getName());
    }

    @Test
    void testGetAge() {
        assertEquals(19, p1.getAge());
    }

    @Test
    void testGetDaysStayed() {
        assertEquals(2, p1.getDaysStayed());
    }

    @Test
    void testGetTreatmentRecieved() {
        assertEquals("Stitches", p1.getTreatmentReceived());
    }

    @Test
    void testGetCondition() {
        assertEquals("Deep Cuts", p1.getCondition());
    }

    @Test
    void testGetTreatmentCost() {
        assertEquals(2, p1.getTreatmentCost());

    }

}