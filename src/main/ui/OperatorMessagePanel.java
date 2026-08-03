package ui;

import javax.swing.*;
import java.awt.*;

import model.Route;
import model.RouteManager;

public class OperatorMessagePanel extends JPanel {
    private DefaultListModel<String> model;
    private JList<String> list;

    public OperatorMessagePanel() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: rebuilds the list of operator messages from all routes
    public void refresh(RouteManager manager) {
        // stub
    }
}

