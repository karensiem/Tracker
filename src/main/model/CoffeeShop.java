package model;

public class CoffeeShop {
    private String name;        //name of the coffee shop
    private String address;     //address of the coffee shop
    private Double rating;      // the rating of the coffee shop out of 5

    //REQUIRES: rating must be between [0, 5]
    //MODIFIES:
    //EFFECTS: Constructs a coffee shop with the given name, address, and rating
    public CoffeeShop(String name, String address, Double r) {
        this.name = name;
        this.address = address;
        this.rating = r;

    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Gets name of the coffee shop
    public String getName() {
        return name;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Gets Address of the coffee shop
    public String getAddress() {
        return address;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Gets rating of the coffee shop
    public Double getRating() {
        return rating;
    }

    //REQUIRES:
    //MODIFIES: this.
    //EFFECTS: Gets name of the coffee shop and replace it with new name
    public void editName(String n) {
        this.name = n;
    }

    //REQUIRES:
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


}