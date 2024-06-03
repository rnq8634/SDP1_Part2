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
   private JPanel backToGameButtonPanel, qiPanel, goldPanel, elixirPanel, characterInfoPanel, realmPanel, combatPowerPanel, playerNamePanel, martialPanel, martialBodyPanel;
   private JLabel qiLabel, goldLabel, elixirLabel, characterInfoLabel, realmLabel, combatPowerLabel, playerNameLabel, martialLabel, martialBodyLabel;
    
    public PlayerWindow(UI ui) {
        this.ui = ui;
    }
    
    public void playerWindowUI() {
        ui.clearScreen();
        
        //character info display(says character info)
        characterInfoPanel = ui.createPanel(200, 0, 300, 40, Color.BLACK);
        characterInfoLabel = ui.createLabel("[Character Info]", ui.gameFont, Color.WHITE);
        characterInfoPanel.add(characterInfoLabel);
        
        //player realm
        realmPanel = ui.createPanel(0, 200, 300, 40, Color.BLUE);
        realmLabel = ui.createLabel("[Realm] " + ui.player.getTitle(), ui.gameFont, Color.WHITE);
        realmPanel.add(realmLabel);
        
        
        //player combat power display
        combatPowerPanel = ui.createPanel(0, 100, 200, 40, Color.BLACK);
        combatPowerLabel = ui.createLabel("[Combat Power] " + ui.player.combatPower(), ui.gameFont, Color.WHITE);
        combatPowerPanel.add(combatPowerLabel);
        
        //name display
        playerNamePanel = ui.createPanel(0, 50, 100, 30, Color.BLACK);
        playerNameLabel = ui.createLabel("[Name] " + ui.player.getName(), ui.gameFont, Color.WHITE);
        playerNamePanel.add(playerNameLabel);
        
        //qi display
        qiPanel = ui.createPanel(0, 150, 120, 40, Color.BLACK);
        qiLabel = ui.createLabel("[Qi] " + ui.player.getQi(), ui.gameFont, Color.WHITE);
        qiPanel.add(qiLabel);
        
        //martial skill display
         String martialArtText = (ui.player.getMartialSkill() != null && ui.player.getMartialSkill().length > 0)
                                ? ui.player.getMartialSkill()[0]
                                : "None";
        martialPanel = ui.createPanel(0, 300, 300, 40, Color.BLACK);
        martialLabel = ui.createLabel("[Martial Art] " + martialArtText, ui.gameFont, Color.WHITE);
        martialPanel.add(martialLabel);
        
        //martial body display
        String martialBodyText = (ui.player.getMartialBody() != null && ui.player.getMartialBody().length > 0)
                                ? ui.player.getMartialBody()[0]
                                : "None";
        martialBodyPanel = ui.createPanel(0, 400, 300, 40, Color.BLACK);
        martialBodyLabel = ui.createLabel("[Martial Body] " + martialBodyText, ui.gameFont, Color.WHITE);
        martialBodyPanel.add(martialBodyLabel);
        
        
        //gold display
        goldPanel = ui.createPanel(400, 100, 100, 40, Color.BLACK);
        goldLabel = ui.createLabel("[Gold] " + ui.player.getGold(), ui.gameFont, Color.WHITE);
        goldPanel.add(goldLabel);
        
        //elixir display
        elixirPanel = ui.createPanel(250, 100, 100, 40, Color.BLACK);
        elixirLabel = ui.createLabel("[Elixir] " + ui.player.getElix(), ui.gameFont, Color.WHITE);
        elixirPanel.add(elixirLabel);
        
        // back to gameplay button
        backToGameButton = ui.createButton("[Back to Game]", e -> ui.gameGUI());
        backToGameButtonPanel = ui.createPanel(325, 500, 130, 32, Color.BLACK);
        backToGameButtonPanel.add(backToGameButton);
        
        //screen display(adds components to the screen)
        ui.screen.add(qiPanel);
        ui.screen.add(backToGameButtonPanel);
        ui.screen.add(goldPanel);
        ui.screen.add(elixirPanel);
        ui.screen.add(combatPowerPanel);
        ui.screen.add(characterInfoPanel);
        ui.screen.add(realmPanel);
        ui.screen.add(playerNamePanel);
        ui.screen.add(martialPanel);
        ui.screen.add(martialBodyPanel);
        
        //screen transition (refreshes screen
        ui.screen.revalidate();
        ui.screen.repaint();
    }
    
}
