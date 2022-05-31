package dungeon_crawl;

/*
 * @author Kieran
 */
public class NewGameScreen implements Screen {

    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println("| STARTING NEW GAME                         |");
        System.out.println("|-------------------------------------------|");
        System.out.println("| WHAT IS YOUR NAME?                        |");
        System.out.println("#-------------------------------------------#");
    }
    
    public void welcomeText(String name) {
        System.out.println("#-------------------------------------------#");
        System.out.println(Screen.lineBorder("WELCOME " + name.toUpperCase() + "!"));
        System.out.println("#-------------------------------------------#");
    }
}