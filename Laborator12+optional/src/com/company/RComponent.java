package com.company;

import javax.swing.*;
import java.io.Serializable;

public class RComponent implements Serializable {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    private String componentName;
    private int X;
    private int Y;


    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public RComponent(){ }

    public void init(String componentName,String text, int X, int Y){
        this.componentName = componentName;
        this.text = text;
        this.X = X;
        this.Y = Y;
    }
}

