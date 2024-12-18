package org.haigh.view;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import haigh.api.nlu.model.Student;
import org.haigh.view.home.HomePage;
import org.haigh.view.login.LoginPage;

import javax.swing.*;
import java.awt.*;

public class Application extends AFrame implements BaseUI {
    private static Student student;
    private static String authorization;
    private static String username;
    private static String password;
    private static LoginPage loginPage;
    private static HomePage homePage;
    @Override
    public void init() {
        this.setSize(new Dimension(WIDTH_UI,HEIGHT_UI));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        loginPage = new LoginPage(this);
        homePage = new HomePage(this);

        loginPage.setVisible(true);
        homePage.setVisible(true);
        goToLoginPage();

    }

    public void goToHomePage() {
        add(homePage, BorderLayout.CENTER);
        homePage.setShow();
        this.removeC(loginPage);
    }
    public void goToLoginPage(){
        add(loginPage, BorderLayout.CENTER);
        this.removeC(homePage);
    }
    @Override
    public void setOnClick() {

    }
    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        UIManager.put("Button.arc", 12);
        UIManager.put("TextComponent.arc", 12);
        UIManager.put("ScrollBar.thumbArc", 12);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Application();
            }
        });
    }

    private void removeC(Component component){
        this.remove(component);
        this.revalidate();
        this.repaint();
    }

    public static String getAuthorization() {
        return authorization;
    }

    public static void setStudent(Student student) {
        Application.student = student;
        Application.authorization = student.getAuthorization();
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Application.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
       Application.password = password;
    }
}
