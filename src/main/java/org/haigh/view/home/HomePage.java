package org.haigh.view.home;

import org.haigh.view.APanel;
import org.haigh.view.Application;
import org.haigh.view.header.HeaderPanel;
import org.haigh.view.home.tab.register_course.RegisterCourseTab;
import org.haigh.view.home.tab.registration_results.RegistrationResultsTab;

import javax.swing.*;
import java.awt.*;

public class HomePage extends APanel {
    private HeaderPanel headerPanel;
    private JTabbedPane  tabbedPane;

    private static RegisterCourseTab registerCourseTab;
    private static RegistrationResultsTab registrationResultsTab;

    public HomePage(Application application) {
        super(application);
    }

    @Override
    public void init() {

        this.setLayout(new BorderLayout());

        headerPanel = new HeaderPanel(application);

        registerCourseTab = new RegisterCourseTab(application);
        registrationResultsTab = new RegistrationResultsTab(application);
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Đăng ký môn học",new ImageIcon(Application.class.getResource("/image/dkmh.png")),registerCourseTab);
        tabbedPane.addTab("Kết quả đăng ký môn học",new ImageIcon(Application.class.getResource("/image/result.png")),registrationResultsTab);
        add(headerPanel,BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    @Override
    public void setOnClick() {

    }
    public void setShow(){
        registerCourseTab.refresh();
        registrationResultsTab.refresh();
    }
}
