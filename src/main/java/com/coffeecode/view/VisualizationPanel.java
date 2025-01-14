package com.coffeecode.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.coffeecode.viewmodel.VisualizerViewModel;
import com.coffeecode.visualizer.BinarySearchVisualizer.SearchState;
import com.coffeecode.visualizer.SearchStep;
import com.coffeecode.visualizer.SearchStepListener;

public class VisualizationPanel extends JPanel implements SearchStepListener, ControlPanel.ControlListener {
    private final ArrayVisualizationPanel arrayPanel;
    private final ControlPanel controlPanel;
    private final transient VisualizerViewModel viewModel;
    private boolean isPlaying;

    public VisualizationPanel(VisualizerViewModel viewModel) {
        this.viewModel = viewModel;
        this.isPlaying = false;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize components
        arrayPanel = new ArrayVisualizationPanel();
        controlPanel = new ControlPanel();

        // Add components
        add(arrayPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // Setup listeners after construction
        setupListeners();
    }

    private void setupListeners() {
        viewModel.addListener(this);
        controlPanel.setControlListener(this);
    }

    @Override
    public void onSearchStep(SearchStep step, List<String> words) {
        arrayPanel.onSearchStep(step, words);
    }

    @Override
    public void onSearchComplete(SearchState finalState) {
        arrayPanel.onSearchComplete(finalState);
        isPlaying = false;
    }

    @Override
    public void onPlayPause() {
        isPlaying = !isPlaying;
        arrayPanel.setPaused(!isPlaying);
    }

    @Override
    public void onStop() {
        isPlaying = false;
        arrayPanel.setPaused(true);
    }

    @Override
    public void onReset() {
        isPlaying = false;
        arrayPanel.reset();
    }
}