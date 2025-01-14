package com.coffeecode.view;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.coffeecode.viewmodel.DictionaryViewModel;

public class InputPanel {
    private final JPanel panel;
    private final JTextField searchField;
    private final JTextField resultField;

    public InputPanel(DictionaryViewModel viewModel) {
        panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Search"));

        searchField = new JTextField(20);
        resultField = new JTextField(20);
        resultField.setEditable(false);

        JButton searchButton = new JButton("Translate");
        searchButton.addActionListener(e -> {
            String word = searchField.getText().trim();
            viewModel.translate(word);
        });

        panel.add(searchField);
        panel.add(searchButton);
        panel.add(resultField);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setResult(String result) {
        resultField.setText(result);
    }
}
