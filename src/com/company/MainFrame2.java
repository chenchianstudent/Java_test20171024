package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

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
    private Container jifcp;
    private JPanel jpn = new JPanel(new GridLayout(1, 6, 5, 5));//6個標籤
    private JPanel jpn1 = new JPanel(new GridLayout(1, 2, 5, 5));//按鈕
    private JLabel jlbs[] = new JLabel[6];
    private int data[] = new int[6];
    private Random rnd = new Random(System.currentTimeMillis());
    private JButton jbt1 = new JButton("Close");
    private JButton jbt2 = new JButton("Generate");
    public MainFrame mframe = new MainFrame();

    public MainFrame2(MainFrame mf) {
        mframe = mf;
        init();
    }

    private void init() {
        this.setBounds(screenW / 2 - frmW / 2, screenH / 2 - frmH / 2, frmW, frmH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(jdp);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mframe.reset();
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
        jifcp = jInternalFrame.getContentPane();
        jifcp.setLayout(new BorderLayout(5, 5));
        jifcp.add(jpn, BorderLayout.CENTER);
        jifcp.add(jpn1, BorderLayout.SOUTH);
        jpn1.add(jbt1);
        jpn1.add(jbt2);
        for(int i=0;i<6;i++){
        jlbs[i]=new JLabel("0");
            jpn.add(jlbs[i]);

        }

        jbt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lotoGenerate();
            }
        });
        jbt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame.dispose();
            }
        });
    }

    private void lotoGenerate() {
        int i = 0;
        while (i < 6) {
            data[i] = rnd.nextInt(42) + 1;
            int j = 0;
            boolean flag = true;
            while (j < 1 && flag) {
                if (data[i] == data[j]) {
                    flag = false;
                }
                j++;
            }
            if (flag=true) {
                jlbs[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
}
