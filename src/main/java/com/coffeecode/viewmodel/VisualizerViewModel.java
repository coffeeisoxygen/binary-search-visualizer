package com.coffeecode.viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coffeecode.visualizer.BinarySearchVisualizer;
import com.coffeecode.visualizer.BinarySearchVisualizer.SearchState;
import com.coffeecode.visualizer.SearchStep;
import com.coffeecode.visualizer.SearchStepListener;

public class VisualizerViewModel {
    private static final Logger LOGGER = LoggerFactory.getLogger(VisualizerViewModel.class);
    private BinarySearchVisualizer visualizer;
    private List<SearchStepListener> listeners = new ArrayList<>();
    private List<String> currentWords;

    public void initialize(Map<String, String> dictionary) {
        List<String> englishWords = new ArrayList<>(dictionary.keySet());
        List<String> indonesianWords = new ArrayList<>(dictionary.values());
        visualizer = new BinarySearchVisualizer(englishWords, indonesianWords);
        LOGGER.info("VisualizerViewModel initialized with {} words", englishWords.size());
    }

    public void addListener(SearchStepListener listener) {
        listeners.add(listener);
    }

    public void visualizeSearch(String word) {
        if (visualizer == null) {
            LOGGER.warn("Visualizer not initialized");
            return;
        }

        List<SearchStep> steps = visualizer.search(word);
        currentWords = visualizer.isReverseSearch() ? new ArrayList<>(visualizer.getIndonesianWords())
                : new ArrayList<>(visualizer.getEnglishWords());

        for (SearchStep step : steps) {
            notifyListeners(step);
        }

        notifySearchComplete(visualizer.getCurrentState());
    }

    private void notifyListeners(SearchStep step) {
        for (SearchStepListener listener : listeners) {
            listener.onSearchStep(step, currentWords);
        }
    }

    private void notifySearchComplete(SearchState state) {
        for (SearchStepListener listener : listeners) {
            listener.onSearchComplete(state);
        }
    }
}