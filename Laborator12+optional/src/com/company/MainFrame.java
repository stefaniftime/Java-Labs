package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class MainFrame extends JFrame implements Serializable {

    private ControlPanel controlPanel;
    private DesignPanel designPanel;
    private PropertyPanel propertyPanel;

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public DesignPanel getDesignPanel() {
        return designPanel;
    }

    public void setDesignPanel(DesignPanel designPanel) {
        this.designPanel = designPanel;
    }


    public MainFrame() {
        super("Designer");
        init();
    }

    public PropertyPanel getPropertyPanel() {
        return propertyPanel;
    }

    public void setPropertyPanel(PropertyPanel propertyPanel) {
        this.propertyPanel = propertyPanel;
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        this.controlPanel = new ControlPanel(this);
        this.designPanel = new DesignPanel();
        this.designPanel.setMainFrame(this);
        this.propertyPanel = new PropertyPanel(this);
        this.add(this.controlPanel,  BorderLayout.NORTH);
        this.add(this.designPanel,  BorderLayout.CENTER);
        this.add(this.propertyPanel, BorderLayout.EAST);






        this.setVisible(true);


        pack();
    }
    public static void main(String[] args) {
        MainFrame m = new MainFrame();
    }
}
