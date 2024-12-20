package org.haigh.view.footer;

import org.haigh.view.APanel;
import org.haigh.view.Application;

import javax.swing.*;
import java.awt.*;

public class FooterPanel extends APanel {
    JButton btnRefresh;
    IRefresh refresh;
    public FooterPanel(Application application,IRefresh iRefresh) {
        super(application);
        this.refresh = iRefresh;
        setOnClick();
    }

    @Override
    public void init() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(0, 50));
        btnRefresh = new JButton("Làm mới");
        this.add(Box.createHorizontalGlue());
        this.add(btnRefresh);
    }

    @Override
    public void setOnClick() {
        if (refresh!=null) {
            btnRefresh.addActionListener(refresh);
        }
    }
}
