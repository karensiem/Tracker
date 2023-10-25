package model;

import java.util.LinkedList;


public class Visited extends Tracker { //coffee shops I have visited
    private LinkedList<CoffeeShop> vlist;

//REQUIRES:
//MODIFIES:
//EFFECTS: constructs a list of visited coffee shops.
    public Visited() {
        this.vlist = new LinkedList<>();
    }


//MODIFIES: this.
//EFFECTS: Adds given coffee shop to list of visited if not repeated
    public Boolean visit(CoffeeShop cs) {
        for (CoffeeShop c : csList) {
            if (c.getName() == cs.getName() && c.getAddress() == cs.getAddress()) {
                addVisited(cs);
                return true;
            }
        }
        return false;
    }


    public void addVisited(CoffeeShop s) {
        vlist.add(s);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the queue list
    public int getNumItems() {
        return vlist.size();
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the queue list
    public CoffeeShop getCoffeeShop(int c) {
        return vlist.get(c);
    }

    //REQUIRES: The given coffee shop must be in visited list
    //MODIFIES:
    //EFFECTS: removes the given coffee shop from the list
    public void removeVisited(CoffeeShop v) {
        vlist.remove(v);
    }

}


