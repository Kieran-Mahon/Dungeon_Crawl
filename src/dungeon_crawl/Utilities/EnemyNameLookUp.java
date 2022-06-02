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

    //Set up the list of items the player can use
    private static HashMap<String, Enemy> loadEnemies() {
        HashMap<String, Enemy> tempEnemies = new HashMap<>();
        //Enemies
        tempEnemies.put("WOLF", new Wolf());
        tempEnemies.put("KNIGHT", new Knight());
        tempEnemies.put("DRAGON", new Dragon());
        tempEnemies.put("BIRD", new Bird());
        
        //Return map
        return tempEnemies;
    }
    
    public static Enemy getItem(String key) {
        //Make new map
        HashMap<String, Enemy> enemies = loadEnemies();
        
        //Check if the key exists
        if (enemies.containsKey(key)) {
            //Return the item
            return enemies.get(key);   
        } else {
            //Return null if the key doesn't exist
            return null;
        }
    }
}