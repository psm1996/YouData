/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import javax.swing.DefaultListModel;
import javax.swing.Icon;

/**
 *
 * @author soumy
 */
public class imageAndText {
    private String name;
    private Icon img;
   
    public imageAndText(String name, Icon img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getImg() {
        return img;
    }

    public void setImg(Icon img) {
        this.img = img;
    }
       
     
     
}
