/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author soumy
 */
public class customRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object>{
    @Override
     public Component getListCellRendererComponent(JList<?> list,Object object, int index, boolean isSelected, boolean hasFocus){
      //return super.getListCellRendererComponent(list, object, index, isSelected, hasFocus)
      imageAndText is = (imageAndText)object;
        setText(is.getName());
        setIcon(is.getImg());
        
        if(isSelected){
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        
        else{
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
         setEnabled(true);
         setFont(list.getFont());   
         
       return this;
     }
     
}
