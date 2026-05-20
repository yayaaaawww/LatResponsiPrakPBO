/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ModelCandidate;

/**
 *
 * @author LENOVO
 */
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel {
    private final String[] COLUMNS = {"ID", "Name", "Path", "Writing", "Coding", "Interview", "Score", "Status"};
    private List<ModelCandidate> data;

    public ModelTable(List<ModelCandidate> data) {
        this.data = data;
    }

    public void setData(List<ModelCandidate> data) {
        this.data = data;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMNS[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        ModelCandidate c = data.get(row);
        switch (col) {
            case 0: return c.getId();
            case 1: return c.getNama();
            case 2: return c.getPath();
            case 3: return c.getWriting();
            case 4: return c.getCoding();
            case 5: return c.getInterview();
            case 6: return String.format("%.2f", c.getScore());
            case 7: return c.getStatus();
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public ModelCandidate getCandidateAt(int row) {
        return data.get(row);
    }
}

