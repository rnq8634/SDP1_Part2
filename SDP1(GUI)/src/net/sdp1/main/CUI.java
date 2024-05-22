package net.sdp1.main;

import static net.sdp1.main.GameMechanic.location;
import static net.sdp1.main.GameMechanic.locations;

// menu
public class CUI 
{
    
    public static void actionMenu() 
    {
        Print.emptySpace();
        Print.heading(locations[location]);
        Print.center("[ACTION]", 30);
        Print.separator(30);
        System.out.println("[1] Continue on your journey");
        System.out.println("[2] Stats");
        System.out.println("[3] Save Game");
        System.out.println("[4] Exit Game");
        Print.separator(30);
    }
    
}
