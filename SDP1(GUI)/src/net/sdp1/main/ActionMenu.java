package net.sdp1.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionMenu extends JFrame {
    
    public ActionMenu() {
        initUI();
    }
    
    private void initUI() {
        setTitle("Action Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(4, 1));
        
        JButton continueButton = new JButton("Continue on your journey");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameMechanic.continueJourney();
            }
        });
        panel.add(continueButton);
        
        JButton statsButton = new JButton("Stats");
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.characterInfo();
            }
        });
        panel.add(statsButton);
        
        JButton saveButton = new JButton("Save Game");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameMechanic.saveGame();
            }
        });
        panel.add(saveButton);
        
        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        panel.add(exitButton);
        
        setVisible(true);
    }
    
}
