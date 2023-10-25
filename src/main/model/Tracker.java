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


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: removes the given coffee shop in the list
    public void removeCS(String c) {
        csList.remove(getFromName(c));
    }
//method to check if exist or return boolean
// print out full list in tracker

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: return true if cs is in tracker and false otherwise
    public Boolean inTracker(String c) {
        for (CoffeeShop cs : csList) {
            String name = cs.getName();
            if (c == name) {
                return true;
            }
        }
        return false;
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the coffee shop with the same name
    public CoffeeShop getFromName(String name) {
        for (CoffeeShop cs : csList) {
            if (cs.getName().equals(name)) {
                return cs;
            }
        }
        return null;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the list
    public int getNumItems() {
        return csList.size();
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the indicated coffee shop with given position
    public CoffeeShop getCoffeeShop(int i) {
        return csList.get(i);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds the given coffee shop to list
    public void addCS(CoffeeShop s) {
        csList.add(s);
    }

}
