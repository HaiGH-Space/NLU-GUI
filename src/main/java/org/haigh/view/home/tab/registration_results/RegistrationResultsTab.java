package org.haigh.view.home.tab.registration_results;

import org.haigh.controller.registration_results.RefreshResultController;
import org.haigh.view.APanel;
import org.haigh.view.Application;
import org.haigh.view.footer.FooterPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RegistrationResultsTab extends APanel {
    private static String[] columnNames = {"Mã môn", "Tên môn", "Số tc", "Nhóm tổ", "Học phí", "Ngày đăng ký"};
    private static DefaultTableModel model;
    private static JTable table;
    private FooterPanel footerPanel;
    Object[][] data = {};

    private static RefreshResultController refreshResultController;
    public RegistrationResultsTab(Application application) {
        super(application);
    }

    @Override
    public void init() {
        this.setLayout(new BorderLayout());
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return column == 5;
            }
        };
        table = new JTable(model);
        setUpTable();
        JScrollPane scrollPane = new JScrollPane(table);
        refreshResultController = new RefreshResultController(this);
        footerPanel = new FooterPanel(application,refreshResultController);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(footerPanel,BorderLayout.SOUTH);
    }

    @Override
    public void setOnClick() {

    }
    private void setSizeCol() {
        //"Mã môn", "Tên môn", "Số tc", "Nhóm tổ", "Học phí", "Ngày đăng ký"
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(300);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
    }
    public void refresh(){
        refreshResultController.refresh(false);
    }
    private void setUpTable(){
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setShowGrid(true);
        table.setRowHeight(30);
        table.setGridColor(Color.BLACK);
        table.setIntercellSpacing(new Dimension(1, 1));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }
    public DefaultTableModel getTableModel(){
        return model;
    }
}
