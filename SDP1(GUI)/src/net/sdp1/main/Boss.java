package net.sdp1.main;

public class Boss {
    
    //boss fight
    public static void finalFight() 
    {
        GameMechanic.battleSystem(new Enemy("Supreme Blossom Goo Jong Myung", 450));
        if(Player.player.health <= 0) 
        {
            GameMechanic.isRunning = false;
        }else 
        {
        //print ending
        Story.gameEnd(Player.player);
        GameMechanic.isRunning = false;
        }
    }
    
}
