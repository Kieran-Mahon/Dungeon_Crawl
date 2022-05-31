package dungeon_crawl;

/*
 * @author Kieran
 */
public class BattleWonScreen implements Screen {
    
    private final Enemy enemy;

    public BattleWonScreen(Enemy enemy) {
        this.enemy = enemy;
    }
    
    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println(Screen.lineBorder("YOU KILLED " + this.enemy.getName() + "!"));
        System.out.println("|                                           |");
        if (this.enemy.getReward() != null) { //Only show if the enemy has death rewards
            System.out.println("| REWARD:                                   |");
            System.out.println(Screen.lineBorder(this.enemy.getReward().toString()));
        }
        System.out.println("#-------------------------------------------#");
        System.out.println("| YOU HAVE BEEN HEALED                      |");
        System.out.println("#-------------------------------------------#");
        System.out.println("| PRESS ENTER TO CONTINUE                   |");
        System.out.println("#-------------------------------------------#");
    }
}