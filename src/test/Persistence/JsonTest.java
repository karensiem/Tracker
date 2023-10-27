package Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import model.CoffeeShop;

public class JsonTest {
    //EFECTS: checks if the coffee shop has the same format as the coffee shop in tracker
    protected void checkCoffeeShop(String name, String address, double rating, Boolean visited, CoffeeShop cs) {
        assertEquals(name, cs.getName());
        assertEquals(address, cs.getAddress());
        assertEquals(rating, cs.getRating());
        assertEquals(visited, cs.getVisited());
    }
}
