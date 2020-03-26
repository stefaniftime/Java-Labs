package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    //create all buttons (Load, Reset, Exit)
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        //add all buttons
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
    }

    private void save(ActionEvent e) {
        try {
            ImageIO.write(frame.canvas.image, "PNG", new File("e:/test.png"));
        } catch (IOException ex) { System.err.println(ex); }
    }
    private void load(ActionEvent e){
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File("e:/test.png"));
            this.frame.canvas.image = image;
            frame.canvas.graphics = frame.canvas.image.createGraphics();
            frame.canvas.repaint();
        }
        catch(IOException ex){
            System.err.println(ex);
        }
    }
    private void reset(ActionEvent e){
         this.frame.canvas.createOffscreenImage();
         this.frame.repaint();
    }
    private void exit(ActionEvent e){
        System.exit(0);
    }
}
