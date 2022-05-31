package dungeon_crawl;

/*
 * @author Kieran
 */
public class LeaderboardScreen implements Screen {
    private Leaderboard lb;

    public LeaderboardScreen(Leaderboard lb) {
        this.lb = lb;
    }
        
    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println("| LEADERBOARD:                              |");
        this.lb.displayLeaderboard();
        System.out.println("#-------------------------------------------#");
        System.out.println("| B: BACK                                   |");
        System.out.println("#-------------------------------------------#");
    }

}