package dungeon_crawl;

/*
 * @author Kieran
 */
public class LevelTwo extends Level {
    private final PlayerPosition playerStartPosition = new PlayerPosition(6, 0); //ROW (7), COL (11)

    @Override
    public PlayerPosition getPlayerStartPosition() {
        return this.playerStartPosition;
    }
    
    @Override
    public GameObject[][] getLevelGrid() {
        GameObject[][] grid = {
            {null, null, null, null, null, null, null, null, null, new Dragon(), new Door()},
            {null, null, new Bird(), null, null, null, null, null, null, new Knight(), new Knight()},
            {null, null, new Bird(), null, null, new Knight(), null, null, null, null, new Knight()},
            {null, null, new Bird(), new Sword(), new Knight(), new HealthPotion(), new Knight(), null, null, null, null},
            {null, null, new Bird(), null, null, new Knight(), null, null, null, null, null},
            {null, null, new Bird(), null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null, null}
        };
        return grid;
    }
}