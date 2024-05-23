package net.sdp1.main;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JFrame {
    private Player player;
    private Enemy enemy;
    private HealthBarGui playerHealthBar;
    private HealthBarGui enemyHealthBar;
    
    public GameScreen(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        
        //health bars
        playerHealthBar = new HealthBarGui(player);
        enemyHealthBar = new HealthBarGui(enemy);
        
        //screen
        setTitle("RPG MURIM SIMULATION");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // health bars
        JPanel healthBarsPanel = new JPanel();
        healthBarsPanel.setLayout(new GridLayout(2, 1));
        healthBarsPanel.add(playerHealthBar);
        healthBarsPanel.add(enemyHealthBar);
        add(healthBarsPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
