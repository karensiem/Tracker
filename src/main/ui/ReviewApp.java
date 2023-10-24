package ui;

import model.CoffeeShop;
import model.Tracker;
import model.Visited;

import java.util.Scanner;

public class ReviewApp {
    private Scanner input;
    private Tracker tracker1;
    private Visited visited1;

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
        if (command.equals("a")) {
            setTracker();
        } else {
            if (command.equals("v")) {
                setVisited();
            } else {
                System.out.println("Invalid...\n");
            }
        }
    }

    private void setTracker() {
        System.out.print("Please select");
        System.out.print("\nadd -> add a new coffee shop");
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
        System.out.print("Please select ");

        System.out.print(" add -> add a new coffee shop");

        System.out.print(" remove -> remove an existing coffee shop");

        String choice = input.next();

        if (choice.equals("add")) {
            System.out.print("Enter coffee shop name:");
            // all coffee shops that are in visited must be in tracker list
        }
    }

    private void makeCoffeeShop() {
        System.out.print("Enter coffee shop name:");
        String name = input.next();
        System.out.print("Enter coffee shop address:");
        String address = input.next();
        System.out.print("Enter" + name + "'s rating out of 5:");
        Double r = input.nextDouble();

        if (r >= 0 && r <= 5) {
            new CoffeeShop(name, address, r);
            System.out.println("Coffee Shop was successfully added. \n");
        } else {
            System.out.println("Rating out of bound :(");

            System.out.println("Select yes if you would like to add another Coffee Shop?");
            String selected = input.next();

            if (selected.equals("yes")) {
                setTracker();
            } else {
                System.out.println("\n");
            }
        }
    }

    private void removeCoffeeShop() {
        System.out.print("Enter coffee shop name you would like to remove:");
        String coffeeShop = input.next();

//        tracker1.removeCS(CoffeeShop c);
        System.out.print("\n");
    }

    // MODIFIES: this
    // EFFECTS: initializes the app
    private void init() {
        tracker1 = new Tracker();
        visited1 = new Visited();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayStart() {
        System.out.println("\nWould you like to access:");
        System.out.println("\ta -> Add new coffee shop");
        System.out.println("\tv -> visited coffee shops");
        System.out.println("\tq -> quit");
    }


}





//        boolean keepGoing = true;
//        String command = null;
//
//
//        while (keepGoing) {
//            displayStart();
//            command = input.next();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//
//        System.out.println("Have a good day!");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("a")) {
//            goCoffeeShops();
////        } else if (command.equals("v")) {
////            goVisited();
//        } else {
//            System.out.println("Invalid");
//        }
//    }
//
//    private void displayStart() {
//        System.out.println("Would you like to access:");
//        System.out.println("a -> Add new coffee shop");
//        System.out.println("v -> visited coffee shops");
//        System.out.println("q -> quit");
//    }
//
//    private void goCoffeeShops() {
//        System.out.print("Enter coffee shop name:");
//        String selection = "";
//        System.out.print("Enter coffee shop address:");
//        String address = "";
//        System.out.print("Enter coffee shop rating:");
//        Double r = (double) 0;
//        new CoffeeShop(selection, address, r);
//
//    }

