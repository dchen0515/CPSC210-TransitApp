package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

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

    // MODIFIES: this
    // EFFECTS: sets up the main window for the TransitApp GUI and initializes
    // all UI panels (stop list, controls, visual component)
    public TransitAppUI() {
        setTitle("Transit Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        setLayout(new BorderLayout());

        stopListPanel = new StopListPanel();
        controlPanel = new ControlPanel(stopListPanel, this);
        visualPanel = new VisualPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, stopListPanel, visualPanel);

        splitPane.setResizeWeight(0.5);
        splitPane.setDividerLocation(450);

        add(splitPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: runs the TransitApp GUI application
    public static void main(String[] args) {
        new TransitAppUI();
    }
}
