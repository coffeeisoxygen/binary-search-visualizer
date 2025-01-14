package com.coffeecode.visualizer;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchVisualizer {
    public enum SearchState {
        SEARCHING, FOUND, NOT_FOUND
    }

    private List<String> englishWords;
    private List<String> indonesianWords;
    private int leftIndex;
    private int midIndex;
    private int rightIndex;
    private SearchState state;
    private boolean isReverseSearch;

    public BinarySearchVisualizer(List<String> englishWords, List<String> indonesianWords) {
        this.englishWords = englishWords;
        this.indonesianWords = indonesianWords;
        resetSearch();
    }

    private void resetSearch() {
        this.leftIndex = 0;
        this.midIndex = 0;
        this.rightIndex = englishWords.size() - 1;
        this.state = SearchState.SEARCHING;
    }

    public List<SearchStep> search(String word) {
        resetSearch();
        List<SearchStep> steps = new ArrayList<>();

        // Try direct search first
        steps.addAll(performSearch(word, englishWords));

        // If not found, try reverse search
        if (state == SearchState.NOT_FOUND) {
            resetSearch();
            isReverseSearch = true;
            steps.addAll(performSearch(word, indonesianWords));
        }

        return steps;
    }

    private List<SearchStep> performSearch(String target, List<String> words) {
        List<SearchStep> steps = new ArrayList<>();
        leftIndex = 0;
        rightIndex = words.size() - 1;

        while (leftIndex <= rightIndex) {
            midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            steps.add(new SearchStep(leftIndex, midIndex, rightIndex, SearchState.SEARCHING));

            int comparison = target.compareTo(words.get(midIndex));

            if (comparison == 0) {
                state = SearchState.FOUND;
                steps.add(new SearchStep(leftIndex, midIndex, rightIndex, SearchState.FOUND));
                return steps;
            }

            if (comparison < 0) {
                rightIndex = midIndex - 1;
            } else {
                leftIndex = midIndex + 1;
            }
        }

        state = SearchState.NOT_FOUND;
        steps.add(new SearchStep(leftIndex, midIndex, rightIndex, SearchState.NOT_FOUND));
        return steps;
    }

    public boolean isReverseSearch() {
        return isReverseSearch;
    }

    public SearchState getCurrentState() {
        return state;
    }

    public List<String> getEnglishWords() {
        return englishWords;
    }

    public List<String> getIndonesianWords() {
        return indonesianWords;
    }
}