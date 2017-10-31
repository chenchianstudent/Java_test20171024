package com.company;

import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    private JPanel jpanel1 = new JPanel(new GridLayout(2, 3, 5, 5));
    private JMenuItem jmiSetFont = new JMenuItem("Font");
    private String[] options = {"PLAIN", "BOLD", "ITALIC", "BOLD+ITALIC"};
    private JComboBox jcbFstyle = new JComboBox(options);
    private JLabel jlbfamily = new JLabel("Family");
    private JLabel jlbstyle = new JLabel("Style");
    private JLabel jlbsize = new JLabel("Size");
    private JTextField jtfFamily = new JTextField("Hand Me Down S (BRK)");
    private JTextField jtfStyle = new JTextField("PLAIN");
    private JTextField jtfSize = new JTextField("12");
    private JFileChooser jfc = new JFileChooser();
    private JInternalFrame JIFAddcategory = new JInternalFrame();
    private Container jIFAddcategoryCP;
    private JMenuBar jIFAddcategoryJmb = new JMenuBar();
    private JMenuItem jmiAddCategory = new JMenuItem("Category");
    private JMenuItem jmData = new JMenuItem("Data");
    private JMenuItem jmLoad = new JMenuItem("Load");
    private JMenuItem jmNew = new JMenuItem("New");
    private JMenuItem jmClose = new JMenuItem("Close");
    private JTextArea jta = new JTextArea();
    private JScrollPane jsp = new JScrollPane(jta);

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
        jm2.add(jmiSetFont);
        jm1.add(jmiAddCategory);
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
        for (int i = 0; i < 6; i++) {
            jlbs[i] = new JLabel("0");
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
        jpanel1.add(jlbfamily);
        jpanel1.add(jlbstyle);
        jpanel1.add(jlbsize);
        jpanel1.add(jtfFamily);
        jpanel1.add(jcbFstyle);
        jpanel1.add(jtfSize);

        jmiSetFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        MainFrame2.this,
                        jpanel1,
                        "Font Setting",
                        JOptionPane.OK_CANCEL_OPTION);
                jcbFstyle.setEditable(true);
                jcbFstyle.addActionListener(this);
                int fontStyle = 0;
                switch (jcbFstyle.getSelectedIndex()) {
                    case 0:
                        fontStyle = Font.PLAIN;
                        break;
                    case 1:
                        fontStyle = Font.BOLD;
                        break;
                    case 2:
                        fontStyle = Font.ITALIC;
                        break;
                    case 3:
                        fontStyle = Font.BOLD + Font.ITALIC;
                        break;
                }
                if (result == JOptionPane.OK_OPTION) {
                    UIManager.put("Menu.font", new Font(jtfFamily.getText(), fontStyle, Integer.parseInt(jtfSize.getText())));

                }
            }
        });
        jmiAddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JIFAddcategory.setVisible(true);
            }
        });
        jIFAddcategoryCP = JIFAddcategory.getContentPane();
        jIFAddcategoryCP.setLayout(new BorderLayout(5, 5));
        jIFAddcategoryCP.add(jsp, BorderLayout.CENTER);
        JIFAddcategory.setBounds(0, 0, 500, 500);
        JIFAddcategory.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JIFAddcategory.setJMenuBar(jIFAddcategoryJmb);
        jIFAddcategoryJmb.add(jmData);
        jmData.add(jmLoad);
        jmData.add(jmNew);
        jmData.add(jmClose);
        jdp.add(JIFAddcategory);
        jmLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        File infile = jfc.getSelectedFile();
                        BufferedReader br = new BufferedReader(new FileReader(infile));
                        System.out.println("FileName:" + infile.getName());
                        String str = "";
                        while ((str = br.readLine()) != null) {
                            jta.append(str+"\n");
                        }
                        System.out.println("Read file Finished");

                    } catch (Exception ioe) {
                        JOptionPane.showMessageDialog(null, "Error" + ioe.toString());
                    }

                }
            }
        });
    }

    private void lotoGenerate() {
        int i = 0;
        while (i < 6) {
            data[i] = rnd.nextInt(42) + 1;
            int j = 0;
            boolean flag = true;
            while (j < i && flag) {
                if (data[i] == data[j]) {
                    flag = false;
                }
                j++;
            }
            if (flag) {
                jlbs[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
}
