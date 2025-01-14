package com.coffeecode.visualizer;

import com.coffeecode.visualizer.BinarySearchVisualizer.SearchState;

public class SearchStep {
    private final int left;
    private final int mid;
    private final int right;
    private final SearchState state;

    public SearchStep(int left, int mid, int right, SearchState state) {
        this.left = left;
        this.mid = mid;
        this.right = right;
        this.state = state;
    }

    public int getLeft() {
        return left;
    }

    public int getMid() {
        return mid;
    }

    public int getRight() {
        return right;
    }

    public SearchState getState() {
        return state;
    }

    @Override
    public String toString() {
        return String.format("SearchStep[left=%d, mid=%d, right=%d, state=%s]",
                left, mid, right, state);
    }
}