package org.haigh.tool;

import org.haigh.controller.IGetDataUseToolSelected;
import org.haigh.controller.INotifyObs;
import org.haigh.controller.register_course.CheckBoxCourseController;
import org.haigh.controller.tool.RegistCallAPI;
import org.haigh.view.AFrame;
import org.haigh.view.APopupMenu;
import org.haigh.view.BaseUI;
import org.haigh.view.home.tab.register_course.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ToolFrame extends AFrame implements BaseUI,ObserverTool, INotifyObs, IGetDataUseToolSelected {
    private static String[] columnNames = {"", "Đăng ký", "Mã môn", "Tên môn", "Số tc", "Nhóm tổ", "Lớp", "Cl", "Sl", "Thời khóa biểu"};
    private Object[][] data = {};
    private List<Object[]> dataNow,dataSelected;
    private List<String> dataSelectedUseTool ;
    private JButton btnRegister;
    private JFormattedTextField secondField;

    JSpinner spinner ;
    private TableCourseModel tableCourseModelSelected;
    private TableCourseModel tableCourseModelNow;
    private TableCourse tableCourseSelected;
    private TableCourse tableCourseNow;
    private RegisterCourseTab registerCourseTab;
    private APopupMenu popupMenu;
    private CheckBoxCourseController checkBoxCourseController;
    private RegistCallAPI registCallAPI;
    public ToolFrame(RegisterCourseTab registerCourseTab){
        this.registerCourseTab = registerCourseTab;
    }
    @Override
    public void init() {
        this.setTitle("Tool đăng ký môn học");
        this.setSize(new Dimension((int) (WIDTH_UI*0.75), (int) (HEIGHT_UI*0.75)));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        registCallAPI = new RegistCallAPI(this);
        GridBagConstraints gbc = new GridBagConstraints();
        dataSelected = new ArrayList<>();
        dataNow = new ArrayList<>();
        dataSelectedUseTool = new ArrayList<>();
        tableCourseModelSelected = new TableCourseModel(data, columnNames);
        tableCourseModelNow = new TableCourseModel(data, columnNames);
        tableCourseModelNow.setCellEditableFirst(1);
        tableCourseNow = new TableCourse(tableCourseModelNow);
        tableCourseSelected = new TableCourse(tableCourseModelSelected);
        popupMenu = new ToolPopupMenu(this,tableCourseNow);
        tableCourseNow.setComponentPopupMenu(popupMenu);
        tableCourseNow.setDefaultRenderer(Object.class, new CustomTableCellRenderer(this));
        tableCourseNow.getColumnModel().getColumn(1).setCellRenderer(new CheckboxRenderer(this));
        JScrollPane scrollPaneNow = new JScrollPane(tableCourseNow);
        JScrollPane scrollPaneSelected = new JScrollPane(tableCourseSelected);
        // Phần đầu tiên chiếm 4 phần
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 4;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(scrollPaneSelected,gbc);
        // Phần 2
        gbc.gridy = 1; // Chuyển xuống hàng
        this.add(scrollPaneNow,gbc);
        // Phần 3
        gbc.weighty = 2; // Phần thứ ba chiếm 2 phần
        gbc.gridy = 2; // Chuyển xuống hàng tiếp theo

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0); // Giá trị tối thiểu
        formatter.setMaximum(100); // Giá trị tối đa
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        secondField = new JFormattedTextField(formatter);
        secondField.setValue(1000);
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1);
        spinner = new JSpinner(model);
        btnRegister = new JButton("Đăng ký");
        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));

        footer.add(new JLabel("Độ trễ: "));
        footer.add(secondField);
        footer.add(new JLabel("Lặp: "));
        footer.add(spinner);
        footer.add(btnRegister);
        this.add(footer, gbc);
        checkBoxCourseController = new CheckBoxCourseController(tableCourseNow);

    }

    @Override
    public void setOnClick() {
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Object[]> res = new ArrayList<>();
                for (String id : dataSelectedUseTool) {
                    for (int i = 0; i < tableCourseModelNow.getRowCount();i++) {
                        if (id.equals(tableCourseModelNow.getValueAt(i,0))) {
                            Object[] data = new Object[tableCourseModelNow.getColumnCount()];
                            for (int column = 0; column < data.length; column++) {
                                data[column] = tableCourseModelNow.getValueAt(i, column);
                            }
                            res.add(data);
                            break;
                        }
                    }
                }
                registCallAPI.call(res, (Integer) secondField.getValue(),(int)spinner.getValue());
            }
        });
    }

    @Override
    public void update(Object[] message,int status) {
        if (status==0) {
            boolean contain = false;
            for (Object[] data : dataSelected) {
                if (message[0].equals(data[0])) {
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                System.out.println(message[0]);
                tableCourseModelNow.addRow(message);
                tableCourseModelSelected.addRow(message);
                dataNow.add(message);
                dataSelected.add(message);
            }
        }else {
            for (int i = 0; i< tableCourseModelSelected.getRowCount();i++) {
                if (tableCourseModelSelected.getValueAt(i, 0).equals(message[0])) {
                    tableCourseModelSelected.removeRow(i);
                    tableCourseModelNow.removeRow(i);
                    break;
                }
            }
            for (Object[] data : dataSelected) {
                if (message[0].equals(data[0])) {
                    dataSelected.remove(data);
                    break;
                }
            }
            for (String id : dataSelectedUseTool) {
                if (message[0].equals(id)) {
                    dataSelectedUseTool.remove(id);
                    break;
                }
            }
            for (Object[] dataN : dataNow) {
                if (message[0].equals(dataN[0])) {
                    dataNow.remove(dataN);
                    break;
                }
            }
        }
    }
    public List<Object[]> getDataSelected(){
        return dataSelected;
    }

    @Override
    public void notifyObserversTool(Object[] message, int status) {
        if (status==0) {
            boolean contain = false;
            for (String id : dataSelectedUseTool) {
                if (message[0].equals(id)) {
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                dataSelectedUseTool.add((String) message[0]);
            }
        }else {
            for (String id : dataSelectedUseTool ) {
                if (id.equals(message[0])) {
                    dataSelectedUseTool.remove(id);
                    break;
                }
            }
        }
    }

    @Override
    public List<String> getDataUseToolSelected() {
        return dataSelectedUseTool;
    }
}
