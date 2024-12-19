package org.haigh.controller.register_course;

import org.haigh.view.home.tab.register_course.TableCoursePopupMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class PopupMenuController extends ACourseController {
    private JMenuItem menuAdd,menuRemove;
    public PopupMenuController(TableCoursePopupMenu tableCoursePopupMenu) {
        super(tableCoursePopupMenu.getRegisterCourseTab());
        this.menuAdd = tableCoursePopupMenu.getMenuAddSelectedList();
        this.menuRemove = tableCoursePopupMenu.getMenuRemoveSelectedList();
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
            registerCourseTab.notifyObserversTool(rowData , e.getSource().equals(menuAdd)?0:1);
        }
    }
}
