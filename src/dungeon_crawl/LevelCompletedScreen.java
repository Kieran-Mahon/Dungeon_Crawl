package dungeon_crawl;

/*
 * @author Kieran
 */
public class LevelCompletedScreen implements Screen {
    private final int level;
    private final String timeTaken;

    public LevelCompletedScreen(int level, String timeTaken) {
        this.level = level;
        this.timeTaken = timeTaken;
    }
    
    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println(Screen.lineBorder("LEVEL " + this.level + " COMPLETED!"));
        System.out.println("|                                           |");
        System.out.println("| TIME TAKEN:                               |");
        System.out.println(Screen.lineBorder(this.timeTaken));
        System.out.println("#-------------------------------------------#");
        System.out.println("| YOUR CHARACTER HAS BEEN RESET!            |");
        System.out.println("#-------------------------------------------#");
        System.out.println("| PRESS ENTER TO CONTINUE                   |");
        System.out.println("#-------------------------------------------#");
    }
    
}