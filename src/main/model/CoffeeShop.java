package model;

import org.json.JSONObject;
import persistence.Writable;

public class CoffeeShop implements Writable {
    private String name;        //name of the coffee shop
    private String address;     //address of the coffee shop
    private Double rating;      // the rating of the coffee shop out of 5
    private Boolean visited;

    //REQUIRES: rating must be between [0, 5]
    //EFFECTS: Constructs a coffee shop with the given name, address, and rating
    public CoffeeShop(String name, String address, Double rating, Boolean visited) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.visited = visited;
    }

    //EFFECTS: Gets name of the coffee shop
    public String getName() {
        return name;
    }

    //EFFECTS: Gets Address of the coffee shop
    public String getAddress() {
        return address;
    }

    //EFFECTS: Gets rating of the coffee shop
    public Double getRating() {
        return rating;
    }

    //EFFECTS: Gets visited of the coffee shop
    public Boolean getVisited() {
        return visited;
    }

    //MODIFIES: this.
    //EFFECTS: Gets name of the coffee shop and replace it with new name
    public void editName(String n) {
        this.name = n;
    }

    //MODIFIES: this.
    //EFFECTS: Gets address of the coffee shop and replace with new address
    public void editAddress(String a) {
        this.address = a;
    }

    //REQUIRES: between [0, 5]
    //MODIFIES: this
    //EFFECTS: Gets rating of the coffee shop and replace with new rating
    public void editRating(double r) {
        this.rating = r;
    }

    //MODIFIES: this.
    //EFFECTS: Gets address of the coffee shop and replace with new address
    public void editVisited(Boolean b) {
        this.visited = b;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("address", address);
        json.put("rating", rating);
        json.put("visited", visited);
        return json;
    }

}