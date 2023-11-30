package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilterTest {
    private Filter testfilter;
    private Tracker t1;
    private CoffeeShop cs1;
    private CoffeeShop cs2;
    private CoffeeShop cs3;

    @BeforeEach
    public void runBefore(){
        testfilter = new Filter();
        cs1 = new CoffeeShop("La Foret", "6848 Jubilee Ave, Burnaby", 4.5, true);
        cs2 = new CoffeeShop("Beard Papa's", "5252 Imperial St", 2.6, false);
        cs3 = new CoffeeShop("Breka", "5252 Imperial St", 3.5, true);
        t1 = new Tracker();
    }

    @Test
    public void testConstructor(){
        assertTrue(testfilter.isEmpty());
        assertEquals(0, testfilter.getNumItems());
        testfilter.addCS(cs1);
        assertFalse(testfilter.isEmpty());
        assertEquals(1, testfilter.getNumItems());
    }

    @Test
    public void testGetCoffeeShop(){
        t1.addCS(cs1);
        t1.addCS(cs2);
        t1.addCS(cs3);
        testfilter.filterHigh(t1);
        assertEquals(2, testfilter.getNumItems());
        assertEquals(cs1, testfilter.getCoffeeShop(0));
        assertEquals(cs3, testfilter.getCoffeeShop(1));
        assertEquals(2, testfilter.getHighList().size());
    }

    @Test
    public void testFilterHigh() {
        t1.addCS(cs1);
        t1.addCS(cs2);
        t1.addCS(cs3);
        testfilter.filterHigh(t1);
        assertEquals(2, testfilter.getNumItems());
    }
}
