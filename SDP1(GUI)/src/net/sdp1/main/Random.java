package net.sdp1.main;

public class Random {
    
    //method to calculate random encounter with the enemy
    /*public static void encounter() 
    {
        //random number between 0 and the length of the encounters array
        int turn = (int) (Math.random() * GameMechanic.turns.length);
        //calling the respective methods
        if(GameMechanic.turns[turn].equals("Battle")) 
        {
            enemyFight();
        }else if(GameMechanic.turns[turn].equals("Recover")) 
        {
            Player.recover();
        } else {
            Shop.wanderingTrader();
        }
    }*/
    
    //random battle method
    public static void enemyFight() 
    {
        Print.emptySpace();
        Print.separator(45);
        System.out.println("[NOTIFICATION] Prepare for an encounter!!!");
        Print.separator(45);
        Print.enterOneToContinue();
        // method for random enemy name
        GameMechanic.battleSystem(new Enemy(GameMechanic.enemies[(int)(Math.random() * GameMechanic.enemies.length)], Player.player.qi));
    }
    
}
