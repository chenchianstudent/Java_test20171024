package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {
    private JLabel lab1 = new JLabel("ID:");
    private JLabel lab2 = new JLabel("Password:");
    private JTextField tf1 = new JTextField();
    private JPasswordField tf2 = new JPasswordField();
    private JButton bt1 = new JButton("Exit");
    private JButton bt2 = new JButton("Enter");
    private Container cp;
    private Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
    private int frmW=300,frmH=150,screenW,screenH;
    public MainFrame() {
        init();
    }

    private void init() {
                        screenW=dim.width;
                        screenH=dim.height;
                        this.setBounds(screenW/2-frmW/2, screenH/2-frmH/2, frmW, frmH);
                        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        cp = this.getContentPane();
                        cp.setLayout(new GridLayout(3, 2, 3, 1));
                        lab1.setHorizontalAlignment(JLabel.RIGHT);
                        lab2.setHorizontalAlignment(JLabel.RIGHT);
                        cp.add(lab1);
                        cp.add(tf1);
                        cp.add(lab2);
                        cp.add(tf2);
                        cp.add(bt1);
                        cp.add(bt2);
                        bt1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.exit(0);
                            }
                        });
                        bt2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(tf1.getText().equals("h304")&&new String(tf2.getPassword()).equals("23323456")){
                                    MainFrame2 mf2=new MainFrame2(MainFrame.this);
                    //MainFrame2 mf2=new MainFrame2();
                    mf2.setVisible(true);
                    MainFrame.this.setVisible(false);
                } else{
                    JOptionPane.showMessageDialog(null,"Wrong or Password");
                }
            }
        });

    }
    public void reset(){
     lab1.setText(" ");
     lab2.setText(" ");
    }
}