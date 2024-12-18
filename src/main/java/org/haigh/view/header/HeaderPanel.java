package org.haigh.view.header;

import org.haigh.view.APanel;
import org.haigh.view.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeaderPanel extends APanel {
    JButton btnLogOut;
    public HeaderPanel(Application application) {
        super(application);
    }

    @Override
    public void init() {
        btnLogOut = new JButton("Đăng xuất", new ImageIcon(Application.class.getResource("/image/logout.png")));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(btnLogOut);
    }

    @Override
    public void setOnClick() {
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                application.goToLoginPage();
            }
        });
    }
}
