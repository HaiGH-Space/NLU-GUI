package org.haigh.controller.register_course;

import org.haigh.tool.ObserverTool;
import org.haigh.tool.SubjectTool;
import org.haigh.tool.ToolFrame;
import org.haigh.view.home.tab.register_course.RegisterCourseTab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class OpenToolController extends  ACourseController implements SubjectTool {
    private RegisterCourseTab registerCourseTab;
    private ToolFrame toolFrame;
    private List<ObserverTool> observerTools;
    private List<Object[]> dataSelected;
    public OpenToolController(RegisterCourseTab registerCourseTab) {
        super(registerCourseTab);
        dataSelected = new ArrayList<>();
        observerTools = new ArrayList<>();
        toolFrame = new ToolFrame(registerCourseTab);
        addObserverTool(toolFrame);
        toolFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                toolFrame.setVisible(false);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        toolFrame.setVisible(true);
    }

    @Override
    public void addObserverTool(ObserverTool observer) {
        this.observerTools.add(observer);
    }

    @Override
    public void removeObserverTool(ObserverTool observer) {
        this.observerTools.remove(observer);
    }

    @Override
    public void notifyObserversTool(Object[] message, int status) {
        if (status==0) {
            boolean contain = false;
            for (Object[] data : dataSelected) {
                if (message[0].equals(data[0])) {
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                dataSelected.add(message);
            }
        }else {
            for (Object[] data : dataSelected) {
                if (message[0].equals(data[0])) {
                    dataSelected.remove(data);
                    break;
                }
            }
        }
        for (ObserverTool observerTool  : observerTools) {
            observerTool.update(dataSelected);
        }
    }

    public List<Object[]> getDataSelected() {
        return dataSelected;
    }
}
