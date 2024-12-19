package org.haigh.tool;

import org.haigh.controller.INotifyObs;
import org.haigh.view.APopupMenu;
import org.haigh.view.home.tab.register_course.TableCoursePopupMenu;

import javax.swing.*;

public class ToolPopupMenu extends APopupMenu {
    public ToolPopupMenu(INotifyObs notifyObs, JTable table) {
        super(notifyObs, table);
        this.menuAddSelectedList.setText("Thêm vào danh sách dk");
        this.menuRemoveSelectedList.setText("Xóa khỏi danh sách dk");
    }
}
