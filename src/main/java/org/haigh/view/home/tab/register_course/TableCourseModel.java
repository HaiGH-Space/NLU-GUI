package org.haigh.view.home.tab.register_course;

import javax.swing.table.DefaultTableModel;

public class TableCourseModel extends DefaultTableModel {
    private int colum  = -1;
    public TableCourseModel(Object[][] data, String[] columnNames) {
        super(data,columnNames);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return getCellEditTable(column);
    }
    private boolean getCellEditTable(int column) {
        return this.colum == column;
    }
    public void setCellEditableFirst(int cellEditable) {
       this.colum = cellEditable;
    }
}