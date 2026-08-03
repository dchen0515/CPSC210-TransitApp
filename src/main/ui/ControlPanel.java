package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;
import java.awt.event.ActionListener;

import model.Route;
import model.Stop;

/*
 * Represents the control panel in the TransitApp GUI that provides user actions
 * for adding and modifying stops.
 */
@ExcludeFromJacocoGeneratedReport
public class ControlPanel extends JPanel implements ActionListener {

    /*
     * NOTE: ActionListener pattern adapted from the "LabelChanger" example provided
     * in CPSC 210
     * Phase 3 instructions (original source: StackOverflow/Oracle documentation).
     */
    private Route model;
    private StopListPanel stopListPanel;

    private JComboBox<String> directionBox;
    private JTextField stopNameField;
    private JTextField stopIdField;
    private JCheckBox timingPointBox;

    private JButton addStopButton;
    private JButton modifyStopButton;

    // MODIFIES: this
    // EFFECTS: initializes the control panel with input fields and buttons for
    // adding and modifying stops; stores references to the stop list panel and
    // model
    public ControlPanel(StopListPanel stopListPanel, Route model) {
        this.stopListPanel = stopListPanel;
        this.model = model;

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
            String direction = (String) directionBox.getSelectedItem();
            String name = stopNameField.getText().trim();
            String idText = stopIdField.getText().trim();
            boolean timingPoint = timingPointBox.isSelected();

            if (name.isEmpty() || idText.isEmpty()) {
                return;
            }

            int id = Integer.parseInt(idText);

            Stop stop = new Stop(direction, name, id, timingPoint);
            model.addStop(stop);
        }

        stopListPanel.refresh(model);
    }
}
