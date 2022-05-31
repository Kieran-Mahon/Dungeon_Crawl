package dungeon_crawl;

/*
 * @author Kieran
 */
public class StunnedScreen implements Screen {

    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println("| YOU ARE STUNNED!                          |");
        System.out.println("#-------------------------------------------#");
    }
}