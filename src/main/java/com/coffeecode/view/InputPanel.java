package com.coffeecode.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.coffeecode.App;
import com.coffeecode.viewmodel.DictionaryViewModel;

public class InputPanel {
    private final JPanel panel;
    private final JTextField searchField;
    private final JButton translateButton;
    private final JButton clearButton;
    private final JButton showDictionaryButton;

    private static final int COMPONENT_HEIGHT = 35;
    private static final Color PRIMARY_COLOR = new Color(64, 123, 255);
    private static final Color PRIMARY_HOVER_COLOR = new Color(50, 105, 220);
    private static final Color SECONDARY_COLOR = new Color(108, 117, 125);
    private static final Color SECONDARY_HOVER_COLOR = new Color(90, 100, 110);

    public InputPanel(DictionaryViewModel viewModel, App app) {
        // Main panel with gradient background
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gradient = new GradientPaint(0, 0, new Color(240, 242, 245),
                        getWidth(), getHeight(), new Color(248, 249, 250));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Search field with modern style
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(200, COMPONENT_HEIGHT));
        searchField.setMaximumSize(new Dimension(300, COMPONENT_HEIGHT));
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        searchField.setToolTipText("Enter a word to translate...");

        // Buttons with hover effect
        translateButton = createStyledButton("Translate", PRIMARY_COLOR, PRIMARY_HOVER_COLOR);
        clearButton = createStyledButton("Clear", SECONDARY_COLOR, SECONDARY_HOVER_COLOR);
        showDictionaryButton = createStyledButton("Show Dictionary", PRIMARY_COLOR, PRIMARY_HOVER_COLOR);
        showDictionaryButton.setPreferredSize(new Dimension(150, COMPONENT_HEIGHT)); // Adjust width

        // Button actions
        translateButton.addActionListener(e -> {
            String word = searchField.getText().trim();
            if (!word.isEmpty()) {
                viewModel.translate(word);
            }
        });
        clearButton.addActionListener(e -> searchField.setText(""));
        showDictionaryButton.addActionListener(e -> app.showDictionary());

        // Layout with improved spacing
        panel.add(searchField);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(translateButton);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(clearButton);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(showDictionaryButton);
        panel.add(Box.createHorizontalGlue());
    }

    private JButton createStyledButton(String text, Color backgroundColor, Color hoverColor) {
        JButton button = new JButton(text) {
            private boolean isHovered = false;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isHovered ? hoverColor : backgroundColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            public void setUI(javax.swing.plaf.ButtonUI ui) {
                super.setUI(ui);
                setContentAreaFilled(false);
                setBorderPainted(false);
                setFocusPainted(false);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        };

        button.setPreferredSize(new Dimension(120, COMPONENT_HEIGHT));
        button.setMaximumSize(new Dimension(150, COMPONENT_HEIGHT));
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(hoverColor);
                button.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(backgroundColor);
                button.repaint();
            }
        });

        return button;
    }

    public JPanel getPanel() {
        return panel;
    }
}
