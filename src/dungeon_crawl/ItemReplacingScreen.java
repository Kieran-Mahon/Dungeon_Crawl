package dungeon_crawl;

import java.util.ArrayList;

/*
 * @author Kieran
 */
public class ItemReplacingScreen implements Screen {
    private final Item newItem;
    private final ArrayList<Item> items;

    public ItemReplacingScreen(Item newItem, ArrayList<Item> items) {
        this.newItem = newItem;
        this.items = items;
    }
    
    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println(Screen.lineBorder(newItem.toString()));
        System.out.println("#-------------------------------------------#");
        System.out.println("| WHICH SLOT REPLACE                        |");
        System.out.println("|                                           |");
        System.out.println(Screen.lineBorder("Q: " + items.get(0)));
        System.out.println(Screen.lineBorder("W: " + items.get(1)));
        System.out.println(Screen.lineBorder("E: " + items.get(2)));
        System.out.println(Screen.lineBorder("R: " + items.get(3)));
        System.out.println("|                                           |");
        System.out.println("| X: CANCEL REPLACING                       |");
        System.out.println("#-------------------------------------------#");
    }

}