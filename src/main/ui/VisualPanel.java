package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;

/*
 * Represents the visual panel in the TransitApp GUI that allows a static image
 * to be displayed.
 */
@ExcludeFromJacocoGeneratedReport
public class VisualPanel extends JPanel {
    // MODIFIES: this
    // EFFECTS: initializes the visual panel with a static image
    public VisualPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 450));

        JPanel grid = new JPanel(new GridLayout(2, 3, 5, 5));

        grid.add(makeImageLabel("9285.jpg"));
        grid.add(makeImageLabel("8115.jpg"));
        grid.add(makeImageLabel("21414.jpg"));

        grid.add(makeImageLabel("7489.jpg"));
        grid.add(makeImageLabel("7492.jpg"));
        grid.add(makeImageLabel("3329.jpg"));

        add(grid, BorderLayout.CENTER);
    }

    // REQUIRES: filename is the name of an image file in the folder src/images/
    // EFFECTS: loads image with given filename from src/images/, scales it down
    // to 250x250, and returns a JLabel with the scaled image centered horizontally
    private JLabel makeImageLabel(String filename) {
        ImageIcon icon = new ImageIcon("src/images/" + filename);
        Image scaled = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled), SwingConstants.CENTER);
    }
}
