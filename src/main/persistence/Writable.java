package persistence;

import org.json.JSONObject;
//JSON Object Return inteface

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
