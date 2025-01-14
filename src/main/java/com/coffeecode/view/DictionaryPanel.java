package com.coffeecode.view;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.coffeecode.viewmodel.DictionaryViewModel;

public class DictionaryPanel {
    private final JDialog dialog;
    private final JTable dictionaryTable;

    public DictionaryPanel(DictionaryViewModel viewModel) {
        dialog = new JDialog();
        dialog.setTitle("Dictionary Data");
        dialog.setSize(400, 500);
        dialog.setLayout(new BorderLayout());

        // Table model
        String[] columnNames = { "Word", "Translation" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        dictionaryTable = new JTable(model);

        // Populate table with dictionary data
        viewModel.getAllEntries().forEach((key, value) -> model.addRow(new Object[] { key, value }));

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(dictionaryTable);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dialog.setVisible(false));
        dialog.add(closeButton, BorderLayout.SOUTH);
        
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }

    public void showDialog(JFrame parent) {
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
