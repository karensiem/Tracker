package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

public class Tracker implements Writable {
    protected LinkedList<CoffeeShop> csList;

    public Tracker() {
        this.csList = new LinkedList<>();
    }

//MODIFIES: this.
//EFFECTS: Adds given coffee shop to tracker if not already there
    public LinkedList<CoffeeShop> tracker(CoffeeShop cs) {
        for (CoffeeShop c : csList) {
            if (c.getName().equals(cs.getName()) && c.getAddress().equals(cs.getAddress())) {
                return csList;
            }
        }
        addCS(cs);
        return csList;
    }

    //EFFECTS: removes the given coffee shop in the list
    public void removeCS(String c) {
        CoffeeShop s = getFromName(c);
        csList.remove(s);
        EventLog.getInstance().logEvent(new Event("Coffee shop: " + s.getName() + " " + s.getAddress()
                + " " + s.getRating() + " " + s.getVisited() + ". was removed"));
    }

    //EFFECTS: return true if cs is in tracker and false otherwise
    public Boolean inTracker(String c) {
        for (CoffeeShop cs : csList) {
            if (c.equals(cs.getName())) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns the coffee shop with the same name
    public CoffeeShop getFromName(String name) {
        for (CoffeeShop cs : csList) {
            if (cs.getName().equals(name)) {
                return cs;
            }
        }
        return null;
    }

    //EFFECTS: returns the amount of current items in the list
    public int getNumItems() {
        return csList.size();
    }


    //EFFECTS: returns the indicated coffee shop with given position
    public CoffeeShop getCoffeeShop(int i) {
        return csList.get(i);
    }

    //EFFECTS: adds the given coffee shop to list
    public void addCS(CoffeeShop s) {
        csList.add(s);
        EventLog.getInstance().logEvent(new Event("Coffee shop: " + s.getName() + " " + s.getAddress()
                + " " + s.getRating() + " " + s.getVisited() + ". was added"));
    }

    //EFFECTS: returns csList from tracker
    public LinkedList<CoffeeShop> getCSList() {
        return csList;
    }

    // equal/hash code the same cslist or get list frmo tracker

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tracker", coffeeShopToJson());
        return json;
    }

    // EFFECTS: returns coffee shops in this tracker as a JSON array
    private JSONArray coffeeShopToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CoffeeShop cs : csList) {
            jsonArray.put(cs.toJson());
        }

        return jsonArray;
    }
}
