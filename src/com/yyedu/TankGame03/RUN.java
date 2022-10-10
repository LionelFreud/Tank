package com.yyedu.TankGame03;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RUN extends JFrame {
    //定义一个面板
    private MyPanel mp = null;

    public static void main(String[] args) {
        RUN game01 = new RUN();
    }

    public RUN(){
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);
        this.setSize(1400,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(mp);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Recorder.save();
                    System.exit(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
