package net.sdp1.main;

public class PlayerDeath 
{
    
    public UI ui;
    
    public PlayerDeath(UI ui) 
    {
        this.ui = ui;
    }
    
    //end results of game
    public void death() 
    {
        ui.playerPanel.setVisible(false);
        ui.actionMenuPanel.setVisible(false);
        ui.playerWindowButton.setVisible(false);
        ui.saveButton.setVisible(false);
        
        ui.gameTextArea.setText("You can feel your QI seeping away as you leave this corporeal realm behind...");
        
        ui.screen.revalidate();
        ui.screen.repaint();
    }
    
}
