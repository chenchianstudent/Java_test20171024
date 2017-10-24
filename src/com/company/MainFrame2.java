package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame2 extends JFrame {
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width, screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 500, frmH = 450;
    private JMenuBar jmb = new JMenuBar();
    private JMenu jm1 = new JMenu("File");
    private JMenu jm2 = new JMenu("Set");
    private JMenu jm3 = new JMenu("Game");
    private JMenu jm4 = new JMenu("About");
    private JMenuItem jmife = new JMenuItem("Exit");
    private JMenuItem jmigl = new JMenuItem("Loto");
    private JDesktopPane jdp = new JDesktopPane();
    private JInternalFrame jInternalFrame = new JInternalFrame();
    public MainFrame mframe = new MainFrame();

    public MainFrame2(MainFrame mf) {
        mframe = mf;
        init();
    }

    private void init() {
        this.setBounds(screenW / 2 - frmW / 2, screenH / 2 - frmH / 2, frmW, frmH);
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(jdp);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mframe.setVisible(true);
            }
        });
        this.setJMenuBar(jmb);
        jmb.add(jm1);
        jmb.add(jm2);
        jmb.add(jm3);
        jmb.add(jm4);
        jm1.add(jmife);
        jm3.add(jmigl);
        jmife.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmife.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmigl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jInternalFrame);
                jInternalFrame.setVisible(true);
            }
        });
        jInternalFrame.setBounds(0, 0, 200, 80);

    }
}
