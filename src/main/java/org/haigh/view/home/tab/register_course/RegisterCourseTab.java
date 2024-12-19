package org.haigh.view.home.tab.register_course;

import org.haigh.controller.INotifyObs;
import org.haigh.controller.register_course.CheckBoxCourseController;
import org.haigh.controller.register_course.OpenToolController;
import org.haigh.controller.register_course.RefreshCourseController;

import org.haigh.view.APanel;
import org.haigh.view.Application;
import org.haigh.view.footer.FooterPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class RegisterCourseTab extends APanel implements INotifyObs {
    private static String[] columnNames = {"", "Đăng ký", "Mã môn", "Tên môn", "Số tc", "Nhóm tổ", "Lớp", "Cl", "Sl", "Thời khóa biểu"};
    private Object[][] data = {};
    private JButton btnOpenTool;
    private TableCourse tableCourse;
    private TableCourseModel tableCourseModel;
    private TableCoursePopupMenu tableCoursePopupMenu;
    private FooterPanel footerPanel;

    // Controller
    private RefreshCourseController refreshCourseController;
//    private CheckBoxCourseController checkBoxCourseController;
    private OpenToolController openToolController;


    public RegisterCourseTab(Application application) {
        super(application);
    }

    @Override
    public void init() {
        this.setLayout(new BorderLayout());
        tableCourseModel = new TableCourseModel(data, columnNames);
        tableCourse = new TableCourse(tableCourseModel);
        tableCoursePopupMenu = new TableCoursePopupMenu(this,tableCourse);
        tableCourse.setComponentPopupMenu(tableCoursePopupMenu);
        tableCourse.setDefaultRenderer(Object.class, new CustomTableCellRenderer(this));
        tableCourse.getColumnModel().getColumn(1).setCellRenderer(new CheckboxRenderer());
        btnOpenTool = new JButton("Mở tool");

        JScrollPane scrollPane = new JScrollPane(tableCourse);

        refreshCourseController = new RefreshCourseController(this);
        openToolController = new OpenToolController(this);
//        checkBoxCourseController = new CheckBoxCourseController(this);
        footerPanel = new FooterPanel(application,refreshCourseController);
        footerPanel.add(btnOpenTool);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(footerPanel,BorderLayout.SOUTH);

    }

    public TableCourseModel getTableCourseModel() {
        return tableCourseModel;
    }

    public TableCourse getTableCourse() {
        return tableCourse;
    }

    @Override
    public void setOnClick() {
        btnOpenTool.addActionListener(openToolController);
//        tableCourse.addMouseListener(checkBoxCourseController);
    }
    @Override
    public void notifyObserversTool(Object[] data,int status){
        openToolController.notifyObserversTool(data,status);
    }
    public List<Object[]> getDataSelected(){
        return openToolController.getDataSelected();
    }
    public void refresh() {
        refreshCourseController.refresh(false);
    }
    public class CheckboxRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            JCheckBox cell = new JCheckBox();
            cell.setSelected((Boolean) value);
            String id = (String) table.getValueAt(row, 0);
            boolean isSelectedAdd = false;
            for (Object[] object  : getDataSelected()) {
                if (id.equals(object[0])) {
                    isSelectedAdd =true;
                    break;
                }
            }
            if (isSelected) {
                cell.setBackground(table.getSelectionBackground());
            } else if (isSelectedAdd){
                cell.setBackground(Color.YELLOW);
            } else {
                cell.setBackground(table.getBackground());
            }
            return cell;
        }
    }
}
