package org.haigh.controller.register_course;

import org.haigh.view.home.tab.register_course.RegisterCourseTab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class ACourseController implements ActionListener , MouseListener, MouseMotionListener {
    protected RegisterCourseTab registerCourseTab;
    protected DefaultTableModel model;
    protected JTable table;
    public ACourseController(RegisterCourseTab registerCourseTab){
        this.registerCourseTab = registerCourseTab;
        this.model = registerCourseTab.getTableCourseModel();
        this.table = registerCourseTab.getTableCourse();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
