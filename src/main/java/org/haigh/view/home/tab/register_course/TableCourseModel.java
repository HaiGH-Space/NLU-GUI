package org.haigh.view.home.tab.register_course;

import javax.swing.table.DefaultTableModel;

public class TableCourseModel extends DefaultTableModel {
    public TableCourseModel(Object[][] data, String[] columnNames) {
        super(data,columnNames);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}