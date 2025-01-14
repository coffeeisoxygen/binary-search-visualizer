package com.coffeecode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import com.coffeecode.view.DictionaryPanel;
import com.coffeecode.view.InputPanel;
import com.coffeecode.view.VisualizationPanel;
import com.coffeecode.viewmodel.DictionaryObserver;
import com.coffeecode.viewmodel.DictionaryViewModel;

public class App implements DictionaryObserver {
    private final DictionaryViewModel viewModel;
    private final JFrame frame;
    private final InputPanel inputPanel;
    private final DictionaryPanel dictionaryPanel;
    private final VisualizationPanel visualizationPanel;

    public App() {
        // Initialize ViewModel
        viewModel = new DictionaryViewModel();
        viewModel.initialize();

        // Initialize Panels with proper dependencies
        inputPanel = new InputPanel(viewModel, this);
        dictionaryPanel = new DictionaryPanel(viewModel);
        visualizationPanel = new VisualizationPanel(viewModel.getVisualizerViewModel());

        // Create Main Frame
        frame = new JFrame("Dictionary Translator");
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create Split Pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(100);

        // Add Panels to Split Pane
        splitPane.setTopComponent(inputPanel.getPanel());
        splitPane.add(visualizationPanel); // Changed: directly add JPanel

        // Add Split Pane to Frame
        frame.add(splitPane);
        frame.setLocationRelativeTo(null);
    }

    public void showDictionary() {
        dictionaryPanel.showDialog(frame);
    }

    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void onTranslationComplete(String result) {
        JOptionPane.showMessageDialog(frame, 
            "Translation: " + result,
            "Translation Result", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void onError(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.viewModel.setObserver(app);
            app.show();
        });
    }
}