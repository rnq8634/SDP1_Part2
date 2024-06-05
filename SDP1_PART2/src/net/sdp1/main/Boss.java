package net.sdp1.main;

public class Boss 
{
    
    public UI ui;
    
    //boss fight
    public void finalFight() 
    {
        ui.combat.battleSystem(new Enemy("Supreme Blossom Goo Jong Myung", 450));
    }
}
