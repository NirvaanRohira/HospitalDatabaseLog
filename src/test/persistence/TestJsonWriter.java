package persistence;

import model.ListOfPatientData;
import model.PatientData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonWriter extends TestJson{

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfPatientData list = new ListOfPatientData("Patient List");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPatientData() {
        try {
            ListOfPatientData list = new ListOfPatientData("Patient List");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPatientList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPatientList.json");
            list = reader.read();
            assertEquals("Patient List", list.getNameList());
            assertEquals(0, list.length());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralPatientData() {
        try {
            ListOfPatientData list = new ListOfPatientData("Patient List");
            list.addPatientData("DDP", 20,100, "antibiotics",
                    "aids", 100);
            list.addPatientData("Casey", 20,50,"Tylenol",
                    "Headache",20);
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralPatientList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPatientList.json");
            list = reader.read();
            assertEquals("Patient List", list.getNameList());
            List<PatientData> patients = list.getList();
            assertEquals(2, patients.size());
            checkPatient("DDP", 20,100, "antibiotics", "aids", 100,
                    patients.get(0));
            checkPatient("Casey", 20,50,"Tylenol","Headache",20,
                    patients.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
