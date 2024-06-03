package net.sdp1.main;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerWindow {
   
   // class reference
   private UI ui;
   private UIManager uiManager;
   
   private JButton backToGameButton;
   private JPanel backToGameButtonPanel, qiPanel, goldPanel, elixirPanel, characterInfoPanel, realmPanel, combatPowerPanel;
   private JLabel qiLabel, goldLabel, elixirLabel, characterInfoLabel, realmLabel, combatPowerLabel;
    
    public PlayerWindow(UI ui) {
        this.ui = ui;
    }
    
    public void playerWindowUI() {
        ui.clearScreen();
        
        //character info display(says character info)
        characterInfoPanel = ui.createPanel(200, 0, 300, 40, Color.BLUE);
        characterInfoLabel = ui.createLabel("[Character Info]", ui.gameFont, Color.WHITE);
        characterInfoPanel.add(characterInfoLabel);
        
        //player realm
        
        
        //player combat power display
        combatPowerPanel = ui.createPanel(0, 150, 200, 40, Color.BLACK);
        combatPowerLabel = ui.createLabel("Combat Power: " + ui.player.combatPower(), ui.gameFont, Color.WHITE);
        combatPowerPanel.add(combatPowerLabel);
        
        //name display
        ui.playerNameLabel.setText("Name: " + ui.player.getName());
        ui.screen.add(ui.playerNameLabel);
        
        //qi display
        qiPanel = ui.createPanel(0, 100, 120, 40, Color.BLACK);
        qiLabel = ui.createLabel("Qi: " + ui.player.getQi(), ui.gameFont, Color.WHITE);
        qiPanel.add(qiLabel);
        
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
        goldPanel = ui.createPanel(300, 100, 100, 40, Color.BLACK);
        goldLabel = ui.createLabel("Gold: " + ui.player.getGold(), ui.gameFont, Color.WHITE);
        goldPanel.add(goldLabel);
        
        //elixir display
        elixirPanel = ui.createPanel(150, 100, 100, 40, Color.BLACK);
        elixirLabel = ui.createLabel("Elixir: " + ui.player.getElix(), ui.gameFont, Color.WHITE);
        elixirPanel.add(elixirLabel);
        
        // back to gameplay button
        backToGameButton = ui.createButton("Back to Game", e -> ui.gameGUI());
        backToGameButtonPanel = ui.createPanel(325, 500, 130, 32, Color.BLACK);
        backToGameButtonPanel.add(backToGameButton);
        
        //screen display(adds components to the screen)
        ui.screen.add(qiPanel);
        ui.screen.add(backToGameButtonPanel);
        ui.screen.add(goldPanel);
        ui.screen.add(elixirPanel);
        ui.screen.add(combatPowerPanel);
        ui.screen.add(characterInfoPanel);
        
        //screen transition (refreshes screen
        ui.screen.revalidate();
        ui.screen.repaint();
    }
    
}
