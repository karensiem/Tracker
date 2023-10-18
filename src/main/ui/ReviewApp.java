//package ui;
//
//import model.CoffeeShop;
//
//import java.util.Scanner;
//
//public class ReviewApp {
//    private Scanner input;
//
//    public ReviewApp() {
//        input = new Scanner();
//        runApp();
//    }
//
//    public void runApp() {
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
//}
