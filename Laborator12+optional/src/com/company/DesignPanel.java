package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.*;
import java.io.*;
import java.util.ArrayList;

public class DesignPanel extends JPanel implements Serializable {



    private MainFrame mainFrame;
    private ArrayList<RComponent> RComponents;

    /**
     * Eliminated args from constructor to respect JavaBeans rules
     */

    public DesignPanel() {

        this.RComponents = new ArrayList<>();
        setPreferredSize(new Dimension(800, 600));
        setLayout(null);
    }
    public void addRandom(JComponent comp, String text) {
        int x = (int) (Math.random() * (800 - 1)  + 1);
        int y = (int) (Math.random() * (600 - 1)  + 1);
        this.addAtLocation(comp, text, x,y);

    }

    public void addAtLocation(JComponent comp, String text, int x, int y){
        int w = comp.getPreferredSize().width;
        int h = comp.getPreferredSize().height;
        comp.setBounds(x, y, w, h);
        comp.setToolTipText(comp.getClass().getName());
        this.add(comp);

        comp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                mainFrame.getPropertyPanel().bindData(comp);

            }
        });

        this.mainFrame.repaint();
        RComponent r = new RComponent();
        r.init(comp.getClass().getName(),text, x, y);
        this.RComponents.add(r);
    }




    public void saveXML(String fileName){
        try {
            FileOutputStream file = new FileOutputStream("data/" + fileName);
            XMLEncoder x = new XMLEncoder(file);
            System.out.println("before");

            DesignPanel temp = new DesignPanel();
            temp.setRComponents(this.getRComponents());

            x.writeObject(temp);
            System.out.println("after");
            x.close();
            file.close();
            System.out.println("[OK] Design Panel has been encoded (XML).");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[OK] Design Panel failed to be encoded (XML).");
        }

    }


    public static boolean loadXML(String fileName, MainFrame mainFrame){
        try{
            FileInputStream file = new FileInputStream("data/"+fileName);
            XMLDecoder x = new XMLDecoder(file);
            DesignPanel dp = (DesignPanel) x.readObject();

            mainFrame.getDesignPanel().removeAll();
            mainFrame.getDesignPanel().updateUI();


            for (RComponent rC: dp.getRComponents()) {
                JComponent component = mainFrame.getControlPanel().createComponent(rC.getComponentName());
                if(component != null){
                    mainFrame.getControlPanel().setText(component, rC.getText());
                    mainFrame.getDesignPanel().addAtLocation(component, rC.getText(),rC.getX(),rC.getY());
                    mainFrame.getDesignPanel().getRComponents().add(rC);
                }
            }


            x.close();
            file.close();
            return true;

        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("[Missing] You don't have anything stored in the XML file yet.");
        }

        return false;
    }


    public void save(String fileName){

        try {
            FileOutputStream file = new FileOutputStream("data/" + fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.write(("").getBytes());
            out.writeObject(this);
            out.close();
            file.close();
            System.out.println("[OK] Design Panel has been serialized.");


        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ERROR] Design Panel failed to be serialized.");
        }


    }


    public static boolean load(String fileName, MainFrame mainFrame){

        try{
            FileInputStream file = new FileInputStream("data/"+fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            mainFrame.getDesignPanel().removeAll();
            mainFrame.getDesignPanel().updateUI();

            // Method for deserialization of object
            DesignPanel dp = (DesignPanel) in.readObject();


            for (RComponent rC: dp.getRComponents()) {
                JComponent component = mainFrame.getControlPanel().createComponent(rC.getComponentName());
                if(component != null){
                    mainFrame.getControlPanel().setText(component, rC.getText());
                    mainFrame.getDesignPanel().addAtLocation(component, rC.getText(),rC.getX(),rC.getY());
                    mainFrame.getDesignPanel().getRComponents().add(rC);
                }
                mainFrame.repaint();
            }

            in.close();
            file.close();
            return true;

        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;

    }

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public ArrayList<RComponent> getRComponents() {
        return RComponents;
    }
    public void setRComponents(ArrayList<RComponent> RComponents) {
        this.RComponents = RComponents;
    }

}

