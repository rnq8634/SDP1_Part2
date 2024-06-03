package net.sdp1.main;

//shop
public class Shop 
{
    
    public static void wanderingTrader() 
    {
        
        System.out.println("You came across a hooded stranger.\nHe seems eager to trade for some gold.");
        
        int price = 15;
        System.out.println("- Elixir: " + price + " gold.");
        
        // ask the player if they want to buy one
        System.out.println("[GOLD] " + Player.player.gold);
        
        System.out.println("Do you want to buy this item?\n[1] Yes, I need it.\n[2] No thanks.");
        //int input = Print.userInput("-> ", 2);
        //check if the player confirms
        //if(input == 1) 
        {
            
            //check if the player has enough gold
            if(Player.player.gold >= price) 
            {
                
                System.out.println("You bought an Elixir for " + price + " gold.");
                
                Player.player.elix++;
                Player.player.gold -= price;
                
            } else 
            {
                
                System.out.println("You don't have enough gold...");
                
            }
        } //else {
            
            System.out.println("The hooded stranger waves to you as he disappears into mist.");
            
        }
    }
    

