package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrackerTest {
    private Tracker testTracker;
    private CoffeeShop cs1;
    private CoffeeShop cs2;
    private CoffeeShop cs3;
    private CoffeeShop cs4;
    private CoffeeShop cs5;

    @BeforeEach
    public void runBefore() {
        testTracker = new Tracker();
        cs1 = new CoffeeShop("La Foret", "6848 Jubilee Ave, Burnaby", 4.5);
        cs2 = new CoffeeShop("Beard Papa's", "5252 Imperial St", 2.6);
        cs3 = new CoffeeShop("Breka", "5252 Imperial St", 3.5);
        cs4 = new CoffeeShop("Breka", "5252 Imperial St", 4.7);
        cs5 = new CoffeeShop("Beard Papa's", "5252 Rupert St", 4.7);
    }

    @Test
    public void testTracker() {
        testTracker.tracker(cs1);
        assertEquals(1, testTracker.getNumItems());
        assertEquals(cs1, testTracker.getCoffeeShop(0));
        testTracker.tracker(cs2);
        assertEquals(2, testTracker.getNumItems());
        assertEquals(cs1, testTracker.getCoffeeShop(0));
        assertEquals(cs2, testTracker.getCoffeeShop(1));
    }

    @Test
    public void testRepeatedTracker() {
        testTracker.addCS(cs1);
        testTracker.addCS(cs2);
        testTracker.addCS(cs3);
        testTracker.tracker(cs4);
        assertEquals(3, testTracker.getNumItems());
        assertEquals(cs1, testTracker.getCoffeeShop(0));
        assertEquals(cs2, testTracker.getCoffeeShop(1));
        assertEquals(cs3, testTracker.getCoffeeShop(2));
    }

    @Test
    public void testPassTracker() {
        testTracker.addCS(cs1);
        testTracker.addCS(cs2);
        testTracker.addCS(cs3);
        testTracker.tracker(cs5);
        assertEquals(4, testTracker.getNumItems());
        assertEquals(cs1, testTracker.getCoffeeShop(0));
        assertEquals(cs2, testTracker.getCoffeeShop(1));
        assertEquals(cs3, testTracker.getCoffeeShop(2));
        assertEquals(cs5, testTracker.getCoffeeShop(3));
    }



}

