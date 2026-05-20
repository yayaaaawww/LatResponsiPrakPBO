/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author LENOVO
 */
import Controller.ControllerCandidate;
import Model.ModelCandidate.ModelCandidate;
import Model.ModelCandidate.ModelTable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewData extends JFrame {
    private ControllerCandidate controller;
    private ModelTable modelTable;
    private JTable table;

    public ViewData() {
        controller = new ControllerCandidate();
        initComponents();
        loadTable();
    }

    private void initComponents() {
        setTitle("Sistem Rekrutmen PT. OOP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ── Tabel ──
        modelTable = new ModelTable(controller.getAllCandidates());
        table = new JTable(modelTable);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Sembunyikan kolom ID
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ── Panel Form (kanan) ──
        InputData inputPanel = new InputData(controller, this);
        add(inputPanel, BorderLayout.EAST);

        // ── Klik baris tabel → isi form ──
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                ModelCandidate c = modelTable.getCandidateAt(table.getSelectedRow());
                inputPanel.fillForm(c);
            }
        });
    }

    public void loadTable() {
        List<ModelCandidate> data = controller.getAllCandidates();
        modelTable.setData(data);
    }

    public JTable getTable() {
        return table;
    }

    public ModelTable getModelTable() {
        return modelTable;
    }
}

