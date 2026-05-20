/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
//123240101_KurniaArdiningrum
/**
 *
 * @author LENOVO
 */
import View.ViewData;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewData view = new ViewData();
            view.setVisible(true);
        });
    }
}
