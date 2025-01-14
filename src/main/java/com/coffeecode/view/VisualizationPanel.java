package com.coffeecode.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VisualizationPanel {
    private final JPanel panel;

    public VisualizationPanel() {
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel visualArea = new JPanel(new BorderLayout());
        visualArea.setBorder(BorderFactory.createTitledBorder("Binary Search Visualization"));
        visualArea.setPreferredSize(new Dimension(700, 400));

        // Placeholder for visualization
        JLabel placeholder = new JLabel("Visualization Area", SwingConstants.CENTER);
        visualArea.add(placeholder, BorderLayout.CENTER);

        panel.add(visualArea, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}