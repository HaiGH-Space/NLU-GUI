package org.haigh.controller.login;

import haigh.api.nlu.API;
import haigh.api.nlu.model.Student;
import org.haigh.model.Instance;
import org.haigh.view.Application;
import org.haigh.view.login.LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginController implements ActionListener {
    LoginPage loginPage;
    public LoginController(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = loginPage.getUsername();
        String password = loginPage.getPassword();
        String message;
        int status = JOptionPane.ERROR_MESSAGE;
        if (!(username.isBlank() || password.isBlank() || password.isEmpty() || username.isEmpty())) {
            try {
                API api = new API();
                Student student =  api.login(username,password);
                if (student != null) {
                    Application.setUsername(username);
                    Application.setPassword(password);
                    Application.setStudent(student);
                    if (loginPage.isRememberMe()) {
                        Instance.writeAccountJSON(username,password);
                    }
                    loginPage.getApplication().goToHomePage();
                    return;
                } else {
                    message = "Đăng nhập thất bại";
                }
            } catch (IOException ex) {
                message = ex.getMessage();
            }
        }else {
            message = "Vui lòng nhập đầy đủ mssv và mật khẩu";
        }
        JOptionPane.showMessageDialog(loginPage, message, "Đăng nhập", status);
    }
}
