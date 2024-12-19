package org.haigh.controller.register_course;

import org.haigh.controller.INotifyObs;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class PopupMenuController extends ACourseController {
    private JMenuItem menuAdd,menuRemove;
    private INotifyObs notifyObs;
    public PopupMenuController(INotifyObs notifyObs,JTable table) {
        super(null);
        this.table = table;
        this.notifyObs = notifyObs;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Object[] rowData = new Object[table.getColumnCount()];
            for (int i = 0; i < table.getColumnCount(); i++) {
                rowData[i] = table.getValueAt(selectedRow, i);
            }
            table.repaint();
            notifyObs.notifyObserversTool(rowData , e.getActionCommand().equals("Add")?0:1);
        }
    }
}
