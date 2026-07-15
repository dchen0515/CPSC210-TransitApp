package ui;

import model.Route;
import model.Stop;

import java.util.Scanner;

// Represents the console application interface for the bus route system
public class Main {

    private Scanner input;
    private Route route;
    
    // EFFECTS: runs the bus route application
    public static void main(String[] args) {
        new Main().runApp();
    }

    // EFFECTS: starts the main menu loop
    private void runApp() {
        input = new Scanner(System.in);
        route = new Route(402, "TWO ROAD");

        boolean running = true;

        while (running) {
            printMenu();
            String command = input.nextLine();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Application terminated.");
    }

    // EFFECTS: prints menu options
    private void printMenu() {
        System.out.println("\nSelect an option.");
        System.out.println("1 -> Add a stop");
        System.out.println("2 -> List all stops");
        System.out.println("3 -> Modify a stop name");
        System.out.println("4 -> Record an operator message");
        System.out.println("q -> Quit");
    }

    // REQUIRES: a user input corresponding to a valid menu option
    // MODIFIES: this
    // EFFECTS: processes the user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            doAddStop();
        } else if (command.equals("2")) {
            doListStops();
        } else if (command.equals("3")) {
            doModifyStop();
        } else if (command.equals("4")) {
            doRecordMessage();
        } else {
            System.out.println("Invalid option.");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a stop to the route
    private void doAddStop() {
        // stub
    }

    // EFFECTS: prints all stops
    private void doListStops() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: modifies a stop name
    private void doModifyStop() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: records a bus operator message
    private void doRecordMessage() {
        // stub
    }
}