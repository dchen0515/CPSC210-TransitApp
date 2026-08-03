package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.RouteManager;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Route;

import java.awt.*;

/* Represents the main graphical user interface for the TransitApp application
 * Displays list of stops, user controls for interacting with route,
 * and the visual component.
 */
@ExcludeFromJacocoGeneratedReport
public class TransitAppUI extends JFrame {
    private StopListPanel stopListPanel;
    private ControlPanel controlPanel;
    private VisualPanel visualPanel;
    private RouteManager routeManager;

    private JTextField routeNumberField;
    private JTextField routeNameField;
    private JButton createRouteButton;

    private JButton loadButton;
    private JButton saveButton;

    private static final String JSON_STORE = "./date/transit.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: sets up the main window for the TransitApp GUI and initializes
    // all UI panels (stop list, controls, route creation, visual component)
    public TransitAppUI() {
        setupFrame();
        setupRouteFields();
        setupPersistence();

        routeManager = new RouteManager();

        add(buildRoutePanel(), BorderLayout.NORTH);

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
    // EFFECTS: initializes persistence functions and load+save buttons
    private void setupPersistence() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        loadButton = new JButton("Load Data");
        saveButton = new JButton("Save Data");

        loadButton.addActionListener(e -> loadData());
        saveButton.addActionListener(e -> saveData());
    }

    // EFFECTS: builds and returns the panel for entering route number and name
    private JPanel buildRoutePanel() {
        JPanel routePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        routePanel.add(new JLabel("Route number:"));
        routePanel.add(routeNumberField);

        routePanel.add(new JLabel("Route name:"));
        routePanel.add(routeNameField);

        routePanel.add(createRouteButton);

        return routePanel;
    }

    // REQUIRES: routeNumberField and routeNameField have valid text
    // MODIFIES: this, routeManager
    // EFFECTS: creates a new Route using the operator input, adds it to the
    // manager, and initializes stop list panel, control panel, and visual
    // panel for interacting with this route
    public void handleCreateRoute() {
        String numText = routeNumberField.getText().trim();
        String name = routeNameField.getText().trim();

        if (numText.isEmpty() || name.isEmpty()) {
            return;
        }

        int routeNum = Integer.parseInt(numText);
        Route currentRoute = new Route(routeNum, name);
        routeManager.addRoute(currentRoute);

        buildMainPanels(currentRoute);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: builds and adds the stop list panel, control panel, and visual
    // panel for the given route to the main window
    private void buildMainPanels(Route currentRoute) {
        stopListPanel = new StopListPanel();
        controlPanel = new ControlPanel(stopListPanel, currentRoute);
        visualPanel = new VisualPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, stopListPanel, visualPanel);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerLocation(450);

        add(splitPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: loads routeManager from JSON file and rebuilds GUI panels
    private void loadData() {
        // stub
    }

    // EFFECTS: saves routeManager to JSON file
    private void saveData() {
        // stub
    }

    // EFFECTS: runs the TransitApp GUI application
    public static void main(String[] args) {
        new TransitAppUI();
    }
}
