package org.haigh.tool;

import org.haigh.view.AFrame;
import org.haigh.view.BaseUI;
import org.haigh.view.home.tab.register_course.RegisterCourseTab;
import org.haigh.view.home.tab.register_course.TableCourse;
import org.haigh.view.home.tab.register_course.TableCourseModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ToolFrame extends AFrame implements BaseUI,ObserverTool {
    private static String[] columnNames = {"", "Đăng ký", "Mã môn", "Tên môn", "Số tc", "Nhóm tổ", "Lớp", "Cl", "Sl", "Thời khóa biểu"};
    private Object[][] data = {};
    TableCourseModel tableCourseModel;
    TableCourse tableCourse;
    RegisterCourseTab registerCourseTab;
    public ToolFrame(RegisterCourseTab registerCourseTab){
        this.registerCourseTab = registerCourseTab;
    }
    @Override
    public void init() {
        this.setTitle("Tool đăng ký môn học");
        this.setSize(new Dimension((int) (WIDTH_UI*0.75), (int) (HEIGHT_UI*0.75)));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        tableCourseModel = new TableCourseModel(data, columnNames);
        tableCourse = new TableCourse(tableCourseModel);
        JScrollPane scrollPane = new JScrollPane(tableCourse);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void setOnClick() {

    }

    @Override
    public void update(List<Object[]> message) {
        tableCourseModel.setRowCount(0);
        for (Object[] data : message) {
            tableCourseModel.addRow(data);
        }
    }
}
