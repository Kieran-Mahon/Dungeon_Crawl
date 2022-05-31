package dungeon_crawl.Utilities;

import dungeon_crawl.Enemies.Bird;
import dungeon_crawl.Enemies.Wolf;
import dungeon_crawl.Enemies.Enemy;
import dungeon_crawl.Enemies.Dragon;
import dungeon_crawl.Enemies.Knight;
import java.util.HashMap;

/*
 * @author Kieran
 */
public class EnemyNameLookUp {

    //All items the player can get
    private final HashMap<String, Enemy> enemies;

    public EnemyNameLookUp() {
        this.enemies = loadEnemies();
    }
    
    //Set up the list of items the player can use
    private HashMap<String, Enemy> loadEnemies() {
        HashMap<String, Enemy> tempEnemies = new HashMap<>();
        //Enemies
        tempEnemies.put("WOLF", new Wolf());
        tempEnemies.put("KNIGHT", new Knight());
        tempEnemies.put("DRAGON", new Dragon());
        tempEnemies.put("BIRD", new Bird());
        
        //Return map
        return tempEnemies;
    }
    
    public Enemy getItem(String key) {
        //Check if the key exists
        if (this.enemies.containsKey(key)) {
            //Return the item
            return this.enemies.get(key);   
        } else {
            //Return null if the key doesn't exist
            return null;
        }
    }
}