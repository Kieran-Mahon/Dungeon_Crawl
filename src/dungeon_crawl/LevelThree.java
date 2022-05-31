package dungeon_crawl;

/*
 * @author Kieran
 */
public class LevelThree extends Level {
    private final PlayerPosition playerStartPosition = new PlayerPosition(3, 0); //ROW (7), COL (11)

    @Override
    public PlayerPosition getPlayerStartPosition() {
        return this.playerStartPosition;
    }
    
    @Override
    public GameObject[][] getLevelGrid() {
        GameObject[][] grid = {
            {null, null, null, new HealthPotion(), null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null, null},
            {null, null, null, new Knight(), null, null, null, null, null, null, new Dragon()},
            {null, null, new Knight(), new Sword(), new Bird(), null, null, null, null, new Dragon(), new Door()},
            {null, null, null, new Knight(), null, null, null, null, null, null, new Bird()},
            {null, null, null, null, null, null, null, null, null, null, null},
            {null, null, null, new HealthPotion(), null, null, null, null, null, null, null}
        };
        return grid;
    }
}