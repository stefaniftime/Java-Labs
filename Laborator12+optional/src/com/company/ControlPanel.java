package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ControlPanel extends JPanel implements Serializable {

    private final MainFrame frame;
    private final JLabel classNameLabel = new JLabel("Class name");
    private final JTextField classNameField = new JTextField(30);
    private final JLabel textLabel = new JLabel("Default text");
    private final JTextField textField = new JTextField(10);
    private final JButton createButton = new JButton("Add component");


    private final JButton saveButton = new JButton("Save");
    private final JButton loadButton = new JButton("Load");
    private final JButton clearButton = new JButton("Clear");


    private boolean loaded = false;


    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        this.add(classNameLabel);
        this.add(classNameField);
        this.add(textLabel);
        this.add(textField);
        this.add(createButton);
        this.add(saveButton);
        this.add(loadButton);
        this.add(clearButton);
        createButton.addActionListener(e -> {
            JComponent comp = createComponent(classNameField.getText());
            if(comp != null) {
                setText(comp, textField.getText());
                frame.getDesignPanel().addRandom(comp, textField.getText());
            }
            else {System.out.println("[Error] The createComponent() method returned a null component.");}
        });
        saveButton.addActionListener(e -> {
            this.frame.getDesignPanel().saveXML("DesignPanel.xml");
        });
        clearButton.addActionListener(e -> {
            this.frame.getDesignPanel().removeAll();
            this.frame.getDesignPanel().updateUI();
            this.loaded = false;

        });
        loadButton.addActionListener(e -> {
            if(this.loaded){
                JOptionPane.showMessageDialog(this,
                        "You cannot load the components twice. Please clear the canvas first.",
                        "Loading stopped.",
                        JOptionPane.WARNING_MESSAGE);
            }
            else this.loaded = DesignPanel.loadXML("DesignPanel.xml", this.frame);
        });

        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

    }
    public JComponent createComponent(String className) {
        try{
            Class temp = Class.forName((!className.contains("javax.swing.") ? "javax.swing." : "") + className);
            JComponent component = (JComponent) temp.newInstance();
            System.out.println("[OK] Component " + className + " has been found and attached.");
            return component;


        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("[Missing] Component " + className + " is not available.");
            JOptionPane.showMessageDialog(this,
                    "The system was not able to found the specified component. Check the entered data and try again.",
                    "JComponent not found",
                    JOptionPane.WARNING_MESSAGE);
        }
        return null;

    }
    public void setText(JComponent comp, String text) {
        try{
            Method method = comp.getClass().getMethod("setText", String.class);
            if(method != null){
                method.invoke(comp,text);
                System.out.println("[OK] Method setText is available and has been called for the component.");
            }


        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("[Missing] Method setText is not available for the component.");
        }
    }
}

