package dungeon_crawl;

import java.util.HashMap;
/*
 * @author Kieran
 */
public class ItemNameLookUp {
    
    //All items the player can get
    private final HashMap<String, Item> playerItems;

    public ItemNameLookUp() {
        this.playerItems = loadItems();
    }
    
    //Set up the list of items the player can use
    private HashMap<String, Item> loadItems() {
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
    
    public Item getItem(String key) {
        //Check if the key exists
        if (this.playerItems.containsKey(key)) {
            //Return the item
            return this.playerItems.get(key);   
        } else {
            //Return null if the key doesn't exist
            return null;
        }
    }
}