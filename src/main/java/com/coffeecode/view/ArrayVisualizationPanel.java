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

        int startX = (getWidth() - (words.size() * CELL_WIDTH)) / 2;
        int startY = getHeight() / 2;

        drawCells(g2d, startX, startY);
        drawIndices(g2d, startX, startY);
    }

    private void drawCells(Graphics2D g2d, int startX, int startY) {
        for (int i = 0; i < words.size(); i++) {
            int x = startX + (i * CELL_WIDTH);
            int y = startY - CELL_HEIGHT;

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
        if (currentStep == null)
            return;

        g2d.setColor(Color.BLACK);
        // Draw index numbers
        for (int i = 0; i < words.size(); i++) {
            int x = startX + (i * CELL_WIDTH);
            String index = String.valueOf(i);
            drawCenteredString(g2d, index, x, startY + V_GAP,
                    CELL_WIDTH, CELL_HEIGHT / 2);
        }

        // Draw pointers
        if (currentStep != null) {
            drawPointer(g2d, "L", currentStep.getLeft(), startX, startY);
            drawPointer(g2d, "M", currentStep.getMid(), startX, startY);
            drawPointer(g2d, "R", currentStep.getRight(), startX, startY);
        }
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
        int x = startX + (index * CELL_WIDTH) + CELL_WIDTH / 2;
        g2d.drawString(label, x - 5, startY + V_GAP + CELL_HEIGHT);
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

}