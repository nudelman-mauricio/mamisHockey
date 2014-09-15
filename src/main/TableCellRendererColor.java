package main;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import logicaNegocios.Tarjeta;

public class TableCellRendererColor extends DefaultTableCellRenderer {

    private JLabel componente;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.      
        if ((column == 2)){
            componente.setHorizontalAlignment(LEFT);
        } else {
            componente.setHorizontalAlignment(CENTER);
        }

        if (value instanceof Tarjeta) {
            if ((column >= 3) && (column < 5)) {
                componente.setBackground(Color.green);
            } else if ((column >= 6) && (column < 8)) {
                componente.setBackground(Color.yellow);
            } else if ((column >= 8) && (column < 10)) {
                componente.setBackground(Color.red);
            }
            if (isSelected) {
                componente.setForeground(Color.black);
            }
        } else {
            if (isSelected) {
                componente.setBackground(Color.GRAY);
            } else {
                componente.setBackground(Color.white);
            }
        }

        return componente;

    }

}
