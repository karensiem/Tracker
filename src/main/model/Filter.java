package model;


import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Filter extends Tracker {
    private LinkedList<CoffeeShop> highList;

    public Filter() {
        super();
        highList = new LinkedList<>();
    }

    //REQUIRES: tracker with at least 1 coffee shop
    //MODIFIES: this
    //EFFECTS: filters out the coffee shops with rating higher or equal to 3.5
    public void filterHigh(Tracker t) {
        for (CoffeeShop c : t.csList) {
            if (c.getRating() >= 3.5) {
                addCS(c);
                EventLog.getInstance().logEvent(new Event("Tracker filtered"));
            }
        }
    }


    //EFFECTS: returns true if the list is empty
    public Boolean isEmpty() {
        return highList.isEmpty();
    }

    public LinkedList<CoffeeShop> getHighList() {
        return highList;
    }

    @Override
    //EFFECTS: adds the given coffee shop to high rating list
    public void addCS(CoffeeShop s) {
        highList.add(s);
    }

    @Override
    //EFFECTS: returns the amount of current items in the high rating list
    public int getNumItems() {
        return highList.size();
    }

    @Override
    //EFFECTS: returns the coffee shop of given position
    public CoffeeShop getCoffeeShop(int i) {
        return highList.get(i);
    }

}
