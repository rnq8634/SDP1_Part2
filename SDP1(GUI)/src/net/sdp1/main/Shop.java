package net.sdp1.main;

//shop
public class Shop 
{
    
    public static void wanderingTrader() 
    {
        Print.emptySpace();
        Print.separator(50);
        System.out.println("You came across a hooded stranger.\nHe seems eager to trade for some gold.");
        Print.separator(50);
        int price = 15;
        System.out.println("- Elixir: " + price + " gold.");
        Print.separator(20);
        // ask the player if they want to buy one
        System.out.println("[GOLD] " + Player.player.gold);
        Print.separator(20);
        System.out.println("Do you want to buy this item?\n[1] Yes, I need it.\n[2] No thanks.");
        int input = Print.userInput("-> ", 2);
        //check if the player confirms
        if(input == 1) 
        {
            Print.emptySpace();
            //check if the player has enough gold
            if(Player.player.gold >= price) 
            {
                Print.separator(35);
                System.out.println("You bought an Elixir for " + price + " gold.");
                Print.separator(35);
                Player.player.elix++;
                Player.player.gold -= price;
                Print.enterOneToContinue();
            } else 
            {
                Print.separator(30);
                System.out.println("You don't have enough gold...");
                Print.separator(30);
                Print.enterOneToContinue();
            }
        } else {
            Print.emptySpace();
            Print.separator(45);
            System.out.println("The hooded stranger waves to you as he disappears into mist.");
            Print.separator(45);
            Print.enterOneToContinue();
        }
    }
    
}
