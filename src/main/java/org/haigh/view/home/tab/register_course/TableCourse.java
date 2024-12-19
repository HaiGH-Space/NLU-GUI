package org.haigh.view.home.tab.register_course;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TableCourse extends JTable {
    private DefaultTableModel tableCourseModel;
    public TableCourse(TableCourseModel model){
        super(model);
        this.tableCourseModel = model;
        this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.setShowGrid(true);
        this.setRowHeight(30);
        this.setGridColor(Color.BLACK);
        this.setDragEnabled(false);
        this.getTableHeader().setReorderingAllowed(false);
        this.setIntercellSpacing(new Dimension(1, 1));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.setDefaultRenderer(Object.class, centerRenderer);
        setSizeCol();
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnIndex == 1 ? Boolean.class : String.class;
    }
    private void setSizeCol() {
        //"","Đăng ký","Mã môn","Tên môn","Số tín chỉ", "Nhóm tổ","Lớp","Số lượng cl","Số lượng lớp","Thời khóa biểu"
        TableColumn column = this.getColumnModel().getColumn(0); // Lấy cột đầu tiên
        column.setMinWidth(0); // Đặt chiều rộng tối thiểu là 0
        column.setMaxWidth(0); // Đặt chiều rộng tối đa là 0
        column.setPreferredWidth(0); // Đặt chiều rộng ưa thích là 0
        column.setWidth(0);
        this.getColumnModel().getColumn(1).setPreferredWidth(15);
        this.getColumnModel().getColumn(2).setPreferredWidth(50);
        this.getColumnModel().getColumn(3).setPreferredWidth(150);//ten mon
        this.getColumnModel().getColumn(4).setPreferredWidth(10);
        this.getColumnModel().getColumn(5).setPreferredWidth(10);
        this.getColumnModel().getColumn(6).setPreferredWidth(19);//lop
        this.getColumnModel().getColumn(7).setPreferredWidth(10);
        this.getColumnModel().getColumn(8).setPreferredWidth(10);
        this.getColumnModel().getColumn(9).setPreferredWidth(200);//tkbb
    }
}
