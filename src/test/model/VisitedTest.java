package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testTrueVisited() {
        testVisit.addVisited(cs1);
        assertEquals(1, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        testVisit.visit(cs2);
        assertEquals(2, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
    }

    @Test
    public void testRepeatedVisited() {
        testVisit.addVisited(cs1);
        assertEquals(1, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        testVisit.visit(cs1);
        assertEquals(1, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
    }
    @Test
    public void testMultipleRepeatedVisited() {
        testVisit.addVisited(cs1);
        testVisit.addVisited(cs2);
        testVisit.addVisited(cs3);
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
        assertEquals(cs3, testVisit.getCoffeeShop(2));
        testVisit.visit(cs1);
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
        assertEquals(cs3, testVisit.getCoffeeShop(2));
        testVisit.visit(cs3);
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
        assertEquals(cs3, testVisit.getCoffeeShop(2));

    }

    @Test
    public void testRepeatedNameAndAddress() {
        CoffeeShop cs4 = new CoffeeShop("La Foret", "6848 Jubilee Ave, Burnaby", 3.6);
        testVisit.addVisited(cs1);
        testVisit.addVisited(cs2);
        testVisit.addVisited(cs3);
        testVisit.visit(cs4);
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
        assertEquals(cs3, testVisit.getCoffeeShop(2));
    }

    @Test
    public void testRemove() {
        testVisit.addVisited(cs1);
        testVisit.addVisited(cs2);
        testVisit.addVisited(cs3);
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
        assertEquals(cs3, testVisit.getCoffeeShop(2));
        testVisit.removeVisited(cs2);
        assertEquals(2, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs3, testVisit.getCoffeeShop(1));
    }

    }

