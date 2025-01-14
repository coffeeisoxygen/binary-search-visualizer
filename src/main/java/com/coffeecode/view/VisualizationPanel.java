package com.coffeecode.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class VisualizationPanel {
    private final JPanel panel;
    private final JLabel lowLabel;
    private final JLabel midLabel;
    private final JLabel highLabel;
    private final JSlider speedSlider;
    private final JButton playPauseButton;
    private final JButton stopButton;
    private final JButton resetButton;

    public VisualizationPanel() {
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Visualization area
        JPanel visualArea = new JPanel(new BorderLayout());
        visualArea.setBorder(BorderFactory.createTitledBorder("Binary Search Visualization"));
        visualArea.setPreferredSize(new Dimension(700, 400));

        // Placeholder for visualization process
        JLabel placeholder = new JLabel("Visualization Area", SwingConstants.CENTER);
        visualArea.add(placeholder, BorderLayout.CENTER);

        // Control panel (low, mid, high display + buttons and slider)
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));

        // Labels for low, mid, high
        JPanel indicatorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        lowLabel = new JLabel("Low: -");
        midLabel = new JLabel("Mid: -");
        highLabel = new JLabel("High: -");

        indicatorPanel.add(lowLabel);
        indicatorPanel.add(midLabel);
        indicatorPanel.add(highLabel);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        playPauseButton = new JButton("Play");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        buttonPanel.add(playPauseButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        // Speed slider
        JPanel sliderPanel = new JPanel(new BorderLayout());
        sliderPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel sliderLabel = new JLabel("Speed:", SwingConstants.LEFT);
        speedSlider = new JSlider(1, 10, 5);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setMajorTickSpacing(1);

        sliderPanel.add(sliderLabel, BorderLayout.WEST);
        sliderPanel.add(speedSlider, BorderLayout.CENTER);

        // Add all components to control panel
        controlPanel.add(indicatorPanel);
        controlPanel.add(buttonPanel);
        controlPanel.add(sliderPanel);

        // Add components to the main panel
        panel.add(visualArea, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return panel;
    }

    // Public methods to update UI (placeholders for now)
    public void updateLow(int low) {
        lowLabel.setText("Low: " + low);
    }

    public void updateMid(int mid) {
        midLabel.setText("Mid: " + mid);
    }

    public void updateHigh(int high) {
        highLabel.setText("High: " + high);
    }

    public JButton getPlayPauseButton() {
        return playPauseButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JSlider getSpeedSlider() {
        return speedSlider;
    }
}
