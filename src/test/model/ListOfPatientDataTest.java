package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests for the different functions regarding a list of patient data
public class ListOfPatientDataTest {

    ListOfPatientData newlist;
    PatientData p1;
    PatientData p2;
    PatientData p3;
    PatientData p4;



    @BeforeEach
    void runBefore() {
        newlist = new ListOfPatientData("today");
        p1 = new PatientData("Nirvaan", 19, 2, "Stitches",
                "Deep Cuts", 2);
        p2 = new PatientData("Emma", 19, 4, "Cinarest",
                "Strep Throat", 15);
        p3 = new PatientData("Natalia", 20, 2, "Rhinocin",
                "Acne", 69);
        p4 = new PatientData("John", 12, 3, "Crocin",
                "Fever", 100);
    }

    @Test
    void testGetNameList() {

        assertEquals("today", newlist.getNameList());
    }

    @Test
    void testAddOnePatientData() {
        assertTrue(newlist.addPatientData(p4));

    }

    @Test
    void testAddManyPatientData() {
        newlist.addPatientData(p2);
        newlist.addPatientData(p3);
        assertEquals(2, newlist.length());


    }

    @Test
    void testRemovesOnePatientData() {
        newlist.addPatientData(p1);
        newlist.removesPatientData(p1);
        assertEquals(0, newlist.length());


    }

    @Test
    void testRemovesManyPatientData() {
        newlist.addPatientData(p1);
        newlist.addPatientData(p2);
        newlist.removesPatientData(p1);
        newlist.removesPatientData(p2);
        assertEquals(0, newlist.length());


    }


    @Test
    void testGetNextPatientsDataNonEmpty() {
        newlist.addPatientData(p1);
        newlist.addPatientData(p2);
        newlist.addPatientData(p3);
        newlist.getNextPatientData();
        assertEquals(2, newlist.length());


    }

    @Test
    void testGetNextPatientDataEmpty(){
        ListOfPatientData emptylist = new ListOfPatientData("empty");
        assertNull(emptylist.getNextPatientData());

    }

    @Test
    void testLength() {
        newlist.addPatientData(p1);
        newlist.addPatientData(p2);
        newlist.addPatientData(p2);
        assertEquals(3, newlist.length());


    }

    @Test
    void testIsEmptyTrue() {
        ListOfPatientData emptylist = new ListOfPatientData("empty");
        assertTrue(emptylist.isEmpty());


    }

    @Test
    void testIsEmptyFalse() {
        newlist.addPatientData(p1);
        newlist.addPatientData(p2);
        assertFalse(newlist.isEmpty());

    }
}

