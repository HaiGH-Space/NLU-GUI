package org.haigh.view.home.tab.register_course;

import org.haigh.controller.register_course.RefreshCourseController;
import org.haigh.view.APanel;
import org.haigh.view.Application;

import javax.swing.*;
import java.awt.*;

public class RegisterCourseTab extends APanel {
    private static String[] columnNames = {"","Đăng ký","Mã môn","Tên môn","Số tc", "Nhóm tổ","Lớp","Cl","Sl","Thời khóa biểu"};
    private Object[][] data = {};
    private TableCourse tableCourse;
    private TableCourseModel tableCourseModel;
    private JButton btnRefresh;

    private RefreshCourseController refreshCourseController;
    public RegisterCourseTab(Application application) {
        super(application);
    }

    @Override
    public void init() {
        this.setLayout(new BorderLayout());
        tableCourseModel = new TableCourseModel(data,columnNames);
        tableCourse = new TableCourse(tableCourseModel);


        btnRefresh = new JButton("Làm mới");

        JScrollPane scrollPane = new JScrollPane(tableCourse);

        refreshCourseController = new RefreshCourseController(this);

        this.add(scrollPane, BorderLayout.CENTER);

    }

    public TableCourseModel getTableCourseModel(){
        return tableCourseModel;
    }

    @Override
    public void setOnClick() {
        btnRefresh.addActionListener(refreshCourseController);
    }
    public void refresh(){
        refreshCourseController.refresh(false);
    }

}
