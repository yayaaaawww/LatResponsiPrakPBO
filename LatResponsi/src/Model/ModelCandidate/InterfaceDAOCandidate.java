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

public interface InterfaceDAOCandidate {
    boolean insert(ModelCandidate candidate);
    List<ModelCandidate> getAll();
    boolean update(ModelCandidate candidate);
    boolean delete(int id);
}

