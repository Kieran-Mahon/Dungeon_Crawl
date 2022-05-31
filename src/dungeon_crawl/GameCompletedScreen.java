package dungeon_crawl;

/*
 * @author Kieran
 */
public class GameCompletedScreen implements Screen {
    private final String finalTime;
    private final String[] levelCompletedTimes;

    public GameCompletedScreen(String finalTime, String[] levelCompletedTimes) {
        this.finalTime = finalTime;
        this.levelCompletedTimes = levelCompletedTimes;
    }

    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println("| GAME COMPLETED!                           |");
        System.out.println("|                                           |");
        System.out.println("| FINAL TIME:                               |");
        System.out.println(Screen.lineBorder(this.finalTime));
        System.out.println("|                                           |");
        //Print the level times
        for (int i = 0; i < levelCompletedTimes.length; i++) {
            System.out.println(Screen.lineBorder("LEVEL " + (i + 1) + " TIME: " + this.levelCompletedTimes[i]));
        }
        System.out.println("|                                           |");
        System.out.println("#-------------------------------------------#");
        System.out.println("| PRESS ENTER TO CONTINUE                   |");
        System.out.println("#-------------------------------------------#");
    }
    
}