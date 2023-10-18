package model;

import java.util.LinkedList;


public class Visited { //coffee shops I have visited
    private LinkedList<CoffeeShop> vList;

//REQUIRES:
//MODIFIES:
//EFFECTS: constructs a list of visited coffee shops.
    public Visited() {
        this.vList = new LinkedList<>();
    }


//MODIFIES: this.
//EFFECTS: Adds given coffee shop to list of visited if not repeated
    public LinkedList<CoffeeShop> visited(CoffeeShop cs) {
        for (CoffeeShop c : vList) {
            if (c.getName() == cs.getName() && c.getAddress() == cs.getAddress()) {
                return vList;
            }
        }
        addVisited(cs);
        return vList;
    }

    public void addVisited(CoffeeShop s) {
        vList.add(s);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the queue list
    public int getNumItems() {
        return vList.size();
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the queue list
    public CoffeeShop getCoffeeShop(int c) {
        return vList.get(c);
    }
}


