package dungeon_crawl;

/*
 * @author Kieran
 */
public abstract class Level {
    
    public abstract PlayerPosition getPlayerStartPosition();
    public abstract GameObject[][] getLevelGrid();
}