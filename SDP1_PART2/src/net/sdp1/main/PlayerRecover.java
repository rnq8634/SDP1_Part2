package net.sdp1.main;

public class PlayerRecover {
    
    public UI ui;
    
    public PlayerRecover(UI ui) {
        this.ui = ui;
    }
    
    public void recover() {
        
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
        if(ui.player.recoverRemaining >= 1 ) 
        {
            System.out.println("Do you want to recover?");
            System.out.println("[RECOVER] " + ui.player.recoverRemaining);
            
            System.out.println("[1] Yes, my body's beat...\n[2] No, I'm brimming with QI!!!");
      
                //player takes a rest
                if(ui.player.health < ui.player.maxHealth) 
                {
                    int hpRestored = (int) (Math.random() * (ui.player.qi/4 + 2) + 10);
                    ui.player.health += hpRestored;
                    if(ui.player.health > ui.player.maxHealth)
                        ui.player.health = ui.player.maxHealth;
                    
                    System.out.println("You did Restorative Breathing and regained "+ hpRestored + " health.");
                    
                    System.out.println("You're now at " + ui.player.health + "/" + ui.player.maxHealth + " health.");
                    
                    ui.player.recoverRemaining--;
                }else if(ui.player.health == ui.player.maxHealth) 
                {
                    System.out.println("There is no reason to rest since you are at full health!");
                }
            }else 
            {
                System.out.println("You felt that there is no reason to rest.");
            }
        }
    }
