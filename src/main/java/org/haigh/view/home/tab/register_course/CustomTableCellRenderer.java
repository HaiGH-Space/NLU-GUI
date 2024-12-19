package org.haigh.view.home.tab.register_course;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private RegisterCourseTab registerCourseTab;
    public CustomTableCellRenderer(RegisterCourseTab registerCourseTab) {
        this.registerCourseTab = registerCourseTab;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String id = (String) table.getValueAt(row, 0);
        boolean isSelectedAdd = false;
        for (Object[] object  : registerCourseTab.getDataSelected()) {
            if (id.equals(object[0])) {
                isSelectedAdd =true;
                break;
            }
        }
        if (isSelected) {
            cell.setBackground(table.getSelectionBackground());
        } else if (isSelectedAdd){
            cell.setBackground(Color.YELLOW);
        } else {
            cell.setBackground(table.getBackground());
        }
        return cell;
    }
}
