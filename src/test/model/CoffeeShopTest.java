package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoffeeShopTest {
    private CoffeeShop cs1;

    @BeforeEach
     public void runBefore(){
        cs1 = new CoffeeShop("La Foret", "6848 Jubilee Ave, Burnaby", 4.5);
    }

    @Test
        public void testConstructor() {
        assertEquals("La Foret", cs1.getName());
        assertEquals("6848 Jubilee Ave, Burnaby", cs1.getAddress());
        assertEquals(4.5, cs1.getRating());
    }

    @Test
        public void testEditName(){
        cs1.editName("Beard Papa's");
        assertEquals("Beard Papa's", cs1.getName());
    }

    @Test
    public void testEditAddress(){
        cs1.editAddress("5252 Imperial St");
        assertEquals("5252 Imperial St", cs1.getAddress());
    }

    @Test
    public void testEditRating(){
        cs1.editRating(3.5);
        assertEquals(3.5, cs1.getRating());
    }

}