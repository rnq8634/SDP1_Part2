package net.sdp1.main;

public class GameLocation {
    
    //class reference
    public GameMechanic gameMechanic;
    public UI ui;
    public PlayerRecover playerRecover;
    
    public GameLocation(UI ui) {
        this.ui = ui;
    }
    
    //game locations(will be changed later)
    public void firstArea() {
        //player location
        GameMechanic.location = 0;
        
        ui.gameTextArea.setText("You are currently at " + GameMechanic.locations[GameMechanic.location] + "\n \nWhat do you plan to do?");
        
        //actions
        ui.buttonEditor(ui.action1, "Attend your first mission", e -> secondArea());
        
        ui.buttonEditor(ui.action2, "Go and fight", null);
        
        ui.buttonEditor(ui.action3, "Explore the area", e -> locationDescription());
        
        ui.buttonEditor(ui.action4, "Take a rest", e -> ui.playerRecover.recover());
    }
    
    public void secondArea() 
    {
        //checks if the player is strong enough to move up the next stage(prevents from going to the last stage too quickly)
        if (ui.player.qi >= 40) 
        {
            //game position/location
            GameMechanic.location = 1;
            
            //game text
            ui.gameTextArea.setText("You are currently at " + GameMechanic.locations[GameMechanic.location] + "\n \nWhat do you plan to do?");
            
            //actions
            ui.buttonEditor(ui.action1, "Attend your second mission", e -> thirdArea());
            
            ui.buttonEditor(ui.action2, "Go and fight", null);
            
            ui.buttonEditor(ui.action3, "Explore the area", e -> locationDescription());//encounter shops or description of the area
            
            ui.buttonEditor(ui.action4, "Take a rest", e -> ui.playerRecover.recover());
        } else 
        {
            ui.gameTextArea.setText("You are too weak, maybe increase your QI to 40 or above.");
            
            //actions
            ui.buttonEditor(ui.action1, "<", e -> ui.gameGUI());
            
            ui.buttonEditor(ui.action2, "", null);
            
            ui.buttonEditor(ui.action3, "", null);
            
            ui.buttonEditor(ui.action4, "", null);
        }
    }
    
    public void thirdArea() 
    {
        //checks if the player is strong enough to move up the next stage(prevents from going to the last stage too quickly)
        if (ui.player.qi >= 100) 
        {
            //game position/location
            GameMechanic.location = 2;
            
            //game text
            ui.gameTextArea.setText("You are currently at " + GameMechanic.locations[GameMechanic.location] + "\n \nWhat do you plan to do?");
            
            //actions
            ui.buttonEditor(ui.action1, "Head up the tall mountains", e -> thirdArea());
            
            ui.buttonEditor(ui.action2, "Go and fight", null);
            
            ui.buttonEditor(ui.action3, "Explore the area", e -> locationDescription());//encounter shops or description of the area
            
            ui.buttonEditor(ui.action4, "Take a rest", e -> ui.playerRecover.recover());
        } else 
        {
            ui.gameTextArea.setText("You are too weak, maybe increase your QI to 100 or above.");
            
            //actions
            ui.buttonEditor(ui.action1, "<", e -> ui.gameGUI());
            
            ui.buttonEditor(ui.action2, "", null);
            
            ui.buttonEditor(ui.action3, "", null);
            
            ui.buttonEditor(ui.action4, "", null);
        }
    }
    
    public void fourthArea() 
    {
        //checks if the player is strong enough to move up the next stage(prevents from going to the last stage too quickly)
        if (ui.player.qi >= 250) 
        {
            //game position/location
            GameMechanic.location = 3;
            
            //game text
            ui.gameTextArea.setText("You are currently at " + GameMechanic.locations[GameMechanic.location] + "\n \nWhat do you plan to do?");
            
            //actions
            ui.buttonEditor(ui.action1, "Face him...", e -> thirdArea());
            
            ui.buttonEditor(ui.action2, "Go and fight", null);
            
            ui.buttonEditor(ui.action3, "Explore the area", e -> locationDescription());//encounter shops or description of the area
            
            ui.buttonEditor(ui.action4, "Take a rest", e -> ui.playerRecover.recover());
        } else 
        {
            ui.gameTextArea.setText("You are too weak, maybe increase your QI to 250 or above.");
            
            //actions
            ui.buttonEditor(ui.action1, "<", e -> ui.gameGUI());
            
            ui.buttonEditor(ui.action2, "", null);
            
            ui.buttonEditor(ui.action3, "", null);
            
            ui.buttonEditor(ui.action4, "", null);
        }
    }
    
    //add location descriptions
    public void locationDescription() 
    {
        
        switch (GameMechanic.location) 
        {
            case 0:
                
                ui.gameTextArea.setText("This is the Demonic Barracks. ");
                
                ui.buttonEditor(ui.action1, "<", e -> ui.gameGUI());
                
                ui.buttonEditor(ui.action2, "Talk to the instructor", null);
                
                ui.buttonEditor(ui.action3, "", null);
                
                ui.buttonEditor(ui.action4, "", null);
                
                break;
            case 1:
                
                ui.gameTextArea.setText("");
                
                ui.buttonEditor(ui.action1, "<", null);
                
                ui.buttonEditor(ui.action2, "", null);
                
                ui.buttonEditor(ui.action3, "", null);
                
                ui.buttonEditor(ui.action4, "", null);
                
                break;
            case 2:
                
                ui.gameTextArea.setText("");
                
                ui.buttonEditor(ui.action1, "<", null);
                
                ui.buttonEditor(ui.action2, "", null);
                
                ui.buttonEditor(ui.action3, "", null);
                
                ui.buttonEditor(ui.action4, "", null);
                
                break;
            case 3:
                
                ui.gameTextArea.setText("");
                
                ui.buttonEditor(ui.action1, "<", null);
                
                ui.buttonEditor(ui.action2, "", null);
                
                ui.buttonEditor(ui.action3, "", null);
                
                ui.buttonEditor(ui.action4, "", null);
                
                break;
            case 4:
                
                ui.gameTextArea.setText("");
                
                ui.buttonEditor(ui.action1, "<", null);
                
                ui.buttonEditor(ui.action2, "", null);
                
                ui.buttonEditor(ui.action3, "", null);
                
                ui.buttonEditor(ui.action4, "", null);
                
                break;
            default:
                break;
        }
        
    }
    
}
