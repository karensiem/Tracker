package model;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;



public class Visited { //coffee shops I have visited
    private LinkedList<CoffeeShop> csList;

//REQUIRES:
//MODIFIES:
//EFFECTS: constructs a list of visited coffee shops.
    public Visited() {
        this.csList = new LinkedList<>();
    }


//MODIFIES: this.
//EFFECTS: Adds given coffee shop to list of visited if not repeated
    public LinkedList<CoffeeShop> visited(CoffeeShop cs) {
        for (CoffeeShop c : csList) {
            if (c.getName() == cs.getName()) {
                return csList;
            }
        }
        addCS(cs);
        return csList;
    }

    public void addCS(CoffeeShop s) {
        csList.add(s);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the queue list
    public int getNumItems() {
        return csList.size();
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the queue list
    public CoffeeShop getCoffeeShop(int c) {
        return csList.get(c);
    }
}


