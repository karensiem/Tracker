package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tracker {
    protected LinkedList<CoffeeShop> csList;

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
    //EFFECTS: removes the given coffee shop in the list
    public void removeCS(CoffeeShop c) {
        csList.remove(c);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the list
    public int getNumItems() {
        return csList.size();
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the list
    public CoffeeShop getCoffeeShop(int c) {
        return csList.get(c);
    }

}
