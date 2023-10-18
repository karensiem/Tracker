package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VisitedTest {
    private Visited testVisit;
    private CoffeeShop cs1;
    private CoffeeShop cs2;
    private CoffeeShop cs3;

    @BeforeEach
    public void runBefore(){
        testVisit = new Visited();
        cs1 = new CoffeeShop("La Foret", "6848 Jubilee Ave, Burnaby", 4.5);
        cs2 = new CoffeeShop("Beard Papa's", "5252 Imperial St", 2.6);
        cs3 = new CoffeeShop("Breka", "5252 Imperial St", 2.6);
    }

    @Test
    public void testTrueVisited(){
        testVisit.addCS(cs1);
        assertEquals(1, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        testVisit.visited(cs2);
        assertEquals(2, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
    }

    @Test
    public void testRepeatedVisited(){
        testVisit.addCS(cs1);
        assertEquals(1, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        testVisit.visited(cs1);
        assertEquals(1, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
    }
    @Test
    public void testMultipleRepeatedVisited(){
        testVisit.addCS(cs1);
        testVisit.addCS(cs2);
        testVisit.addCS(cs3);
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
        assertEquals(cs3, testVisit.getCoffeeShop(2));
        testVisit.visited(cs1);
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
        assertEquals(cs3, testVisit.getCoffeeShop(2));
        testVisit.visited(cs3);
        assertEquals(3, testVisit.getNumItems());
        assertEquals(cs1, testVisit.getCoffeeShop(0));
        assertEquals(cs2, testVisit.getCoffeeShop(1));
        assertEquals(cs3, testVisit.getCoffeeShop(2));
    }
}

