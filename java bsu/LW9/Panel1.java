package LW9;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Panel1 extends JPanel{
    public Panel1() {
        super(new BorderLayout());
        
        String[] columnNames = {"Items"};

        DefaultTableModel modelInput = new DefaultTableModel();
        DefaultTableModel modelOutput = new DefaultTableModel();
        modelInput.addColumn("ItemsInput");
        modelOutput.addColumn("ItemsOutput");
        modelInput.addRow(new Object[]{"name"});
        modelInput.addRow(new Object[]{"date"});
        modelInput.addRow(new Object[]{"boss"});
        modelInput.addRow(new Object[]{"girl"});
        modelInput.addRow(new Object[]{"vaib"});

        JButton buttonInToOut = new JButton(">");
        JButton buttonOutToIn = new JButton("<");
        JLabel center = new JLabel();

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 1));
        jPanel.add(buttonInToOut);
        jPanel.add(center);
        jPanel.add(buttonOutToIn);

        JTable tableLeft = new JTable(modelInput);
        JTable tableRight = new JTable(modelOutput);

        // Добавляем таблицы в JScrollPane
        JScrollPane scrollPaneLeft = new JScrollPane(tableLeft);
        JScrollPane scrollPaneRight = new JScrollPane(tableRight);


        add(scrollPaneLeft, BorderLayout.WEST);
        add(scrollPaneRight, BorderLayout.EAST);
        add(jPanel, BorderLayout.CENTER);

        buttonInToOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = tableLeft.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int selectedEl = selectedRows[i];
                    Object[] data = new Object[1];
                    data[0] = modelInput.getValueAt(selectedEl, 0);
                    modelOutput.addRow(data);
                    modelInput.removeRow(selectedEl);
                }
            }
        });
        
        buttonOutToIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = tableRight.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int selectedEl = selectedRows[i];
                    Object[] data = new Object[1];
                    data[0] = modelOutput.getValueAt(selectedEl, 0);
                    modelInput.addRow(data);
                    modelOutput.removeRow(selectedEl);
                }
            }
        });
    }
}