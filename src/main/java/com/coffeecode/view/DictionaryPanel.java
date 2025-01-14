package com.coffeecode.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.coffeecode.viewmodel.DictionaryViewModel;

public class DictionaryPanel {
    private final JPanel panel;
    private final JTable dictionaryTable;

    public DictionaryPanel(DictionaryViewModel viewModel) {
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dictionary Data"));

        // Table model
        String[] columnNames = { "Word", "Translation" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        dictionaryTable = new JTable(model);

        // Populate table with dictionary data
        viewModel.getAllEntries().forEach((key, value) -> model.addRow(new Object[] { key, value }));

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(dictionaryTable);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel getPanelWithInput(InputPanel inputPanel) {
        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(inputPanel.getPanel(), BorderLayout.NORTH);
        combinedPanel.add(panel, BorderLayout.CENTER);
        return combinedPanel;
    }
}
