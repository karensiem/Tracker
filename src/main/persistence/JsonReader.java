package persistence;

import model.CoffeeShop;
import model.Tracker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads Tracker from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Tracker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Tracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTracker(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses tracker from JSON object and returns it
    private Tracker parseTracker(JSONObject jsonObject) {
        Tracker t = new Tracker();
        addCoffeeShopsFromTracker(t, jsonObject);
        return t;
    }

    // MODIFIES: t
    // EFFECTS: parses coffee shops from JSON object and adds them to tracker
    private void addCoffeeShopsFromTracker(Tracker t, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tracker");
        for (Object json : jsonArray) {
            JSONObject nextShop = (JSONObject) json;
            addCoffeeShop(t, nextShop);
        }
    }

    // MODIFIES: t
    // EFFECTS: parses coffee shop from JSON object and adds it to tracker
    private void addCoffeeShop(Tracker t, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        Double rating = jsonObject.getDouble("rating");
        Boolean visited = jsonObject.getBoolean("visited");
        CoffeeShop cs = new CoffeeShop(name, address, rating, visited);
        t.addCS(cs);
    }
}
