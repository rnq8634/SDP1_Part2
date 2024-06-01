package net.sdp1.main;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlayerWindow {
   
   // class reference
   private UI ui;
   private UIManager uiManager;
   
   private JButton backToGameButton;
   private JPanel backToGameButtonPanel;
    
    public PlayerWindow(UI ui) {
        this.ui = ui;
    }
    
    public void playerWindowUI() {
        ui.clearScreen();
        
        //name display
        ui.playerNameLabel.setText("Name: " + ui.player.getName());
        ui.screen.add(ui.playerNameLabel);
        
        //qi display
        // maybe gets bigger the higher the qi
        
        //martial skill display
         String martialArtText = (ui.player.getMartialSkill() != null && ui.player.getMartialSkill().length > 0)
                                ? ui.player.getMartialSkill()[0]
                                : "None";
        ui.martialLabel.setText("Martial Art: " + martialArtText);
        ui.screen.add(ui.martialLabel);
        
        //martial body display
        String martialBodyText = (ui.player.getMartialBody() != null && ui.player.getMartialBody().length > 0)
                                ? ui.player.getMartialBody()[0]
                                : "None";
        ui.martialBodyLabel.setText("Martial Body: " + martialBodyText);
        ui.screen.add(ui.martialBodyLabel);
        
        //gold display
        
        //elixir display
        
        // back to gameplay button
        backToGameButton = ui.createButton("Back to Game", e -> ui.gameGUI());
        backToGameButtonPanel = ui.createPanel(325, 500, 150, 32, Color.YELLOW);
        backToGameButtonPanel.add(backToGameButton);
        
        ui.screen.add(backToGameButtonPanel);
        
        ui.screen.revalidate();
        ui.screen.repaint();
    }
    
}
