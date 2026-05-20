/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author LENOVO
 */

//123240101_KurniaArdiningrum
import Model.ModelCandidate.*;
import java.util.List;

public class ControllerCandidate {
    private DAOCandidate dao;

    public ControllerCandidate() {
        this.dao = new DAOCandidate();
    }

    public boolean addCandidate(String nama, String path, int writing, int coding, int interview) {
        validateInput(nama, writing, coding, interview);

        ModelCandidate candidate;
        if (path.equals("Android Dev")) {
            candidate = new AndroidDeveloper(nama, writing, coding, interview);
        } else {
            candidate = new WebDeveloper(nama, writing, coding, interview);
        }
        return dao.insert(candidate);
    }

    public List<ModelCandidate> getAllCandidates() {
        return dao.getAll();
    }

    public boolean updateCandidate(int id, String nama, String path, int writing, int coding, int interview) {
        validateInput(nama, writing, coding, interview);

        ModelCandidate candidate;
        if (path.equals("Android Dev")) {
            candidate = new AndroidDeveloper(nama, writing, coding, interview);
        } else {
            candidate = new WebDeveloper(nama, writing, coding, interview);
        }
        candidate.setId(id);
        return dao.update(candidate);
    }

    public boolean deleteCandidate(int id) {
        return dao.delete(id);
    }

    private void validateInput(String nama, int writing, int coding, int interview) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong!");
        }
        if (writing < 0 || writing > 100 || coding < 0 || coding > 100 || interview < 0 || interview > 100) {
            throw new IllegalArgumentException("Nilai harus berada di antara 0 - 100!");
        }
    }
}

