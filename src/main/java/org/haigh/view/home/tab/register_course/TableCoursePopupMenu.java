package org.haigh.view.home.tab.register_course;

import org.haigh.controller.INotifyObs;
import org.haigh.view.APopupMenu;

import javax.swing.*;

public class TableCoursePopupMenu extends APopupMenu {
    public TableCoursePopupMenu(INotifyObs notifyObs, JTable table) {
        super(notifyObs, table);
        this.menuAddSelectedList.setText("Thêm vào tool");
        this.menuRemoveSelectedList.setText("Xóa khỏi tool");
    }
}


