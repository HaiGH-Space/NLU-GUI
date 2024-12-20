package org.haigh.controller.tool;

import haigh.api.nlu.API;
import haigh.api.nlu.model.RegisterCourse;
import haigh.api.nlu.model.Student;
import org.haigh.model.Instance;
import org.haigh.view.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;


public class RegistCallAPI   {
    JFrame frame;
    API api;
    public RegistCallAPI(JFrame frame){
        this.frame = frame;
    }
    public void call(List<Object[]> data,int delay, int loop) {
         api = new API();
        Timer timer = new Timer(delay, new ActionListener() {
            private int count = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < loop) {
                    new APISwingWorker(data).execute();
                    count++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.setInitialDelay(0); // Set delay lần đầu = 0
        timer.start();
    }

    class APISwingWorker extends SwingWorker<Void,Void> {
        List<Object[]> data;
        public APISwingWorker(List<Object[]> data){
            this.data = data;
        }
        @Override
        protected Void doInBackground() throws Exception {

                Student student = api.login(Application.getUsername(), Application.getPassword());
                if (student == null) {
                    throw new IOException("Đăng nhập không thành công");
                }

                String auth = student.getAuthorization();
                for (Object[] o : data) {
                    String huy = "Đăng";
                    if (!(boolean)o[1]) {
                        huy = "Hủy đăng";
                    }
                    try {
                        RegisterCourse registerCourse = api.dkmh(auth, (String) o[0], (Boolean) o[1]);

                        if (registerCourse.isIs_thanh_cong()) {
                            JOptionPane.showMessageDialog(frame, huy +" ký môn: " + o[3] + " thành công\n" + registerCourse.getKet_qua_dang_ky().getNgay_dang_ky(), "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, huy + " ký môn: " + o[3] + "\n" + registerCourse.getThong_bao_loi() , "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(frame, huy + " ký môn: " + o[3] + "\ndk: " + o[1] , "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
                return null;
        }
    }
}
