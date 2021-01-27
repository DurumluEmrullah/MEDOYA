package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class anaSayfa extends JFrame {


    private JButton agiEgitButton;
    private JButton testEtButton;
    private JPanel mainPanel;
    private JButton tekVeri;

    public anaSayfa(){

        add(mainPanel);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocation(500,200);
        setTitle("Veri Madenciliği Proje Ödevi");

        testEtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testForm tf = new testForm();
                tf.setVisible(true);
               // setVisible(false);
            }
        });
        agiEgitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agiEgit ag = new agiEgit();
                ag.setVisible(true);
            }
        });
        tekVeri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testTekveri tv = new testTekveri();
                tv.setVisible(true);
            }
        });
    }



}
