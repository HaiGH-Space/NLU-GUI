package org.haigh.view.home.tab.register_course;

import org.haigh.controller.register_course.PopupMenuController;

import javax.swing.*;

public class TableCoursePopupMenu extends JPopupMenu {
    private JMenuItem menuAddSelectedList;
    private JMenuItem menuRemoveSelectedList;
    private RegisterCourseTab registerCourseTab;

    //Controller
    private PopupMenuController popupMenuController;

    public TableCoursePopupMenu(RegisterCourseTab registerCourseTab) {
        this.registerCourseTab =registerCourseTab;
        menuAddSelectedList = new JMenuItem("Thêm vào tool");
        menuRemoveSelectedList = new JMenuItem("Xóa khỏi tool");
        popupMenuController = new PopupMenuController(this);
        menuAddSelectedList.addActionListener(popupMenuController);
        menuRemoveSelectedList.addActionListener(popupMenuController);
        this.add(menuAddSelectedList);
        this.add(menuRemoveSelectedList);
    }

    public RegisterCourseTab getRegisterCourseTab(){
        return registerCourseTab;
    }

    public JMenuItem getMenuAddSelectedList() {
        return menuAddSelectedList;
    }


    public JMenuItem getMenuRemoveSelectedList() {
        return menuRemoveSelectedList;
    }

}


