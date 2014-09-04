package main;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRendererColor extends DefaultTableCellRenderer {

    private JLabel componente;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        String texto = (String) value;
        
        if (column == 1){
            componente.setHorizontalAlignment(CENTER);
        }else{
        componente.setHorizontalAlignment(LEFT);
        }
       
        if (column < 3) {
            componente.setBackground(Color.white);
        }
        if ((column >= 3) && (column < 5)) {
            componente.setBackground(Color.green);
        }
        if ((column >= 6) && (column < 8)) {
            componente.setBackground(Color.yellow);
        }
        if ((column >= 8) && (column < 9)) {
            componente.setBackground(Color.red);
        }
    return componente ;

}

}
