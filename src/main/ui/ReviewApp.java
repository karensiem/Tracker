package ui;

import model.CoffeeShop;
import model.Filter;
import model.Tracker;
import model.Visited;

import java.util.Scanner;



public class ReviewApp {
    private Scanner input;
    protected Tracker tracker1;
    private Visited visited1;
    private Filter filter;

    public ReviewApp() {
        input = new Scanner(System.in);
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
            if (command.equals("v")) {
                setVisited();
            } else {
                if (command.equals("f")) {
                    setFilter();
                } else {
                    if (command.equals("l")) {
                        setPrinter();
                    } else {
                        System.out.println("Invalid...\n");
                    }
                }
            }
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

    private void setVisited() {
        System.out.print("Please select\n");

        System.out.print("\tadd -> add to visited\n"); //[note: the coffee shops must be in tracker]

        System.out.print("\tremove -> remove from the visited list\n");

        String choice = input.next();

        if (choice.equals("add")) {
            makeVisitedShop();
        } else {
            if (choice.equals("remove")) {
                removeVisitedShop();
            }
        }
    }

    private void setFilter() {
        filter.filterHigh(tracker1);
        printFilter();
    }

    private void makeVisitedShop() {
        System.out.print("Enter coffee shop name: ");
        String name = input.next();

        if (tracker1.inTracker(name)) {
            visited1.visit(name); //keeps adding in null as it accesses a different cslist than the initialized one
            System.out.println("Successfully added.\n");
        } else {
            System.out.println("Coffee Shop not found in Tracker...\n");
            System.out.println("Would you like to add a new coffee shop in Tracker\n");
            String decision = input.next();

            if (decision.equals("yes")) {
                makeCoffeeShop();
            } else {
                System.out.println("\n");
            }
        }
    }



    private void makeCoffeeShop() {
        System.out.print("Enter coffee shop name: ");
        String name = input.next();
        System.out.print("Enter coffee shop address: ");
        String address = input.next();
        System.out.print("Enter" + name + "'s rating out of 5:");
        Double r = input.nextDouble();

        if (r >= 0 && r <= 5) {
            new CoffeeShop(name, address, r);
            tracker1.addCS(new CoffeeShop(name, address, r));
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

    private void removeVisitedShop() {
        System.out.print("Enter coffee shop name you would like to remove:");
        String a = input.next();
        if (visited1.inTracker(a)) {
            visited1.removeCS(a);
            System.out.println("Successfully removed.\n");
        } else {
            System.out.println("Coffee Shop not found...\n");
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
        visited1 = new Visited();
        filter = new Filter();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayStart() {
        System.out.println("\nWould you like to access:");
        System.out.println("\tt -> Coffee shop tracker");
        System.out.println("\tv -> visited coffee shops");
        System.out.println("\tf -> filter put high rating coffee shops");
        System.out.println("\tl -> get list of coffee shops");
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

        for (int i = 0; i < visited1.getNumItems(); i++) {
            CoffeeShop objectV = visited1.getCoffeeShop(i);
            System.out.println("\t" + objectV.getName() + "  " + objectV.getAddress() + "  " +  objectV.getRating());
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

