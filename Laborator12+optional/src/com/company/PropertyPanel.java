package com.company;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class PropertyPanel extends JPanel {

    private MainFrame mainFrame;
    private JTable table;

    PropertyPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));
        setPreferredSize(new Dimension(260, 600));
        this.setLayout(new BorderLayout());





        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.setVisible(true);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setColumnIdentifiers(new String[]{"Property","Value"});
        tableModel.addRow(new String[]{"Click on a component first."});

        table.setModel(tableModel);
        table.repaint();




        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {



                if(e.getColumn() != -1){
                    Object value = table.getModel().getValueAt(e.getFirstRow(), e.getColumn());
                    Object property = table.getModel().getValueAt(e.getFirstRow(), 0);
                    if(value != null && property != null) {
                        System.out.println(property.toString());
                        System.out.println(value.toString());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);

    }

    public void bindData(JComponent comp){

        try {

            BeanInfo info = Introspector.getBeanInfo(comp.getClass());
            PropertyDescriptor[] properties = info.getPropertyDescriptors();
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);
            for(PropertyDescriptor item : properties){
                try {
                    Object value = null;
                    if (item.getPropertyType() == String.class ||item.getPropertyType() == int.class ) {
                        Method m = item.getReadMethod();
                        if(m != null && m.getName().startsWith("get") && !m.getName().equalsIgnoreCase("getClass"))
                            value = m.invoke(comp);
                    }
                    tableModel.addRow(new String[]{item.getName(), value != null ? ( item.getPropertyType()  == String.class ? (String) value : (item.getPropertyType() == int.class ? value.toString() : "" )  ) : ""});


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            table.setModel(tableModel);
            table.repaint();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
