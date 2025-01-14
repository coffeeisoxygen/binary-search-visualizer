package com.coffeecode.view;

import java.awt.BorderLayout;
import javax.swing.*;

public class VisualizationPanel {
    private final JPanel panel;

    public VisualizationPanel() {
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Binary Search Visualization"));

        // Placeholder for visualization
        JLabel placeholder = new JLabel("Visualization Area", SwingConstants.CENTER);
        panel.add(placeholder, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}
