package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;

/*
 * Represents the control panel in the TransitApp GUI that provides user actions
 * for adding and modifying stops.
 */
@ExcludeFromJacocoGeneratedReport
public class ControlPanel extends JPanel {
    private JTextField stopNameField;
    private JButton addStopButton;
    private JButton modifyStopButton;
    private StopListPanel stopListPanel;

    // MODIFIES: this
    // EFFECTS: initializes the control panel with input fields and buttons for 
    // adding and modifying stops
    public ControlPanel(StopListPanel stopListPanel) {
        this.stopListPanel = stopListPanel;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        stopNameField = new JTextField(20);
        addStopButton = new JButton("Add Stop");
        modifyStopButton = new JButton("Modify Stop");

        add(new JLabel("Stop name:"));
        add(stopNameField);
        add(addStopButton);
        add(modifyStopButton);
    }
}
