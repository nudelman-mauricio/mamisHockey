/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tame;

import java.awt.Font;

/**
 *
 * @author Leanwit
 */
public interface CellFont {
    
    public Font getFont(int row, int column);
    public void setFont(Font font, int row, int column);
    public void setFont(Font font, int[] rows, int[] columns);


}
