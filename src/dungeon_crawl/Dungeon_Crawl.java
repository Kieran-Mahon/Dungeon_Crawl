package dungeon_crawl;

/*
 * @author Kieran
 */
public class Dungeon_Crawl {

    public static void main(String[] args) {
        
        //Main loop
        while (true) {
            //Start screen
            new StartScreen().displayScreen();
            //Get start screen input
            InputHandler startInput = new InputHandler(new String[] {"B", "L", "N", "Q"});
            String input = startInput.getInput();
            
            if (input.equals("Q")) {
                //Quit program
                //No need to save here as no data has been made
                System.exit(0);
                
            } else if (input.equals("B")) {
                Leaderboard lb = new Leaderboard();
                //Leaderboard screen
                new LeaderboardScreen(lb).displayScreen();
                //Get leaderboard screen input
                InputHandler lbInput = new InputHandler(new String[] {"B"});
                lbInput.getInput(); //Wait for b to be inputted
                
            } else { //Only else is N (new game) and L (load game)
                if (input.equals("N")) {
                    //New game screen
                    NewGameScreen newGameScreen = new NewGameScreen();
                    newGameScreen.displayScreen();
                    //Get player name
                    InputHandler newGameInput = new InputHandler();
                    input = newGameInput.getNonEmptyInput(10);
                        
                    //Put the name in uppercase
                    input = input.toUpperCase();
                    
                    //Display the welcome message
                    newGameScreen.welcomeText(input);
                    
                    //Call gamecontroller for main game logic
                    GameController gameController = new GameController(new Player(new PlayerData(input)));
                    gameController.gameLoop();
                    
                } else { //Only variable left is L (load game)
                    GameController gameController = new GameController();
                    //Try to load the game
                    if (gameController.loadGame() == true) {
                        //Main game logic
                        gameController.gameLoop();
                    }
                }
            }
            //Empty line to make the output look nice
            System.out.println();
        }
    }
}