package dungeon_crawl;

import java.util.ArrayList;

/*
 * @author Kieran
 */
public class GameController {
    
    //Player
    private Player player;
    //Board
    private final Board board = new Board();
    //Levels
    private ArrayList<Level> levels;
    //Player item name lookup
    private final ItemNameLookUp itemNameLookUp = new ItemNameLookUp();
    //Time manager
    private TimeManager timeManager;
    
    //Used for new game
    public GameController(Player player) {
        //Set up default level templates
        loadDefaultLevelTemplates();
        //Pass player info
        this.player = player;
        //Setup the level
        setUpLevel(-0);
    }
    
    //Used for loading game
    public GameController() {
        //Set up default level templates
        loadDefaultLevelTemplates();
    }
    
    //Default levels templates
    private void loadDefaultLevelTemplates() {
        this.levels = new ArrayList<>();
        this.levels.add(new LevelOne());
        this.levels.add(new LevelTwo());
        this.levels.add(new LevelThree());
    }
    
    private void setUpLevel(int index) {
        //Set start position of the player
        this.player.setPlayerPosition(this.levels.get(index).getPlayerStartPosition());
        //Create board using the level information
        this.board.setUpBoard(this.levels.get(index).getLevelGrid());
    }
    
    public boolean loadGame() {
        //Try to load the player and the board
        boolean errorFoundPlayer = loadPlayer();
        boolean errorFoundBoard = this.board.loadBoard();
        
        if (errorFoundPlayer && errorFoundBoard) {
            System.out.println();
            System.out.println("#-------------------------------------------#");
            System.out.println("| ERROR FOUND WHILE LOADING                 |");
            System.out.println("#-------------------------------------------#");
        } else if (errorFoundBoard == true) {
            System.out.println();
            System.out.println("#-------------------------------------------#");
            System.out.println("| ERROR FOUND IN BOARD DATA                 |");
            System.out.println("#-------------------------------------------#");
        } else if (errorFoundPlayer == true) {
            System.out.println();
            System.out.println("#-------------------------------------------#");
            System.out.println("| ERROR FOUND IN PLAYER DATA                |");
            System.out.println("#-------------------------------------------#");
        } else {
            System.out.println();
            System.out.println("#-------------------------------------------#");
            System.out.println("| GAME LOADED                               |");
            System.out.println("#-------------------------------------------#");
        }
        System.out.println("| PRESS ENTER TO CONTINUE                   |");
        System.out.println("#-------------------------------------------#");
        
        //Wait for enter key
        new InputHandler().getEnterKey();
        
       return !errorFoundPlayer && !errorFoundBoard; //Both have to be false to return true
    }
    
    public void saveGame() {
        if (IOController.savePlayer(this.player) || IOController.savePlayerItems(this.player)) { //Both return true if there is an error
            System.out.println();
            System.out.println("#-------------------------------------------#");
            System.out.println("| ERROR FOUND WHILE SAVING PLAYER DATA      |");
            System.out.println("#-------------------------------------------#");
        } else {
            //Only save the board if there are no issues with saving the player
            this.board.saveBoard(); //Error message for saving the board is within its save function
        }
    }
    
    private boolean loadPlayer() {
        boolean errorFound = false;
        //Load player info
        Player tempPlayer = IOController.loadPlayer();
        //Check if player is empty
        if (tempPlayer == null) {
            errorFound = true;
        } else {
            //Load player items
            tempPlayer = IOController.loadPlayerItems(tempPlayer);
            if (tempPlayer == null) { //Check if empty again
                errorFound = true;
            } else {
                this.player = tempPlayer; //Import player data
            }
        }
        return errorFound;
    }
    
    public void gameLoop() {
        while (true) {
            //Start timer
            this.timeManager = new TimeManager();
            
            //Call the game play
            levelGamePlay();
            
            //Stop timer - Add to the time so loaded times count towards the final time
            int time = updateCurrentTime();
            
            //Add level time to completed level times list
            this.player.getPlayerData().setLevelTime(this.player.getCurrentLevel() - 1, time);
            
            //Display level completed screen
            String levelTimeAsString = TimeManager.convertSecondToStringTime(time);
            new LevelCompletedScreen(this.player.getCurrentLevel(), levelTimeAsString).displayScreen();
            
            //Wait for enter key
            new InputHandler().getEnterKey();
            
            //Check if the final level just got completed
            if (this.player.getCurrentLevel() == 3) {
                gameCompleted();
                break;
            }
            
            //Increase current level
            this.player.setCurrentLevel(this.player.getCurrentLevel() + 1);
            
            //Set up next level
            setUpLevel(this.player.getCurrentLevel() - 1);
            
            //Reset player
            this.player.resetPlayer();
        }
    }
    
