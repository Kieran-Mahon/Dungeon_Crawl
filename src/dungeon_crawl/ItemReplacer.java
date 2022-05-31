package dungeon_crawl;

/*
 * @author Kieran
 */
public class ItemReplacer {
    
    public void startItemReplacing(Item newItem, Player player) {
        //Show replacing screen
        new ItemReplacingScreen(newItem, player.getItems()).displayScreen();

        String input = new InputHandler(new String[] {"Q", "W", "E", "R", "X"}).getInput();
        int slot;
        switch (input) { //Q,W,E, and R are items to replace, X is drop the new item
            case "Q":
                slot = 0;
                break;
            case "W":
                slot = 1;
                break;
            case "E":
                slot = 2;
                break;
            case "R":
                slot = 3;
                break;
            default:
                //Drop the item
                slot = -1;
                break;
        }
        if (slot != -1) {
            //Replace item
            player.setItem(slot, newItem);
            
            //Display item replaced screen
            new ItemReplacedScreen().displayScreen();
        }
    }
}