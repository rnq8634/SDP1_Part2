package net.sdp1.main;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class PlayerTrait 
{
    
    public UI ui;
    
    public PlayerTrait(UI ui) 
    {
        this.ui = ui;
    }
    
    public void martialScroll() 
    {
        ui.playerWindowButton.setVisible(false);
        ui.saveButton.setVisible(false);
        //game text
        ui.gameTextArea.setText("Choose your trait");
        
        //actions
        ui.buttonEditor(ui.action1, "Luminous Star Sword", e -> chosenSkill(e, "0"));
            
        ui.buttonEditor(ui.action2, "Supreme Demon Sword Art", e -> chosenSkill(e, "1"));
            
        ui.buttonEditor(ui.action3, "Extreme Art of the Blade God", e -> chosenSkill(e, "2"));
            
        ui.buttonEditor(ui.action4, "Sword Art of the Heavenly Demon", e -> chosenSkill(e, "3"));
    }
    
    public void martialScrollBody() 
    {
        //game text
        ui.gameTextArea.setText("Choose your trait");
        
        //actions
        ui.buttonEditor(ui.action1, "Cool Blood Blockage", e -> chosenBody(e, "0"));
            
        ui.buttonEditor(ui.action2, "Extreme Martial Body", e -> chosenBody(e, "1"));
            
        ui.buttonEditor(ui.action3, "Heavenly Killing Star", e -> chosenBody(e, "2"));
            
        ui.buttonEditor(ui.action4, "Heavenly Body", e -> chosenBody(e, "3"));
    }
    
    public void chosenSkill(ActionEvent e, String actionCommand) {
        int chosenSkill = Integer.parseInt(actionCommand);
        
        switch (chosenSkill) {
            case 0://Luminous Star Sword
                ui.player.numMartialSkill = 0;
                
                ui.gameTextArea.setText("You have chosen " + ui.player.martialSkill[ui.player.numMartialSkill]);
                break;
            case 1://Supreme Demon Sword Art
                ui.player.numMartialSkill = 1;
                
                ui.gameTextArea.setText("You have chosen " + ui.player.martialSkill[ui.player.numMartialSkill]);
                break;
            case 2://Extreme Art of the Blade God
                if(ui.player.getQi() > 100) {
                    ui.player.numMartialSkill = 2;
                
                    ui.gameTextArea.setText("You have chosen " + ui.player.martialSkill[ui.player.numMartialSkill]);
                } else {
                    JOptionPane.showMessageDialog(ui.screen, "Your QI is too low for this skill!");
                    return;
                }
                break;
            case 3://Sword Art of the Heavenly Demon
                if(ui.player.getQi() > 200) {
                    ui.player.numMartialSkill = 3;
                
                    ui.gameTextArea.setText("You have chosen " + ui.player.martialSkill[ui.player.numMartialSkill]);
                } else {
                    JOptionPane.showMessageDialog(ui.screen, "You must first learn the art of the Heavenly Demon!");
                    return;
                }
                break;
            default:
                break;
        }
        
        //actions       
        ui.buttonEditor(ui.action1, ">", ev -> martialScrollBody());
                
        ui.buttonEditor(ui.action2, "", null);
                
        ui.buttonEditor(ui.action3, "", null);
                
        ui.buttonEditor(ui.action4, "", null);
        
    }
    
    public void chosenBody(ActionEvent e, String actionCommand) {
        
        int chosenBody = Integer.parseInt(actionCommand);
        
        switch (chosenBody) {
            case 0://Cool Blood Blockage
                ui.player.numMartialBody = 0;
                
                ui.gameTextArea.setText("You have chosen " + ui.player.martialBody[ui.player.numMartialBody]);
                break;
            case 1://Extreme Martial Body
                ui.player.numMartialBody = 1;
                
                ui.gameTextArea.setText("You have chosen " + ui.player.martialBody[ui.player.numMartialBody]);
                break;
            case 2://Heavenly Killing Star
                ui.player.numMartialBody = 2;
                
                ui.gameTextArea.setText("You have chosen " + ui.player.martialBody[ui.player.numMartialBody]);
                break;
            case 3://Heavenly Body
                ui.player.numMartialBody = 3;
                
                ui.gameTextArea.setText("You have chosen " + ui.player.martialBody[ui.player.numMartialBody]);
                break;
            default:
                break;
        }
        
        //actions       
        ui.buttonEditor(ui.action1, ">", ev -> ui.gameGUI());
                
        ui.buttonEditor(ui.action2, "", null);
                
        ui.buttonEditor(ui.action3, "", null);
                
        ui.buttonEditor(ui.action4, "", null);
    }
}
