package org.haigh.view.login;

import org.haigh.controller.login.LoginController;
import org.haigh.model.Account;
import org.haigh.model.Instance;
import org.haigh.view.APanel;
import org.haigh.view.Application;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends APanel {
    JButton btnLogin;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JCheckBox btnRememberMe;
    LoginController controller;
    public LoginPage(Application application) {
        super(application);
    }

    public String getUsername(){
        return txtUsername.getText();
    }
    public String getPassword(){
        return txtPassword.getText();
    }

    @Override
    public void setOnClick() {
        controller = new LoginController(this);
        btnLogin.addActionListener(controller);
    }

    @Override
    public void init() {
        this.setLayout(new GridBagLayout());

        JPanel containerCenter = new JPanel();
        containerCenter.setLayout(new BoxLayout(containerCenter, BoxLayout.Y_AXIS));
        containerCenter.setPreferredSize(new Dimension(280, 300));

        JPanel containUsername = new JPanel();
        JPanel containPassword = new JPanel();
        JPanel containBtnRememberMe = new JPanel();

        containUsername.setLayout(new BoxLayout(containUsername, BoxLayout.X_AXIS));
        containPassword.setLayout(new BoxLayout(containPassword, BoxLayout.X_AXIS));
        containBtnRememberMe.setLayout(new BoxLayout(containBtnRememberMe,BoxLayout.X_AXIS));

        txtPassword = new JPasswordField(15);
        txtPassword.setMaximumSize(new Dimension(150, 30));

        btnRememberMe = new JCheckBox("Nhớ mật khẩu");
        containBtnRememberMe.add(Box.createHorizontalGlue());
        containBtnRememberMe.add(btnRememberMe);

        containPassword.add(new JLabel("Mật khẩu:"));
        containPassword.add(Box.createHorizontalGlue());
        containPassword.add(txtPassword);

        txtUsername = new JTextField(15);
        txtUsername.setMaximumSize(new Dimension(150, 30));

        containUsername.add(new JLabel("MSSV:"));
        containUsername.add(Box.createHorizontalGlue());
        containUsername.add(txtUsername);

        btnLogin = new JButton("Đăng nhập");

        containerCenter.add(containUsername);
        containerCenter.add(Box.createVerticalStrut(15));
        containerCenter.add(containPassword);
        containerCenter.add(Box.createVerticalStrut(15));
        containerCenter.add(containBtnRememberMe);
        containerCenter.add(Box.createVerticalStrut(15));
        containerCenter.add(btnLogin);
        Account account = Instance.ACCOUNT;
        if (account!=null) {
            txtPassword.setText(account.getPassword());
            txtUsername.setText(account.getUsername());
        }
        this.add(containerCenter);
    }
    public boolean isRememberMe(){
        return btnRememberMe.isSelected();
    }
}
