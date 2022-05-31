package dungeon_crawl;

/*
 * @author Kieran
 */
public class StartScreen implements Screen {

    @Override
    public void displayScreen() {
        System.out.println("#-------------------------------------------#");
        System.out.println("| WELCOME TO DUNGEON CRAWL                  |");
        System.out.println("|                                           |");
        System.out.println("| YOUR GOAL IS TO GET TO THE DOOR (D)       |");
        System.out.println("|                                           |");
        System.out.println("| THERE MIGHT BE SOME ENEMIES IN THE WAY    |");
        System.out.println("| BUT I'M SURE YOU CAN HANDLE THEM!         |");
        System.out.println("|                                           |");
        System.out.println("| THIS GAME AUTOMATICALLY SAVES OUTSIDE OF  |");
        System.out.println("| BATTLE                                    |");
        System.out.println("#-------------------------------------------#");
        System.out.println("| B: LEADERBOARD                            |");
        System.out.println("| L: LOAD GAME                              |");
        System.out.println("| N: NEW GAME                               |");
        System.out.println("| Q: QUIT                                   |");
        System.out.println("#-------------------------------------------#");
    }
}