package model;


import java.util.LinkedList;
import java.util.List;

public class Filter extends Tracker {
    private LinkedList<CoffeeShop> highList;

    public Filter() {
        highList = new LinkedList<>();
    }

    //REQUIRES: tracker with at least 1 coffee shop
    //MODIFIES: this
    //EFFECTS: filters out the coffee shops with rating higher or equal to 3.5
    public LinkedList<CoffeeShop> filterHigh(Tracker t) {
        for (CoffeeShop c : t.csList) {
            if (c.getRating() >= 3.5) {
                addCS(c);
            }
        }
        return highList;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns true if the list is empty
    public Boolean isEmpty() {
        return highList.isEmpty();
    }

    @Override
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: adds the given coffee shop to high rating list
    public void addCS(CoffeeShop s) {
        highList.add(s);
    }

    @Override
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the amount of current items in the hight rating list
    public int getNumItems() {
        return highList.size();
    }

    @Override
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the coffee shop of given position
    public CoffeeShop getCoffeeShop(int i) {
        return highList.get(i);
    }

}
