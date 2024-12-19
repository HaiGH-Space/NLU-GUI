package org.haigh.view;

import org.haigh.controller.INotifyObs;
import org.haigh.controller.register_course.PopupMenuController;

import javax.swing.*;

public class APopupMenu extends JPopupMenu{
    protected JMenuItem menuAddSelectedList;
    protected JMenuItem menuRemoveSelectedList;
    protected INotifyObs notifyObs;

    //Controller
    private PopupMenuController popupMenuController;

    public APopupMenu(INotifyObs notifyObs,JTable table) {
        this.notifyObs = notifyObs;
        menuAddSelectedList = new JMenuItem();
        menuRemoveSelectedList = new JMenuItem();
        menuAddSelectedList.setActionCommand("Add");
        menuRemoveSelectedList.setActionCommand("Remove");
        popupMenuController = new PopupMenuController(notifyObs,table);
        menuAddSelectedList.addActionListener(popupMenuController);
        menuRemoveSelectedList.addActionListener(popupMenuController);
        this.add(menuAddSelectedList);
        this.add(menuRemoveSelectedList);
    }
}
