package org.haigh.controller.registration_results;

import haigh.api.nlu.model.RegistrationResults;
import haigh.api.nlu.model.RegistrationResults.Group;
import org.haigh.model.Instance;
import org.haigh.view.Application;
import org.haigh.view.footer.IRefresh;
import org.haigh.view.home.tab.registration_results.RegistrationResultsTab;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class RefreshResultController implements IRefresh {
    RegistrationResultsTab registerCourseTab;
    DefaultTableModel model;

    public RefreshResultController(RegistrationResultsTab registerCourseTab) {
        this.registerCourseTab = registerCourseTab;
        this.model = registerCourseTab.getTableModel();
    }

    @Override
    public void refresh(boolean isShow) {
        String message;
        int status = JOptionPane.ERROR_MESSAGE;
        try {
            List<RegistrationResults> registrationResults = Instance.API.dsKetQuaDKMH(Application.getAuthorization());
            if (registrationResults != null) {
                status = JOptionPane.INFORMATION_MESSAGE;
                message = "Làm mới danh sách thành công";
                model.setRowCount(0);
                for (RegistrationResults results : registrationResults) {
                    //  {"Mã môn", "Tên môn", "Số tc", "Nhóm tổ", "Học phí", "Ngày đăng ký"}
                    Group group = results.getTo_hoc();
                    model.addRow(new Object[]{group.getMa_mon(), group.getTen_mon(), group.getSo_tc(), group.getNhom_to(), results.getHoc_phi_tam_tinh(), results.getNgay_dang_ky()});
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

    @Override
    public void actionPerformed(ActionEvent e) {
        refresh(true);
    }
}
