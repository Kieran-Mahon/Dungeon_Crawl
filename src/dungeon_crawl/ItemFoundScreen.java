package dungeon_crawl;

/*
 * @author Kieran
 */
public class ItemFoundScreen implements Screen {
    private final String itemName;

    public ItemFoundScreen(String itemName) {
        this.itemName = itemName;
    }
    
    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println(Screen.lineBorder("YOU FOUND A " + itemName));
        System.out.println("#-------------------------------------------#");
    }
    
}