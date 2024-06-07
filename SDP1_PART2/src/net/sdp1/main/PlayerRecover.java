package net.sdp1.main;

public class PlayerRecover 
{
    
    public UI ui;
    
    public PlayerRecover(UI ui) 
    {
        this.ui = ui;
    }
    
    public void recover() 
    {
        
        ui.gameTextArea.setText("Do you want to recover? \n\n[RECOVER] " + ui.player.recoverRemaining);
        
        //choices
        ui.buttonEditor(ui.action1, "Yes, my body's beat...", null);
        
        ui.buttonEditor(ui.action2, "No, I'm brimming with QI!!!", e -> ui.gameGUI());
        
        ui.buttonEditor(ui.action3, "", null);
        
        ui.buttonEditor(ui.action4, "", null);
        
    }
    
    //taking a rest
    public void recoverHealth() 
    {
        if(ui.player.recoverRemaining >= 1)
        {
            //player takes a rest
            if(ui.player.health < ui.player.maxHealth) 
            {
                int hpRestored = (int) (Math.random() * (ui.player.qi/4 + 2) + 10);
                ui.player.health += hpRestored;
                if(ui.player.health > ui.player.maxHealth)
                    ui.player.health = ui.player.maxHealth;

                ui.gameTextArea.setText("You're now at " + ui.player.health + "/" + ui.player.maxHealth + " health.");

                //choices
                ui.buttonEditor(ui.action1, "<", e -> ui.gameGUI());

                ui.buttonEditor(ui.action2, "", null);

                ui.buttonEditor(ui.action3, "", null);

                ui.buttonEditor(ui.action4, "", null); 

                ui.player.recoverRemaining--;
            }
        }
    }
}
