package persistence;

import model.PatientData;
import model.ListOfPatientData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader extends TestJson{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfPatientData pd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPatientData() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPatientList.json");
        try {
            ListOfPatientData pd = reader.read();
            assertEquals("Patient List", pd.getNameList());
            assertEquals(0, pd.length());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPatientData() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPatientList.json");
        try {
            ListOfPatientData pd = reader.read();
            assertEquals("Patient List", pd.getNameList());
            List<PatientData> patients = pd.getList();
            assertEquals(2, patients.size());
            checkPatient("DDP", 20,100, "antibiotics", "aids", 100,
                    patients.get(0));
            checkPatient("Casey", 20,50,"Tylenol","Headache",20,
                    patients.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

