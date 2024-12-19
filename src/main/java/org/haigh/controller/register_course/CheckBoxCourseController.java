package org.haigh.controller.register_course;


import javax.swing.*;
import java.awt.event.MouseEvent;

public class CheckBoxCourseController extends ACourseController {

    public CheckBoxCourseController(JTable table) {
        super(null);
        this.table = table;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.rowAtPoint(e.getPoint());
        int column = table.columnAtPoint(e.getPoint());
        if (column == 1) {
            boolean isSelected = (Boolean) table.getValueAt(row, column);
            table.setValueAt(!isSelected, row, column);
        }
    }
}
