package com.coffeecode.visualizer;

import java.util.List;

import com.coffeecode.visualizer.BinarySearchVisualizer.SearchState;

public interface SearchStepListener {
    void onSearchStep(SearchStep step, List<String> words);

    void onSearchComplete(SearchState finalState);
}