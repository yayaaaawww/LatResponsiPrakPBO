/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.ModelCandidate;

import java.util.List;

/**
 *
 * @author LENOVO
 */
import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCandidate implements InterfaceDAOCandidate {

    @Override
    public boolean insert(ModelCandidate c) {
        String sql = "INSERT INTO recruit (nama, path, writing, coding, interview, score, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = Connector.getConnection().prepareStatement(sql)) {
            ps.setString(1, c.getNama());
            ps.setString(2, c.getPath());
            ps.setInt(3, c.getWriting());
            ps.setInt(4, c.getCoding());
            ps.setInt(5, c.getInterview());
            ps.setFloat(6, c.getScore());
            ps.setString(7, c.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal insert: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<ModelCandidate> getAll() {
        List<ModelCandidate> list = new ArrayList<>();
        String sql = "SELECT * FROM recruit";
        try (Statement st = Connector.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ModelCandidate c = new ModelCandidate(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("path"),
                    rs.getInt("writing"),
                    rs.getInt("coding"),
                    rs.getInt("interview"),
                    rs.getFloat("score"),
                    rs.getString("status")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Gagal getAll: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean update(ModelCandidate c) {
        String sql = "UPDATE recruit SET nama=?, path=?, writing=?, coding=?, interview=?, score=?, status=? WHERE id=?";
        try (PreparedStatement ps = Connector.getConnection().prepareStatement(sql)) {
            ps.setString(1, c.getNama());
            ps.setString(2, c.getPath());
            ps.setInt(3, c.getWriting());
            ps.setInt(4, c.getCoding());
            ps.setInt(5, c.getInterview());
            ps.setFloat(6, c.getScore());
            ps.setString(7, c.getStatus());
            ps.setInt(8, c.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal update: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM recruit WHERE id=?";
        try (PreparedStatement ps = Connector.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal delete: " + e.getMessage());
            return false;
        }
    }
}
