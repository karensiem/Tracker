package Persistence;

import model.Tracker;
import model.CoffeeShop;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Tracker t1 = new Tracker();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTracker() {
        try {
            Tracker t2 = new Tracker();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTracker.json");
            writer.open();
            writer.write(t2);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTracker.json");
            t2 = reader.read();
            assertEquals(0, t2.getNumItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTracker() {
        try {
            Tracker t3 = new Tracker();
            t3.addCS(new CoffeeShop("La Foret",
                    "6848 Jubilee Ave, Burnaby", 4.5, true));
            t3.addCS(new CoffeeShop("Beard Papa's",
                    "5252 Imperial St", 2.6, false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTracker.json");
            writer.open();
            writer.write(t3);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTracker.json");
            t3 = reader.read();
            assertEquals(2, t3.getNumItems());
            checkCoffeeShop("La Foret",
                    "6848 Jubilee Ave, Burnaby", 4.5, true, t3.getCoffeeShop(0));
            checkCoffeeShop("Beard Papa's",
                    "5252 Imperial St", 2.6, false, t3.getCoffeeShop(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
