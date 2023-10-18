package model;

import java.util.LinkedList;

public class Tracker {
    private LinkedList<CoffeeShop> csList;

    public Tracker() {
        this.csList = new LinkedList<>();
    }

//MODIFIES: this.
//EFFECTS: Adds given coffee shop to tracker if not already there
    public LinkedList<CoffeeShop> tracker(CoffeeShop cs) {
        for (CoffeeShop c : csList) {
            if (c.getName() == cs.getName() && c.getAddress() == cs.getAddress()) {
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
