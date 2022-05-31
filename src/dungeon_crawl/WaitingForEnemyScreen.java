package dungeon_crawl;

/*
 * @author Kieran
 */
public class WaitingForEnemyScreen implements Screen {
    private final Enemy enemy;

    public WaitingForEnemyScreen(Enemy enemy) {
        this.enemy = enemy;
    }
    
    @Override
    public void displayScreen() {
        System.out.println(Screen.lineBorder("WAITING FOR " + this.enemy.getName() + "..."));
        System.out.println("#-------------------------------------------#");
    }
}