package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;

import model.Route;
import model.RouteManager;

/*
 * Represents the panel in the TransitApp GUI that displays all operator messages
 * recorded by the user.
 */
@ExcludeFromJacocoGeneratedReport
public class OperatorMessagePanel extends JPanel {
    private DefaultListModel<String> model;
    private JList<String> list;

    public OperatorMessagePanel() {
        setLayout(new BorderLayout());

        model = new DefaultListModel<>();
        list = new JList<>(model);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane scrollPane = new JScrollPane(list);
        add(new JLabel("Operator Messages"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: rebuilds the list of operator messages from all routes
    public void refresh(RouteManager manager) {
        model.clear();

        for (Route route : manager.getRoutes()) {
            model.addElement("Route " + route.getRouteNumber() + " " + route.getRouteName());

            for (String msg : route.getOperatorMessages()) {
                model.addElement("  - " + msg);
            }
            model.addElement("");
        }

        if (model.isEmpty()) {
            model.addElement("(No operator messages recorded)");
        }
    }
}
