package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;
import java.awt.event.ActionListener;

import model.Route;
import model.RouteManager;
import model.Stop;

/*
 * Represents the control panel in the TransitApp GUI that provides user actions
 * for adding and modifying stops in the active route.
 */
@ExcludeFromJacocoGeneratedReport
public class ControlPanel extends JPanel implements ActionListener {

    /*
     * NOTE: ActionListener pattern adapted from the "LabelChanger" example provided
     * in CPSC 210
     * Phase 3 instructions (original source: StackOverflow/Oracle documentation).
     */
    private RouteManager routeManager;
    private Route activeRoute;
    private StopListPanel stopListPanel;
    private OperatorMessagePanel operatorMessagePanel;

    private JComboBox<String> directionBox;
    private JTextField stopNameField;
    private JTextField stopIdField;
    private JCheckBox timingPointBox;

    private JButton addStopButton;
    private JButton modifyStopButton;

    private JTextField operatorMessageField;
    private JButton recordMessageButton;

    // REQUIRES: stopListPanel and routeManager are not null
    // MODIFIES: this
    // EFFECTS: constructs new ControlPanel with accompanying StopListPanel,
    // OperatorMessagePanel, and RouteManager
    // and sets active route using the given value; initializes all input fields and
    // action buttons to add/modify stops
    // and/or operator messages in active route. Note: ControlPanel is based on
    // user's choice in StopListPanel, and defaults
    // to the active route when nothing is selected.
    public ControlPanel(StopListPanel stopListPanel,
            OperatorMessagePanel operatorMessagePanel,
            RouteManager routeManager,
            Route activeRoute,
            JTextField operatorMessageField,
            JButton recordMessageButton) {
        this.stopListPanel = stopListPanel;
        this.operatorMessagePanel = operatorMessagePanel;
        this.routeManager = routeManager;
        this.activeRoute = activeRoute;
        this.operatorMessageField = operatorMessageField;
        this.recordMessageButton = recordMessageButton;

        setupFields();
        setupButtons();
        add(buildInputPanel());
    }

    // MODIFIES: this
    // EFFECTS: initializes all text fields, combo boxes, and checkboxes
    private void setupFields() {
        directionBox = new JComboBox<>(new String[] { "NB", "SB", "EB", "WB" });
        stopNameField = new JTextField(15);
        stopIdField = new JTextField(6);
        timingPointBox = new JCheckBox("Timing point?");
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons and attaches action listeners
    private void setupButtons() {
        addStopButton = new JButton("Add Stop");
        addStopButton.setActionCommand("add");
        addStopButton.addActionListener(this);

        modifyStopButton = new JButton("Modify Stop");
        modifyStopButton.setActionCommand("modify");
        modifyStopButton.addActionListener(this);
    }

    // EFFECTS: builds and returns a panel containing all input fields and buttons
    private JPanel buildInputPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panel.add(new JLabel("Direction (one of NB, SB, EB, WB):"));
        panel.add(directionBox);

        panel.add(new JLabel("Stop name:"));
        panel.add(stopNameField);

        panel.add(new JLabel("Stop ID (5-digit positive integer beginning with 5 or 6):"));
        panel.add(stopIdField);

        panel.add(timingPointBox);
        panel.add(addStopButton);
        panel.add(modifyStopButton);

        return panel;
    }

    /*
     * REQUIRES: e is not null
     * MODIFIES: this, model, stopListPanel
     * EFFECTS: if action command is "add," adds a new stop with the name currently
     * in the text field
     * to the model; if action command is "modify," modifies the selected stop in
     * the model using the
     * text field value; refreshes the stop list panel to reflect changes.
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("add")) {
            handleAddStop();
        } else if (cmd.equals("modify")) {
            handleModifyStop();
        } else if (cmd.equals("operator")) {
            handleOperatorMessage();
        }

        stopListPanel.refresh(routeManager);
    }

    // MODIFIES: model
    // EFFECTS: adds a new stop to the route using the input fields
    private void handleAddStop() {
        Route targetRoute = determineTargetRoute();
        if (targetRoute == null) {
            return;
        }

        String direction = (String) directionBox.getSelectedItem();
        String name = stopNameField.getText().trim();
        String idText = stopIdField.getText().trim();
        boolean timingPoint = timingPointBox.isSelected();

        if (name.isEmpty() || idText.isEmpty()) {
            return;
        }

        int id = Integer.parseInt(idText);
        Stop stop = new Stop(direction, name, id, timingPoint);

        targetRoute.addStop(stop);
    }

    // EFFECTS: returns the route to add a stop to based on selection or activeRoute
    private Route determineTargetRoute() {
        int index = stopListPanel.getSelectedIndex();
        if (index < 0) {
            return activeRoute;
        }

        Stop selectedStop = stopListPanel.getStopAtIndex(index);
        Route selectedRoute = stopListPanel.getRouteAtIndex(index);

        if (selectedStop != null) {
            return routeManager.findRouteContaining(selectedStop);
        }

        if (selectedRoute != null) {
            return selectedRoute;
        }

        return activeRoute;
    }

    // MODIFIES: selected stop
    // EFFECTS: modifies the stop the user clicked using the input fields
    private void handleModifyStop() {
        int index = stopListPanel.getSelectedIndex();
        if (index < 0) {
            return;
        }

        Stop selected = stopListPanel.getStopAtIndex(index);
        if (selected == null) {
            return;
        }

        String direction = (String) directionBox.getSelectedItem();
        String name = stopNameField.getText().trim();
        String idText = stopIdField.getText().trim();
        boolean timingPoint = timingPointBox.isSelected();

        if (name.isEmpty() || idText.isEmpty()) {
            return;
        }

        int id = Integer.parseInt(idText);

        selected.setDirection(direction);
        selected.setStopName(name);
        selected.setStopID(id);
        selected.setTimingPoint(timingPoint);
    }

    // MODIFIES: routeManager
    // EFFECTS: records an operator message for selected route; if no route
    // selected,
    // records message for activeRoute
    private void handleOperatorMessage() {
        Route target = determineTargetRoute();
        if (target == null) {
            return;
        }

        String msg = operatorMessageField.getText().trim();
        if (msg.isEmpty()) {
            return;
        }

        target.recordOperatorMessage(msg);
        operatorMessageField.setText("");

        stopListPanel.refresh(routeManager);
        operatorMessagePanel.refresh(routeManager);
    }

    // Setter for new route
    public void setRoute(Route newRoute) {
        this.activeRoute = newRoute;
    }
}
