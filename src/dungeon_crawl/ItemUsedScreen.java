package dungeon_crawl;

import dungeon_crawl.Items.Item;

/*
 * @author Kieran
 */
public class ItemUsedScreen implements Screen {
    private final String name;
    private final Item item;
    private final boolean showContinue;

    public ItemUsedScreen(String name, Item item, boolean showContinue) {
        this.name = name;
        this.item = item;
        this.showContinue = showContinue;
    }
    
    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println(Screen.lineBorder(this.name + " USED " + this.item.getName() + "!"));
        System.out.println("#-------------------------------------------#");
        //Show/hide the enter message
        if (showContinue == true) {
            System.out.println("| PRESS ENTER TO CONTINUE                   |");
            System.out.println("#-------------------------------------------#");
        }
    }
}