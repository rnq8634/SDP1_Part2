package net.sdp1.main;

import javax.swing.*;
import java.awt.*;

public class HealthBarGui extends JPanel 
{
    private Character character;
    private int barWidth = 200;
    private int barHeight = 20;
    
    public HealthBarGui(Character character) 
    {
        this.character = character;
        setPreferredSize(new Dimension(barWidth, barHeight));
    }
    
    public void updateHealth() {
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        double healthRatio = (double) character.getHealth() / character.getMaxHealth();
        int healthBarWidth = (int) (barWidth * healthRatio);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, barWidth, getHeight());
        g.setColor(Color.RED);
        g.fillRect(barWidth, 0, getWidth() - barWidth, getHeight());
    }
}
