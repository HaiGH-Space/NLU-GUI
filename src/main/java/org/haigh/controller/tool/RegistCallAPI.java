package org.haigh.controller.tool;

import haigh.api.nlu.API;
import haigh.api.nlu.model.RegisterCourse;
import haigh.api.nlu.model.Student;
import org.haigh.model.Instance;
import org.haigh.view.Application;

import javax.swing.*;
import java.io.IOException;
import java.util.List;


public class RegistCallAPI   {
    JFrame frame;
    public RegistCallAPI(JFrame frame){
        this.frame = frame;
    }
    public void call(List<Object[]> data) {
        Student student = login();
        if (student!=null) {
            String auth = student.getAuthorization();
            API api = Instance.API;
           for (Object[] o : data) {
               try {
                    RegisterCourse registerCourse = api.dkmh(auth, (String) o[0], (Boolean) o[1]);
                    if (registerCourse.isIs_thanh_cong()) {
                        JOptionPane.showMessageDialog(frame, "Đăng ký môn: "+ o[3] + "thành công+\n" + registerCourse.getKet_qua_dang_ky().getNgay_dang_ky(), "Đăng ký môn", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(frame, "Đăng ký môn: "+ o[3] + "\n"+ registerCourse.getThong_bao_loi(), "Đăng ký môn", JOptionPane.ERROR_MESSAGE);
                    }

               } catch (IOException e) {
                   JOptionPane.showMessageDialog(frame, "Đăng ký môn: "+ o[3] + "\ndk: "+ o[1], "Đăng ký môn", JOptionPane.ERROR_MESSAGE);
               }
           }

        }

    }
    public Student login() {
        API api = Instance.API;
        try {
            Student student = api.login(Application.getUsername(), Application.getPassword());
            if (student == null) {
                throw new IOException();
            }
            return student;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Lỗi ở đăng nhập ... Hãy thử lại", "Đăng nhập", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}
