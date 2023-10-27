package Persistence;

import model.Tracker;
import model.Filter;
import model.CoffeeShop;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Tracker t = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTracker() {
        //testing an existing empty tracker
        JsonReader reader = new JsonReader("./data/testReaderEmptyTracker.json");
        try {
            Tracker tracker = reader.read();
            assertEquals(0, tracker.getNumItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTracker() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTracker.json");
        try {
            Tracker tracker = reader.read();
            assertEquals(1, tracker.getNumItems());
            checkCoffeeShop("La Foret",
                    "6848 Jubilee Ave, Burnaby", 4.5, true, tracker.getCoffeeShop(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
