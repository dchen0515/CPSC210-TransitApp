package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;

import java.util.List;
import java.util.ArrayList;

import model.Stop;

/*
 * Represents the panel in the TransitApp GUI that displays the list of stops
 * currently associated with the route.
 */
@ExcludeFromJacocoGeneratedReport
public class StopListPanel extends JPanel {
    private DefaultListModel<String> model;
    private JList<String> list;

    // MODIFIES: this
    // EFFECTS: initializes the StopList panel with a scrollable JList
    public StopListPanel() {
        setLayout(new BorderLayout());

        model = new DefaultListModel<>();
        list = new JList<>(model);

        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);
    }

    // REQUIRES: stops is not null
    // MODIFIES: this
    // EFFECTS: clears the current list and displays the given stops in the panel
    public void showStops(List<Stop> stops) {
        // stub
    }
}
