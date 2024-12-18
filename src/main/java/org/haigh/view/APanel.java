package org.haigh.view;

import javax.swing.*;

public abstract class APanel extends JPanel implements BaseUI {
    protected Application application;
    public APanel(Application application){
        this.application = application;
        this.init();
        this.setOnClick();
    }
    public Application getApplication() {
        return application;
    }
}
