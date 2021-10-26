package persistence;

import model.ListOfPatientData;
import model.PatientData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//CITATION: JsonSerializationDemo
//Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private final String source;

    //EFFECTS: Creates a reader that can read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads a workroom from the given file and returns it;
    // throws IOException if an error occurs reading data from the file
    public ListOfPatientData read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePatientData(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses workroom for JSON object and returns it
    private ListOfPatientData parsePatientData(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ListOfPatientData pdl = new ListOfPatientData(name);
        addPatients(pdl, jsonObject);
        return pdl;
    }


    //MODIFIES: pdl
    //EFFECTS: parses thingies from JSON object and adds them to the list of patient data
    private void addPatients(ListOfPatientData pdl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("patients");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addPatient(pdl, nextThingy);
        }

    }

    // MODIFIES: pd
    // EFFECTS: parses thingy from JSON object  this.name = name;
    //        this.age = age;
    //        this.daysStayed = daysStayed;
    //        this.treatmentReceived = treatmentReceived;
    //        this.condition = condition;
    //        this.treatmentCost = treatmentCost; and adds it to workroom
    private void addPatient(ListOfPatientData pdl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        int daysStayed = jsonObject.getInt("daysStayed");
        String treatmentRecieved = jsonObject.getString("treatmentRecieved");
        String condition = jsonObject.getString("condition");
        int treatmentCost = jsonObject.getInt("treatmentCost");

        PatientData pd = new PatientData(name, age, daysStayed, treatmentRecieved, condition, treatmentCost);
        pdl.addPatientData(pd.getName(), pd.getAge(), pd.getDaysStayed(), pd.getTreatmentReceived(),
                pd.getCondition(), pd.getTreatmentCost());
    }


}
