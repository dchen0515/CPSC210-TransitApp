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
        setPreferredSize(new Dimension(400, 300));

        ImageIcon icon = new ImageIcon("C:\\Users\\dchen\\Desktop\\IMG_6290.jpg");

        Image scaled = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaled);

        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);
    }

}
