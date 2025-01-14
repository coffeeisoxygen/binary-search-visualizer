package com.coffeecode.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.coffeecode.App;
import com.coffeecode.viewmodel.DictionaryViewModel;

public class InputPanel {
    private final JPanel panel;
    private final JTextField searchField;
    private final JTextField resultField;

    public InputPanel(DictionaryViewModel viewModel, App app) {
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        searchField = new JTextField(20);
        resultField = new JTextField(20);
        resultField.setEditable(false);

        JButton searchButton = new JButton("Translate");
        searchButton.addActionListener(e -> {
            String word = searchField.getText().trim();
            if (word.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a word to translate", 
                    "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            viewModel.translate(word);
        });

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            searchField.setText("");
            resultField.setText("");
        });

        // Left side components
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);
        searchPanel.add(resultField);

        // Right side button
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton showDictionaryButton = new JButton("Show Dictionary");
        showDictionaryButton.addActionListener(e -> app.showDictionary());
        rightPanel.add(showDictionaryButton);

        panel.add(searchPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setResult(String result) {
        resultField.setText(result);
    }
}
