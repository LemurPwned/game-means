/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kneighs;

import java.awt.Color;

/**
 *
 * @author Jakub
 */
public class Class {
    private String name;
    private int index;
    private Color color;
    
    //to be expanded
    public Class(String name, int index, Color color){
        this.name = name;
        this.index = index;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
