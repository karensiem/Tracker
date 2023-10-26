package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisitedTest {
    private Visited testVisit;
    private CoffeeShop cs1;
    private CoffeeShop cs2;
    private CoffeeShop cs3;

    @BeforeEach
    public void runBefore() {
        testVisit = new Visited();
        cs1 = new CoffeeShop("La Foret", "6848 Jubilee Ave, Burnaby", 4.5);
        cs2 = new CoffeeShop("Beard Papa's", "5252 Imperial St", 2.6);
        cs3 = new CoffeeShop("Breka", "5252 Imperial St", 3.5);
    }

    @Test
    public void testConstructors() {
        assertEquals(0, testVisit.getNumItems());
        testVisit.addCS(cs1);
        testVisit.addCS(cs2);
        assertEquals(2, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
        testVisit.add(cs3);
        assertEquals(2, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
    }

    @Test
    public void testGetFromName() {
        testVisit.addCS(cs1);
        testVisit.addCS(cs2);
        testVisit.addCS(cs3);
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getFromName("La Foret"));
        assertEquals(cs2, testVisit.getFromName(cs2.getName()));
    }

    @Test
    public void testGetFromFailName() {
        testVisit.add(cs1);
        testVisit.addCS(cs2);
        testVisit.addCS(cs3);
        assertEquals(2, testVisit.getNumItems());
        assertNull(testVisit.getFromName("La Fore"));
        assertEquals(cs1, testVisit.getFromName(cs1.getName()));
        testVisit.addVisited(cs1.getName());
        assertEquals(3, testVisit.getNumItems());
    }

    @Test
    public void testRemoveCS() {
        testVisit.addCS(cs1);
        testVisit.addCS(cs2);
        testVisit.addCS(cs3);
        assertEquals(3, testVisit.getNumItems());
        testVisit.removeCS(cs3.getName());
        assertEquals(2, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
    }

    @Test
    public void testRemoveFailCS() {
        testVisit.addCS(cs1);
        testVisit.addCS(cs2);
        testVisit.addCS(cs3);
        assertEquals(3, testVisit.getNumItems());
        testVisit.removeCS("Something Sweet");
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
        assertEquals(cs3, testVisit.getCoffeeShop(2));
    }

    @Test
    public void testAddVisited() {
        testVisit.add(cs1);
        testVisit.addCS(cs2);
        testVisit.addCS(cs3);
        assertEquals(2, testVisit.getNumItems());
        testVisit.addVisited(cs1.getName());
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs2, testVisit.getCoffeeShop(0));
        assertEquals(cs3, testVisit.getCoffeeShop(1));
        assertEquals(cs1, testVisit.getCoffeeShop(2));
    }

    @Test
    public void testVisit() {
        testVisit.add(cs1);
        testVisit.add(cs2);
        testVisit.addCS(cs3);
        assertEquals(1, testVisit.getNumItems());
        testVisit.visit(cs1.getName());
        assertEquals(2, testVisit.getNumItems());
        assertEquals(cs3, testVisit.getCoffeeShop(0));
        assertEquals(cs1, testVisit.getCoffeeShop(1));
    }

    @Test
    public void testFailVisit() {
        testVisit.add(cs1);
        testVisit.addCS(cs3);
        assertEquals(1, testVisit.getNumItems());
        testVisit.visit(cs2.getName());
        assertEquals(1, testVisit.getNumItems());
        assertEquals(cs3, testVisit.getCoffeeShop(0));
    }

}

