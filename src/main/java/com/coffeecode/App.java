package com.coffeecode;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.coffeecode.viewmodel.DictionaryObserver;
import com.coffeecode.viewmodel.DictionaryViewModel;

public class App implements DictionaryObserver {
    private final DictionaryViewModel viewModel;
    private final JFrame frame;
    private final JTextField searchField;
    private final JLabel resultLabel;

    public App() {
        // Initialize ViewModel
        viewModel = new DictionaryViewModel();
        viewModel.initialize();

        // Create UI components
        frame = new JFrame("Dictionary Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout(10, 10));

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Translate");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Result panel
        JPanel resultPanel = new JPanel(new FlowLayout());
        resultLabel = new JLabel("Enter a word to translate");
        resultPanel.add(resultLabel);

        // Add components to frame
        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(resultPanel, BorderLayout.CENTER);

        // Add action listener
        searchButton.addActionListener(e -> {
            String word = searchField.getText().trim();
            String translation = viewModel.translate(word);
            onTranslationComplete(translation != null ? translation : "Not found");
        });

        // Center on screen
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void onTranslationComplete(String result) {
        resultLabel.setText("Translation: " + result);
    }

    @Override
    public void onError(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.show();
        });
    }
}