    private void levelGamePlay(){
        //Place the player on the board
        this.board.setCell(this.player.getPlayerPosition().getRow(), this.player.getPlayerPosition().getCol(), this.player);
        BoardScreen boardScreen = new BoardScreen(this.board, this.player.getCurrentLevel());
        while (true) {
            //Display the grid
            boardScreen.displayScreen();
            
            //Get game input
            InputHandler gameInput = new InputHandler(new String[] {"W", "A", "S", "D", "Q"});
            String input = gameInput.getInput();
            
            if (input.equals("Q")) {
                //Update current time
                updateCurrentTime();
                //Quit program
                saveGame();
                System.exit(0);
            } else {
                //Move player
                PlayerPosition currentPlayerPos = this.player.getPlayerPosition();
                GameObject objectInCell = movePlayer(input);
                if (objectInCell != null) {
                    if (objectInCell instanceof Enemy) { //Check if its an enemy to fight
                        BattleController battleController = new BattleController();
                        if (battleController.battle((Enemy) objectInCell, player)) {
                            //Player won
                        } else {
                            //Player lost, reset enemy and move player back
                            this.board.setCell(this.player.getPlayerPosition(), objectInCell);
                            this.board.setCell(currentPlayerPos, this.player);
                            this.player.setPlayerPosition(currentPlayerPos);
                        }
                    } else if (objectInCell instanceof Item) { //Check if its an item to pick up
                        //Replace item
                        new ItemReplacer().startItemReplacing((Item) objectInCell, this.player);
                    } else if (objectInCell instanceof Door) { //Check if its the door to finish the level
                        //Break the game loop as the the player has finished the level
                        break;
                    }
                }
            }
        }
    }
    
    private boolean tryToMovePlayer(String direction){
        boolean canMove = false;
        //Get player current location
        int row = this.player.getPlayerPosition().getRow();
        int col = this.player.getPlayerPosition().getCol();
            switch (direction) {
                case "W": //Move up
                    if (row > 0) { //Any row but the top
                        canMove = true;
                    }
                    break;
                case "A": //Move left
                    if (col > 0) { //Any col but the far left one
                        canMove = true;
                    }
                    break;
                case "S": //Move down
                    if (row < 6) { //Any row but the bottom
                        canMove = true;
                    }
                    break;
                case "D": //Move right
                    if (col < 10) { //Any col but the far right one
                        canMove = true;
                    }
                    break;
            }
        return canMove;
    }
    
    private GameObject movePlayer(String direction){
        //Object which gets returned, null means nothing there
        GameObject gameObjectToReturn = null;
        
        if (tryToMovePlayer(direction) == true) {
            //Get player current location
            int oldRow = this.player.getPlayerPosition().getRow();
            int oldCol = this.player.getPlayerPosition().getCol();
            int newRow = oldRow;
            int newCol = oldCol;
            String outputText = "| PLAYER MOVED";
            switch (direction) {
                case "W": //Move up
                    newRow = oldRow - 1;
                    outputText +=  " FORWARD                      |";
                    break;
                case "A": //Move left
                    newCol = oldCol - 1;
                    outputText +=  " LEFT                         |";
                    break;
                case "S": //Move down
                    newRow = oldRow + 1;
                    outputText +=  " DOWNWARDS                    |";
                    break;
                case "D": //Move right
                    newCol = oldCol + 1;
                    outputText +=  " RIGHT                        |";
                    break;
            }
            if (this.board.getCell(newRow, newCol) != null) {
                gameObjectToReturn = this.board.getCell(newRow, newCol);
            }
            //Update player position on the board
            this.board.updatePlayerLocation(newRow, newCol, player);
            //Update the player
            this.player.setPlayerPosition(new PlayerPosition(newRow, newCol));
            
            //Print the movement message
            System.out.println("#-------------------------------------------#");
            System.out.println(outputText);
            System.out.println("#-------------------------------------------#");
        } else {
            //Print the cannot move message
            System.out.println("#-------------------------------------------#");
            System.out.println("| YOU CANNOT MOVE THERE!                    |");
            System.out.println("#-------------------------------------------#");
        }
        return gameObjectToReturn;
    }

    private void gameCompleted() {
        //Convert the seconds of each level completed time into string time
        String[] stringLevelTimes = new String[this.player.getPlayerData().getLevelTimes().length];
        int finalTime = 0;
        for (int i = 0; i < stringLevelTimes.length; i++) {
            stringLevelTimes[i] = TimeManager.convertSecondToStringTime(this.player.getPlayerData().getLevelTime(i));
            finalTime += this.player.getPlayerData().getLevelTime(i); //Final time
        }

        //Add the final time to the PlayerData
        this.player.getPlayerData().setFinalTime(finalTime);

        //Try to add the player to the leaderboard (leader data is saved on file)
        Leaderboard lb = new Leaderboard();
        lb.tryToAdd(this.player.getPlayerData());
        
        //Convert final time from int to string time (formated time eg. 5:03)
        String finalTimeAsString = TimeManager.convertSecondToStringTime(this.player.getPlayerData().getFinalTime());
        new GameCompletedScreen(finalTimeAsString, stringLevelTimes).displayScreen();
        
        //Wait for enter key
        new InputHandler().getEnterKey();
    }
    
    private int updateCurrentTime () {
        int time = -1;
        if (this.timeManager != null) {
            time = this.player.getCurrentLevelTime() + this.timeManager.timeFinished();
            this.player.setCurrentLevelTime(time);
        }
        return time;
    }
}