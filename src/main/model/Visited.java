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
//EFFECTS: Adds given coffee shop to list of visited if in tracker
    public LinkedList<CoffeeShop> visit(String cs) {
        for (CoffeeShop c : csList) {
            if (cs.equals(c.getName())) {
                addVisited(cs);
                return vlist;
            }
        }
        return vlist;
    }

    @Override
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: removes the given coffee shop from the list
    public void removeCS(String c) {
        vlist.remove(getFromName(c));
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds the given coffee shop name to list
    public void addVisited(String s) {
        vlist.add(getFromName(s));
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds the given coffee shop to list
    public void add(CoffeeShop c) {
        super.addCS(c);
    }

    @Override
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the queue list
    public int getNumItems() {
        return vlist.size();
    }

    @Override
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the indicated coffee shop with given position
    public CoffeeShop getCoffeeShop(int c) {
        return vlist.get(c);
    }




    @Override
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds the given coffee shop to list
    public void addCS(CoffeeShop s) {
        vlist.add(s);
        super.addCS(s);
    }




}


