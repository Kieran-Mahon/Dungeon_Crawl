package dungeon_crawl;

/*
 * @author Kieran
 */
public class BattleLostScreen implements Screen {
    
    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println("| YOU DIED!                                 |");
        System.out.println("#-------------------------------------------#");
        System.out.println("| YOU HAVE BEEN HEALED                      |");
        System.out.println("| YOU HAVE BEEN MOVED BACK TO LAST LOCATION |");
        System.out.println("#-------------------------------------------#");
        System.out.println("| PRESS ENTER TO CONTINUE                   |");
        System.out.println("#-------------------------------------------#");
    }
}