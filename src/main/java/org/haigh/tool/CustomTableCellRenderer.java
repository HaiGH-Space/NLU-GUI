package org.haigh.tool;

import org.haigh.controller.IGetDataUseToolSelected;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private IGetDataUseToolSelected iGetDataUseToolSelected;
    public CustomTableCellRenderer(IGetDataUseToolSelected iGetDataUseToolSelected) {
        this.iGetDataUseToolSelected = iGetDataUseToolSelected;
        setHorizontalAlignment(SwingConstants.CENTER);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String id = (String) table.getValueAt(row, 0);
        boolean isSelectedAdd = false;
        for (String idT  : iGetDataUseToolSelected.getDataUseToolSelected()) {
            if (id.equals(idT)) {
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
