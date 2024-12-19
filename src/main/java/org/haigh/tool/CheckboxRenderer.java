package org.haigh.tool;


import org.haigh.controller.IGetDataUseToolSelected;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CheckboxRenderer extends DefaultTableCellRenderer {
    IGetDataUseToolSelected iGetDataSelected;
    public CheckboxRenderer(IGetDataUseToolSelected iGetDataSelected) {
        this.iGetDataSelected = iGetDataSelected;
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JCheckBox cell = new JCheckBox();
        cell.setSelected((Boolean) value);
        String id = (String) table.getValueAt(row, 0);
        boolean isSelectedAdd = false;
        for (String idT  : iGetDataSelected.getDataUseToolSelected()) {
            if (id.equals(idT)) {
                isSelectedAdd =true;
                break;
            }
        }
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
        } else if (isSelectedAdd){
            panel.setBackground(Color.YELLOW);
        } else {
            panel.setBackground(table.getBackground());
        }
        panel.add(cell);

        // Trả về JPanel thay vì JCheckBox
        return panel;
    }
}