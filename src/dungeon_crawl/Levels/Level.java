package dungeon_crawl.Levels;

import dungeon_crawl.GameObject;
import dungeon_crawl.PlayerPosition;

/*
 * @author Kieran
 */
public abstract class Level {
    
    public abstract PlayerPosition getPlayerStartPosition();
    public abstract GameObject[][] getLevelGrid();
}