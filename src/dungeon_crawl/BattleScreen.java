package dungeon_crawl;

/*
 * @author Kieran
 */
public class BattleScreen implements Screen {
    private final Enemy enemy;
    private final Player player;
    private final boolean displayItems;
    
    public BattleScreen(Enemy enemy, Player player, boolean displayItems) {
        this.enemy = enemy;
        this.player = player;
        this.displayItems = displayItems;
    }
    
    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println(Screen.lineBorder(this.enemy.getName()));
        System.out.println("|                                           |");
        System.out.println(Screen.lineBorder("HEALTH: " + this.enemy.getHealth()));
        System.out.println("#-------------------------------------------#");
        System.out.println(Screen.lineBorder("YOUR HEALTH: " + this.player.getHealth()));
        //Only show the items when the constructpr with parameters is called
        if (this.displayItems == true) {
            System.out.println("|                                           |");
            System.out.println(Screen.lineBorder("Q: " + this.player.getItems().get(0)));
            System.out.println(Screen.lineBorder("W: " + this.player.getItems().get(1)));
            System.out.println(Screen.lineBorder("E: " + this.player.getItems().get(2)));
            System.out.println(Screen.lineBorder("R: " + this.player.getItems().get(3)));
        }
        System.out.println("#-------------------------------------------#");
    }
    
}