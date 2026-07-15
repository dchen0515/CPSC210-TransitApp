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
        System.out.println("Enter direction (NB, SB, EB, WB):");
        String direction = input.nextLine();

        System.out.println("Enter stop name:");
        String name = input.nextLine();

        System.out.println("Enter stop ID (5-digit number starting with 5 or 6):");
        int id = Integer.parseInt(input.nextLine());

        System.out.println("Is this a timing point? (enter true/false only, case-sensitive):");
        boolean timingPoint = Boolean.parseBoolean(input.nextLine());

        // Construct the stop object
        Stop s = new Stop(direction, name, id, timingPoint);
        route.addStop(s);
        System.out.println("Stop added.");
    }

    // EFFECTS: prints all stops
    private void doListStops() {
        String result = route.listStops();

        if (result.isEmpty()) {
            System.out.println("No stops in list yet.");
        } else {
            System.out.println("Stops on this route:");
            System.out.println(result);
        }
    }

    // MODIFIES: this
    // EFFECTS: modifies a stop name
    private void doModifyStop() {
        System.out.println("Enter a stop ID to modify.");
        int id = Integer.parseInt(input.nextLine());

        Stop found = null;

        for (Stop s : route.getStops()) {
            if (s.getStopID() == id) {
                found = s;
                break;
            }
        }

        if (found == null) {
            System.out.println("Stop not found.");
            return;
        }

        System.out.println("Enter new stop name:");
        String newName = input.nextLine();

        if (newName.isEmpty()) {
            System.out.println("New stop name cannot be empty.");
            return;
        }

        found.modifyStopName(newName);
        System.out.println("Stop name updated.");
    }

    // MODIFIES: this
    // EFFECTS: records a bus operator message
    private void doRecordMessage() {
        System.out.println("Enter operator message:");
        String msg = input.nextLine();

        if (msg.isEmpty()) {
            System.out.println("Message cannot be empty.");
            return;
        }

        route.recordOperatorMessage(msg);
        System.out.println("Message recorded.");
    }
}