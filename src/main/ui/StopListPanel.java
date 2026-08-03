package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;

import java.util.List;

import model.Route;
import model.RouteManager;
import model.Stop;

/*
 * Represents the panel in the TransitApp GUI that displays all routes and their list of stops
 * in a single stacked list. Each route has a header (Stops for <route>) followed by its stops.
 * The panel allows the user to select any stop across any route.
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

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);
    }

    // REQUIRES: stops is not null
    // MODIFIES: this
    // EFFECTS: clears the current list and displays the given stops in the panel
    public void showStops(List<Stop> stops) {
        model.clear();

        for (Stop s : stops) {
            model.addElement(s.getStopName());
        }
    }

    // REQUIRES: manager must not be null
    // MODIFIES: this
    // EFFECTS: clears the current displayed stops, rebuilds the list using all
    // routes in manager. For each route, adds a header line, and formats each
    // stop into a readable string, followed by a blank separator line
    public void refresh(RouteManager manager) {
        model.clear();

        for (Route route : manager.getRoutes()) {
            model.addElement("Stops for " + route.getRouteNumber() + " " + route.getRouteName());

            for (Stop s : route.getStops()) {
                String display = s.getDirection()
                        + " | " + s.getStopName()
                        + " | ID: " + s.getStopID()
                        + " | Timing point? " + (s.getIsTimingPoint() ? "Yes" : "No");
                model.addElement("  " + display);
            }
            model.addElement("");
        }
    }

    // getter for the chosen index
    public int getSelectedIndex() {
        return list.getSelectedIndex();
    }

    // REQUIRES: manager is not null; index is valid in this panel's JList
    // EFFECTS: returns the Stop object corresponding to given JList index,
    // interpreting list as a multi-route stacked display. Returns null if
    // the index refers to a route header or a blank line
    public Stop getStopAtIndex(RouteManager manager, int index) {
        return new Stop(null, null, 0, false);
        // stub
    }
}
