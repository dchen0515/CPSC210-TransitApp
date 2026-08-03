package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.RouteManager;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Route;

import java.awt.*;
import java.io.IOException;

/* Represents the main graphical user interface for the TransitApp application.
 * Allows users to create multiple routes and a list of stops in a stacked list, 
 * and provides user controls for interacting with the active route. Also includes
 * a visual component (bus images).
 */
@ExcludeFromJacocoGeneratedReport
public class TransitAppUI extends JFrame {
    private StopListPanel stopListPanel;
    private OperatorMessagePanel operatorMessagePanel;
    private ControlPanel controlPanel;
    private VisualPanel visualPanel;
    private RouteManager routeManager;

    private JPanel routePanel;

    private JTextField routeNumberField;
    private JTextField routeNameField;
    private JTextField operatorMessageField;
    private JButton createRouteButton;
    private JButton recordMessageButton;

    private JButton loadButton;
    private JButton saveButton;

    private static final String JSON_STORE = "./data/transit.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: sets up the main window for the TransitApp GUI and initializes
    // all UI panels (route + stop list, controls, visual component)
    public TransitAppUI() {
        setupFrame();
        setupRouteFields();
        setupOperatorMessageFields();
        setupPersistence();

        routeManager = new RouteManager();

        add(buildRoutePanel(), BorderLayout.NORTH);
        buildMainPanels(null);

        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up the basic frame structure and properties (title, size,
    // layout)
    private void setupFrame() {
        setTitle("Transit Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: initializes text fields and button to create a route, as well as
    // attaching an action listener for creating a route
    private void setupRouteFields() {
        routeNumberField = new JTextField(5);
        routeNameField = new JTextField(15);
        createRouteButton = new JButton("Create Route");
        createRouteButton.addActionListener(e -> handleCreateRoute());
    }

    // MODIFIES: this
    // EFFECTS: initializes operator message input field and button
    private void setupOperatorMessageFields() {
        operatorMessageField = new JTextField(25);
        recordMessageButton = new JButton("Record Message");
    }

    // MODIFIES: this
    // EFFECTS: initializes persistence functions and load+save buttons
    private void setupPersistence() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        loadButton = new JButton("Load Data");
        saveButton = new JButton("Save Data");

        loadButton.addActionListener(e -> loadData());
        saveButton.addActionListener(e -> saveData());
    }

    // EFFECTS: builds and returns the panel for entering route number, name, and
    // operator messages
    private JPanel buildRoutePanel() {
        routePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        routePanel.setPreferredSize(new Dimension(900, 50));

        routePanel.add(new JLabel("Route number:"));
        routePanel.add(routeNumberField);

        routePanel.add(new JLabel("Route name:"));
        routePanel.add(routeNameField);

        routePanel.add(createRouteButton);
        routePanel.add(loadButton);
        routePanel.add(saveButton);

        routePanel.add(new JLabel("Operator message:"));
        routePanel.add(operatorMessageField);
        routePanel.add(recordMessageButton);

        return routePanel;
    }

    // MODIFIES: this
    // EFFECTS: builds and adds the stop list panel, control panel, and visual
    // panel to the main window; only called once on startup
    private void buildMainPanels(Route activeRoute) {
        stopListPanel = new StopListPanel();
        operatorMessagePanel = new OperatorMessagePanel();
        controlPanel = new ControlPanel(
                stopListPanel,
                operatorMessagePanel,
                routeManager,
                activeRoute,
                operatorMessageField,
                recordMessageButton);

        recordMessageButton.setActionCommand("operator");
        recordMessageButton.addActionListener(controlPanel);

        visualPanel = new VisualPanel();

        JSplitPane leftPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                stopListPanel,
                operatorMessagePanel);
        leftPane.setResizeWeight(0.7);
        
        JSplitPane mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, visualPanel);
        mainPane.setResizeWeight(0.5);

        add(mainPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    // REQUIRES: routeNumberField and routeNameField have valid text
    // MODIFIES: routeManager, controlPanel, stopListPanel
    // EFFECTS: creates a new Route using the operator input, adds it to the
    // manager, and refreshes stop list panel to show all routes
    public void handleCreateRoute() {
        String numText = routeNumberField.getText().trim();
        String name = routeNameField.getText().trim();

        if (numText.isEmpty() || name.isEmpty()) {
            return;
        }

        int routeNum = Integer.parseInt(numText);
        Route currentRoute = new Route(routeNum, name);
        routeManager.addRoute(currentRoute);

        controlPanel.setRoute(currentRoute);
        stopListPanel.refresh(routeManager);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: loads routeManager from JSON file, sets first route as active, and
    // refreshes StopListPanel to display all routes; updates route creation fields
    // to match active route
    private void loadData() {
        try {
            routeManager = jsonReader.read();

            if (routeManager.getRoutes().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No routes found in file.");
                return;
            }

            Route loadedRoute = routeManager.getRoutes().get(0);

            getContentPane().removeAll();

            add(buildRoutePanel(), BorderLayout.NORTH);
            buildMainPanels(loadedRoute);

            stopListPanel.refresh(routeManager);

            routeNumberField.setText(Integer.toString(loadedRoute.getRouteNumber()));
            routeNameField.setText(loadedRoute.getRouteName());

            revalidate();
            repaint();

            JOptionPane.showMessageDialog(this, "Data loaded successfully.");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load data.");
        }
    }

    // EFFECTS: saves routeManager to JSON file; displays confirmation message
    // showing
    // success or failure
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(routeManager);
            jsonWriter.close();

            JOptionPane.showMessageDialog(this, "Data saved successfully.");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to save data.");
        }
    }

    // EFFECTS: runs the TransitApp GUI application
    public static void main(String[] args) {
        new TransitAppUI();
    }
}
