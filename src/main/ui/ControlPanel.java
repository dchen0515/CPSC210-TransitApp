package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;
import java.awt.event.ActionListener;

/*
 * Represents the control panel in the TransitApp GUI that provides user actions
 * for adding and modifying stops.
 */
@ExcludeFromJacocoGeneratedReport
public class ControlPanel extends JPanel implements ActionListener {

    /*
     * NOTE: ActionListener pattern adapted from the "LabelChanger" example provided in CPSC 210
     * Phase 3 instructions (original source: StackOverflow/Oracle documentation).
     */
    private TransitAppUI model;
    private StopListPanel stopListPanel;

    private JTextField stopNameField;
    private JButton addStopButton;
    private JButton modifyStopButton;

    // MODIFIES: this
    // EFFECTS: initializes the control panel with input fields and buttons for 
    // adding and modifying stops; stores references to the stop list panel and model
    public ControlPanel(StopListPanel stopListPanel, TransitAppUI model) {
        this.stopListPanel = stopListPanel;
        this.model = model;

        setLayout(new FlowLayout(FlowLayout.LEFT));

        stopNameField = new JTextField(20);

        addStopButton = new JButton("Add Stop");
        addStopButton.setActionCommand("add");
        addStopButton.addActionListener(this);

        modifyStopButton = new JButton("Modify Stop");
        modifyStopButton.setActionCommand("modify");
        modifyStopButton.addActionListener(this);

        add(new JLabel("Stop name:"));
        add(stopNameField);
        add(addStopButton);
        add(modifyStopButton);
    }

    /*
     * REQUIRES: e is not null
     * MODIFIES: this, model, stopListPanel
     * EFFECTS: if action command is "add," adds a new stop with the name currently in the text field
     * to the model; if action command is "modify," modifies the selected stop in the model using the 
     * text field value; refreshes the stop list panel to reflect changes.
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // stub
    }
}
