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
import Model.ModelCandidate.*;

import javax.swing.*;
import java.awt.*;

public class InputData extends JPanel {
    private ControllerCandidate controller;
    private ViewData viewData;

    private JTextField txtName;
    private JComboBox<String> cbPath;
    private JTextField txtWriting;
    private JTextField txtCoding;
    private JTextField txtInterview;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    private int selectedId = -1;

    public InputData(ControllerCandidate controller, ViewData viewData) {
        this.controller = controller;
        this.viewData = viewData;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(200, 0));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.gridx = 0;
        gbc.weightx = 1;

        gbc.gridy = 0; add(new JLabel("Name"), gbc);
        gbc.gridy = 1; txtName = new JTextField(); add(txtName, gbc);

        gbc.gridy = 2; add(new JLabel("Path"), gbc);
        gbc.gridy = 3; cbPath = new JComboBox<>(new String[]{"Android Dev", "Web Dev"}); add(cbPath, gbc);

        gbc.gridy = 4; add(new JLabel("Writing"), gbc);
        gbc.gridy = 5; txtWriting = new JTextField(); add(txtWriting, gbc);

        gbc.gridy = 6; add(new JLabel("Coding"), gbc);
        gbc.gridy = 7; txtCoding = new JTextField(); add(txtCoding, gbc);

        gbc.gridy = 8; add(new JLabel("Interview"), gbc);
        gbc.gridy = 9; txtInterview = new JTextField(); add(txtInterview, gbc);

        gbc.gridy = 10; btnAdd = new JButton("Add"); add(btnAdd, gbc);
        gbc.gridy = 11; btnUpdate = new JButton("Update"); add(btnUpdate, gbc);
        gbc.gridy = 12; btnDelete = new JButton("Delete"); add(btnDelete, gbc);
        gbc.gridy = 13; btnClear = new JButton("Clear"); add(btnClear, gbc);

        // ── Event Listeners ──
        btnAdd.addActionListener(e -> handleAdd());
        btnUpdate.addActionListener(e -> handleUpdate());
        btnDelete.addActionListener(e -> handleDelete());
        btnClear.addActionListener(e -> clearForm());
    }

    public void fillForm(ModelCandidate c) {
        selectedId = c.getId();
        txtName.setText(c.getNama());
        cbPath.setSelectedItem(c.getPath());
        txtWriting.setText(String.valueOf(c.getWriting()));
        txtCoding.setText(String.valueOf(c.getCoding()));
        txtInterview.setText(String.valueOf(c.getInterview()));
    }

    private void handleAdd() {
        try {
            String nama = txtName.getText().trim();
            String path = (String) cbPath.getSelectedItem();
            int writing = Integer.parseInt(txtWriting.getText().trim());
            int coding = Integer.parseInt(txtCoding.getText().trim());
            int interview = Integer.parseInt(txtInterview.getText().trim());

            boolean success = controller.addCandidate(nama, path, writing, coding, interview);
            if (success) {
                JOptionPane.showMessageDialog(this, "Kandidat berhasil ditambahkan!");
                viewData.loadTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan kandidat.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validasi", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleUpdate() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kandidat yang ingin diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            String nama = txtName.getText().trim();
            String path = (String) cbPath.getSelectedItem();
            int writing = Integer.parseInt(txtWriting.getText().trim());
            int coding = Integer.parseInt(txtCoding.getText().trim());
            int interview = Integer.parseInt(txtInterview.getText().trim());

            boolean success = controller.updateCandidate(selectedId, nama, path, writing, coding, interview);
            if (success) {
                JOptionPane.showMessageDialog(this, "Kandidat berhasil diupdate!");
                viewData.loadTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengupdate kandidat.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validasi", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void handleDelete() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih kandidat yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus kandidat ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = controller.deleteCandidate(selectedId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Kandidat berhasil dihapus!");
                viewData.loadTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus kandidat.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void clearForm() {
        selectedId = -1;
        txtName.setText("");
        cbPath.setSelectedIndex(0);
        txtWriting.setText("");
        txtCoding.setText("");
        txtInterview.setText("");
        viewData.getTable().clearSelection();
    }
}
