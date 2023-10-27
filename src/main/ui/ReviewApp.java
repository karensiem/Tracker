package ui;

import model.CoffeeShop;
import model.Filter;
import model.Tracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class ReviewApp {
    private Scanner input;
    protected Tracker tracker1;
    private Filter filter;
    private static final String JSON_FILES = "./data/tracker.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public ReviewApp() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_FILES);
        jsonReader = new JsonReader(JSON_FILES);
        runApp();
    }

    public void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();
        while (keepGoing) {
            displayStart();
            command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Have a good day!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("t")) {
            setTracker();
        } else {
            if (command.equals("f")) {
                setFilter();
            } else {
                if (command.equals("p")) {
                    setPrinter();
                } else {
                    if (command.equals("l")) {
                        loadTracker();
                    } else {
                        if (command.equals("s")) {
                            saveTracker();
                        } else {
                            System.out.println("Invalid...\n");
                        }
                    }
                }
            }
        }
    }

    private void loadTracker() {
        try {
            tracker1 = jsonReader.read();
            System.out.println("Loaded tracker from " + JSON_FILES);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FILES);
        }
    }

    // EFFECTS: saves the tracker to file
    private void saveTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(tracker1);
            jsonWriter.close();
            System.out.println("Saved tracker to " + JSON_FILES);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILES);
        }
    }



    private void setTracker() {
        System.out.print("Please select\n");
        System.out.print("\tadd -> add a new coffee shop\n");
        System.out.print("\tremove -> remove an existing coffee shop\n");
        String select = input.next();
        if (select.equals("add")) {
            makeCoffeeShop();
        } else {
            if (select.equals("remove")) {
                removeCoffeeShop();
            }
        }
    }


    private void setFilter() {
        filter.filterHigh(tracker1);
        printFilter();
    }



    private void makeCoffeeShop() {
        System.out.print("Enter coffee shop name: ");
        String name = input.next();
        System.out.print("Enter coffee shop address: ");
        String address = input.next();
        System.out.print("Enter" + name + "'s rating out of 5:");
        double rating = input.nextDouble();
        System.out.print("(true or false) You have visited" + name + "before\n");
        Boolean visited = input.nextBoolean();



        if (rating >= 0 && rating <= 5) {
            new CoffeeShop(name, address, rating, visited);
            tracker1.addCS(new CoffeeShop(name, address, rating, visited));
            System.out.println("\nCoffee Shop was successfully added.\n");
        } else {
            System.out.println("\nRating out of bound :(\n");

            System.out.println("Select yes if you would like to add another Coffee Shop?\n");
            String selected = input.next();

            if (selected.equals("yes")) {
                makeCoffeeShop();
            } else {
                System.out.println("\n");
            }
        }
    }


    private void removeCoffeeShop() {
        System.out.print("Enter coffee shop name you would like to remove:");
        String coffeeShop = input.next();
        if (tracker1.inTracker(coffeeShop)) {
            tracker1.removeCS(coffeeShop);
            System.out.println("Successfully removed.\n");
        } else {
            System.out.println("Coffee Shop not found...\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the app
    private void init() {
        tracker1 = new Tracker();
        filter = new Filter();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayStart() {
        System.out.println("\nWould you like to access:");
        System.out.println("\tt -> Coffee shop tracker");
        System.out.println("\tf -> filter put high rating coffee shops");
        System.out.println("\tp -> get list of Coffee Shops");
        System.out.println("\ts -> save tracker to files");
        System.out.println("\tl -> load tracker from files");
        System.out.println("\tq -> quit");
    }

    private void setPrinter() {
        System.out.println("select to view:\n");
        System.out.println("\t t -> tracker list");
        System.out.println("\t v -> visited list");

        String list = input.next();

        if (list.equals("t")) {
            printList();
        } else {
            if (list.equals("v")) {
                printVisited();
            }
        }
    }

    private void printList() {
        System.out.println("Here are all the Coffee Shops in your list");

        for (int i = 0; i < tracker1.getNumItems(); i++) {
            CoffeeShop objectT = tracker1.getCoffeeShop(i);
            System.out.println("\t" + objectT.getName() + "  " + objectT.getAddress() + "  " +  objectT.getRating());
        }
    }

    private void printVisited() {
        System.out.println("Here are all the Coffee Shops in your list:");
        for (CoffeeShop cs : tracker1.getCSList()) {
            if (cs.getVisited()) {
                System.out.println("\t" + cs.getName() + "  " + cs.getAddress() + "  " + cs.getRating());
            }
        }
    }

    private void printFilter() {
        System.out.println("Here are all the high rated Coffee Shops in your list:");

        for (int i = 0; i < filter.getNumItems(); i++) {
            CoffeeShop objectF = filter.getCoffeeShop(i);
            System.out.println("\t" + objectF.getName() + "  " + objectF.getAddress() + "  " +  objectF.getRating());
        }
    }


}

