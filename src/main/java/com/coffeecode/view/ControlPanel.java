package com.coffeecode.view;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ControlPanel extends JPanel {
    private final JButton playPauseButton;
    private final JButton stopButton;
    private final JButton resetButton;
    private final JSlider speedSlider;
    private ControlListener listener;

    public ControlPanel() {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createTitledBorder("Controls"));

        playPauseButton = new JButton("Play");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");
        speedSlider = new JSlider(1, 10, 5);

        setupComponents();
        addListeners();
    }

    private void setupComponents() {
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        add(playPauseButton);
        add(stopButton);
        add(resetButton);
        add(new JLabel("Speed: "));
        add(speedSlider);
    }

    private void addListeners() {
        playPauseButton.addActionListener(e -> {
            if (listener != null) listener.onPlayPause();
        });
        stopButton.addActionListener(e -> {
            if (listener != null) listener.onStop();
        });
        resetButton.addActionListener(e -> {
            if (listener != null) listener.onReset();
        });
    }

    public void setControlListener(ControlListener listener) {
        this.listener = listener;
    }

    public interface ControlListener {
        void onPlayPause();
        void onStop();
        void onReset();
    }
}
