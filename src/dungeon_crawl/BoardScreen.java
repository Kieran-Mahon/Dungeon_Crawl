package dungeon_crawl;

/*
 * @author Kieran
 */
public class BoardScreen implements Screen {
    private Board board;
    private int currentLevel;

    public BoardScreen(Board board, int currentLevel) {
        this.board = board;
        this.currentLevel = currentLevel;
    }

    @Override
    public void displayScreen() {
        System.out.println();
        System.out.println("#-------------------------------------------#");
        System.out.println(Screen.lineBorder("LEVEL " + currentLevel));
        System.out.println("|                                           |");
        System.out.println("| E - ENEMY, I - ITEM, D - DOOR,            |");
        System.out.println("| X - PLAYER                                |");
        System.out.println("#-------------------------------------------#");
        this.board.displayBoard();
        System.out.println("#-------------------------------------------#");
        System.out.println("| W: MOVE UP                                |");
        System.out.println("| A: MOVE LEFT                              |");
        System.out.println("| S: MOVE DOWN                              |");
        System.out.println("| D: MOVE RIGHT                             |");
        System.out.println("| Q: QUIT (GAME AUTOMATICALLY SAVES)        |");
        System.out.println("#-------------------------------------------#");
    }
        
}