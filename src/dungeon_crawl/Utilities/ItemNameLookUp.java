package dungeon_crawl.Utilities;

import dungeon_crawl.Items.GroundSlam;
import dungeon_crawl.Items.HealthPotion;
import dungeon_crawl.Items.Item;
import dungeon_crawl.Items.Sword;
import dungeon_crawl.Items.Scratch;
import dungeon_crawl.Items.Shield;
import dungeon_crawl.Items.Punch;
import dungeon_crawl.Items.FireBall;
import dungeon_crawl.Items.BigBite;
import dungeon_crawl.Items.Flight;
import dungeon_crawl.Items.Bite;
import dungeon_crawl.Items.StunStick;
import java.util.HashMap;
/*
 * @author Kieran
 */
public class ItemNameLookUp {
    
    //Set up the list of items the player can use
    private static HashMap<String, Item> loadItems() {
        HashMap<String, Item> items = new HashMap<>();
        
        //Used by the player (player can use any type of item if the game gives it to them)
        items.put("PUNCH", new Punch());
        items.put("HEALTH_POTION", new HealthPotion());
        items.put("STUN_STICK", new StunStick());
        //Used for the wolf
        items.put("BITE", new Bite());
        items.put("BIG_BITE", new BigBite());
        //Used by the knight
        items.put("SHIELD", new Shield());
        items.put("SWORD", new Sword());
        //Used by the dragon
        items.put("FIRE_BALL", new FireBall());
        items.put("GROUND_SLAM", new GroundSlam());
        //Used by the bird
        items.put("FLIGHT", new Flight());
        items.put("SCRATCH", new Scratch());
        
        //Return map
        return items;
    }
    
    public static Item getItem(String key) {
        //Create new map
        HashMap<String, Item> playerItems = loadItems();
        
        //Check if the key exists
        if (playerItems.containsKey(key)) {
            //Return the item
            return playerItems.get(key);   
        } else {
            //Return null if the key doesn't exist
            return null;
        }
    }
}