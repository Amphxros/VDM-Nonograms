package es.ucm.vdm.lib.zero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyClass {

    public static void main(String[] args){
        JFrame mFrame_= new JFrame("Hola Mundo");
        JLabel mLabel_= new JLabel("Label: Hola Mundo");
        //Panel
        Panel mPanel_= new Panel();
        mPanel_.setBounds(40,80,400,400);
        mPanel_.setBackground(Color.PINK);
        mFrame_.add(mPanel_);
        mFrame_.getContentPane().add(mLabel_);
        mFrame_.setSize(420,420);

        //Button
        Button b= new Button();
        b.setBackground(Color.CYAN);
        b.setBounds(60,60,100,50);






        mFrame_.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

}