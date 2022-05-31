package dungeon_crawl.Levels;

import dungeon_crawl.Door;
import dungeon_crawl.Enemies.Bird;
import dungeon_crawl.Enemies.Wolf;
import dungeon_crawl.Enemies.Knight;
import dungeon_crawl.GameObject;
import dungeon_crawl.Items.Sword;
import dungeon_crawl.Items.Shield;
import dungeon_crawl.PlayerPosition;

/*
 * @author Kieran
 */
public class LevelOne extends Level {
    private final PlayerPosition playerStartPosition = new PlayerPosition(6, 5); //ROW (7), COL (11)

    @Override
    public PlayerPosition getPlayerStartPosition() {
        return this.playerStartPosition;
    }
    
    @Override
    public GameObject[][] getLevelGrid() {
        GameObject[][] grid = { 
            {new Door(), new Knight(), null, null, null, null, null, null, null, null, new Sword()},
            {new Knight(), null, null, null, null, null, new Wolf(), null, null, null, null},
            {null, null, null, null, null, null, null, null, null, new Shield(), null},
            {null, null, null, null, null, new Wolf(), null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null, null},
            {new Wolf(), null, null, null, null, new Bird(), null, null, new Wolf(), null, new Wolf()},
            {new Wolf(), new Wolf(), null, null, null, null, null, null, null, new Wolf(), new Wolf()}
        };
        return grid;
    }
}