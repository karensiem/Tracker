package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

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
        cs1 = new CoffeeShop("La Foret", "6848 Jubilee Ave, Burnaby", 4.5, true);
        cs2 = new CoffeeShop("Beard Papa's", "5252 Imperial St", 2.6, false);
        cs3 = new CoffeeShop("Breka", "5252 Imperial St", 3.5, true);
        cs4 = new CoffeeShop("Breka", "5252 Imperial St", 4.7, false);
        cs5 = new CoffeeShop("Beard Papa's", "5252 Rupert St", 4.7, true);
    }

    @Test
    public void testConstructor(){
        testTracker.addCS(cs1);
        testTracker.addCS(cs2);
        testTracker.addCS(cs3);
        assertEquals(cs2, testTracker.getCoffeeShop(1));
        assertEquals(cs1, testTracker.getCoffeeShop(0));
        assertEquals(3, testTracker.getNumItems());
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

    @Test
    public void testGetFromName() {
        testTracker.addCS(cs1);
        testTracker.addCS(cs2);
        testTracker.addCS(cs3);
        testTracker.tracker(cs5);
        assertEquals(cs1, testTracker.getFromName("La Foret"));
        assertEquals(cs3, testTracker.getFromName("Breka"));
    }

    @Test
    public void testFailGetFromName() {
        testTracker.addCS(cs1);
        testTracker.addCS(cs2);
        testTracker.addCS(cs3);
        testTracker.tracker(cs5);
        assertNull(testTracker.getFromName("Rice"));
    }

    @Test
    public void testRemoveCS() {
        testTracker.addCS(cs1);
        testTracker.addCS(cs2);
        testTracker.addCS(cs3);
        assertEquals(3, testTracker.getNumItems());
        testTracker.removeCS("Breka");
        assertEquals(2, testTracker.getNumItems());
        assertEquals(cs1, testTracker.getCoffeeShop(0));
        assertEquals(cs2, testTracker.getCoffeeShop(1));
        testTracker.removeCS("La Garet");
        assertEquals(2, testTracker.getNumItems());
        assertEquals(cs1, testTracker.getCoffeeShop(0));
        assertEquals(cs2, testTracker.getCoffeeShop(1));

    }

    @Test
    public void testInTracker() {
        testTracker.addCS(cs1);
        testTracker.addCS(cs2);
        testTracker.addCS(cs3);
        assertTrue(testTracker.inTracker("Breka"));
    }

    @Test
    public void testNotInTracker() {
        assertFalse(testTracker.inTracker("Breka"));
        testTracker.addCS(cs1);
        testTracker.addCS(cs2);
        testTracker.addCS(cs3);
        assertFalse(testTracker.inTracker("mario"));
    }

    @Test
    public void testGetCSList() {
        testTracker.addCS(cs1);
        testTracker.addCS(cs2);
        testTracker.addCS(cs3);
        assertEquals(3, testTracker.getCSList().size());
    }


}

