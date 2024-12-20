package org.haigh.controller.register_course;

import haigh.api.nlu.model.Group;
import haigh.api.nlu.model.InformationCenter;
import haigh.api.nlu.model.Subject;
import org.haigh.model.Instance;
import org.haigh.view.Application;
import org.haigh.view.footer.IRefresh;
import org.haigh.view.home.tab.register_course.RegisterCourseTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class RefreshCourseController extends ACourseController implements IRefresh {
    public RefreshCourseController(RegisterCourseTab registerCourseTab) {
       super(registerCourseTab);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        refresh(true);
    }

    @Override
    public void refresh(boolean isShow) {
        String message;
        int status = JOptionPane.ERROR_MESSAGE;
        try {
            InformationCenter informationCenter = Instance.API.dsThongTin(Application.getAuthorization());
            if (informationCenter != null) {
                status = JOptionPane.INFORMATION_MESSAGE;
                message = "Làm mới danh sách thành công";
                model.setRowCount(0);
                List<Group> groups = informationCenter.getDs_nhom_to();
                List<Subject> subjects = informationCenter.getDs_mon_hoc();
                for (Group group : groups) {
                    //  {"","Đăng ký","Mã môn","Tên môn","Số tín chỉ", "Nhóm tổ","Lớp","Số lượng cl","Số lượng lớp","Thời khóa biểu"}
                    model.addRow(new Object[]{group.getId_to_hoc(), group.isIs_dk(), group.getMa_mon(), getTenMonByID(subjects, group.getMa_mon()), group.getSo_tc(), group.getNhom_to(), group.getLop(), group.getSl_cl(), group.getSl_cp(), group.getTkb()});
                }
            } else {
                message = "Không tìm thấy danh sách!";
            }
        } catch (IOException ex) {
            message = ex.getMessage();
        }
        if (isShow) {
            JOptionPane.showMessageDialog(registerCourseTab.getApplication(), message, "Làm mới danh sách môn", status);
        }
    }

    private String getTenMonByID(List<Subject> subjects, String id) {
        for (Subject subject : subjects) {
            if (subject.getMa().equalsIgnoreCase(id)) {
                return subject.getTen();
            }
        }
        return "";
    }
}
