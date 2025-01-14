package com.coffeecode.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

import com.coffeecode.visualizer.BinarySearchVisualizer.SearchState;
import com.coffeecode.visualizer.SearchStep;
import com.coffeecode.visualizer.SearchStepListener;

public class ArrayVisualizationPanel extends JPanel implements SearchStepListener {
    private static final int CELL_WIDTH = 60;
    private static final int CELL_HEIGHT = 40;
    private static final int V_GAP = 20;
    private static final int H_GAP = 10;
    private static final int MAX_ITEMS_PER_ROW = 10;

    private List<String> words;
    private SearchStep currentStep;
    private boolean isPaused = false;

    public ArrayVisualizationPanel() {
        setPreferredSize(new Dimension(700, 400));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (words == null)
            return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate starting position
        int itemsPerRow = Math.min(MAX_ITEMS_PER_ROW, words.size());
        int totalWidth = itemsPerRow * (CELL_WIDTH + H_GAP) - H_GAP;
        int startX = (getWidth() - totalWidth) / 2;
        int startY = getHeight() / 2;

        drawCells(g2d, startX, startY);
        drawIndices(g2d, startX, startY);
    }

    private void drawCells(Graphics2D g2d, int startX, int startY) {
        if (words == null) return;

        int totalRows = (int) Math.ceil(words.size() / (double) MAX_ITEMS_PER_ROW);
        int startingY = startY - (totalRows * (CELL_HEIGHT + V_GAP)) / 2;

        for (int i = 0; i < words.size(); i++) {
            int row = i / MAX_ITEMS_PER_ROW;
            int col = i % MAX_ITEMS_PER_ROW;

            int x = startX + (col * (CELL_WIDTH + H_GAP));
            int y = startingY + (row * (CELL_HEIGHT + V_GAP));

            // Draw cell background
            g2d.setColor(getCellColor(i));
            g2d.fillRect(x, y, CELL_WIDTH, CELL_HEIGHT);

            // Draw border
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, CELL_WIDTH, CELL_HEIGHT);

            // Draw text
            String word = words.get(i);
            drawCenteredString(g2d, word, x, y, CELL_WIDTH, CELL_HEIGHT);
        }
    }

    private void drawIndices(Graphics2D g2d, int startX, int startY) {
        if (currentStep == null) return;

        int totalRows = (int) Math.ceil(words.size() / (double) MAX_ITEMS_PER_ROW);
        int startingY = startY - (totalRows * (CELL_HEIGHT + V_GAP)) / 2;

        g2d.setColor(Color.BLACK);
        
        // Draw index numbers
        for (int i = 0; i < words.size(); i++) {
            int row = i / MAX_ITEMS_PER_ROW;
            int col = i % MAX_ITEMS_PER_ROW;

            int x = startX + (col * (CELL_WIDTH + H_GAP));
            int y = startingY + (row * (CELL_HEIGHT + V_GAP));

            String index = String.valueOf(i);
            drawCenteredString(g2d, index, x, y + CELL_HEIGHT, CELL_WIDTH, V_GAP);
        }

        // Draw pointers
        drawPointer(g2d, "L", currentStep.getLeft(), startX, startingY);
        drawPointer(g2d, "M", currentStep.getMid(), startX, startingY);
        drawPointer(g2d, "R", currentStep.getRight(), startX, startingY);
    }

    private Color getCellColor(int index) {
        if (currentStep == null)
            return Color.WHITE;

        if (index == currentStep.getMid()) {
            return switch (currentStep.getState()) {
                case FOUND -> Color.GREEN;
                case NOT_FOUND -> Color.RED;
                default -> Color.YELLOW;
            };
        }
        if (index >= currentStep.getLeft() && index <= currentStep.getRight()) {
            return new Color(230, 230, 250);
        }
        return Color.WHITE;
    }

    private void drawPointer(Graphics2D g2d, String label, int index,
            int startX, int startY) {
        int row = index / MAX_ITEMS_PER_ROW;
        int col = index % MAX_ITEMS_PER_ROW;

        int x = startX + (col * (CELL_WIDTH + H_GAP)) + CELL_WIDTH/2;
        int y = startY + (row * (CELL_HEIGHT + V_GAP)) + CELL_HEIGHT + V_GAP;

        g2d.drawString(label, x - 5, y + 15);
    }

    private void drawCenteredString(Graphics2D g2d, String text, int x, int y,
            int width, int height) {
        FontMetrics fm = g2d.getFontMetrics();
        int textX = x + (width - fm.stringWidth(text)) / 2;
        int textY = y + ((height - fm.getHeight()) / 2) + fm.getAscent();
        g2d.drawString(text, textX, textY);
    }

    @Override
    public void onSearchStep(SearchStep step, List<String> words) {
        this.currentStep = step;
        this.words = words;
        repaint();
    }

    @Override
    public void onSearchComplete(SearchState finalState) {
        repaint();
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }

    public void reset() {
        this.words = null;
        this.currentStep = null;
        this.isPaused = false;
        repaint();
    }

}