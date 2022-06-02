package dungeon_crawl.Controllers;

import dungeon_crawl.Board;
import dungeon_crawl.Door;
import dungeon_crawl.Levels.LevelOne;
import dungeon_crawl.Levels.LevelThree;
import dungeon_crawl.Levels.LevelTwo;
import dungeon_crawl.Levels.Level;
import dungeon_crawl.Items.Item;
import dungeon_crawl.Enemies.Enemy;
import dungeon_crawl.GameObject;
import dungeon_crawl.ItemReplacer;
import dungeon_crawl.Leaderboard;
import dungeon_crawl.Player;
import dungeon_crawl.PlayerPosition;
import dungeon_crawl.Utilities.TimeManager;
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
    //Time manager
    private TimeManager timeManager;
    //View controller
    private DungeonCrawl dungeonCrawl;
    //Battle controller
    private BattleController battleController;
    
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
        return !loadPlayer() && !this.board.loadBoard(); //Both have to be false to return true
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
    
    public void gameLogic(DungeonCrawl dc) {
        //Assign view controller reference
        this.dungeonCrawl = dc;
        
        //Start timer
        this.timeManager = new TimeManager();
        
        //Place player on the board
        this.board.setCell(this.player.getPlayerPosition().getRow(), this.player.getPlayerPosition().getCol(), this.player);
        
        //Update board
        displayBoard();
        
        //Update level text
        this.dungeonCrawl.getViewController().getGamePanel().updateLevelLabel("LEVEL " + this.player.getCurrentLevel());
    }
    
    private void displayBoard() {
        this.dungeonCrawl.getViewController().getGamePanel().updateGrid(this.board.displayBoard());
    }
    
    public void playerMoveAction(String dir) {
        //Move player
        PlayerPosition currentPlayerPos = this.player.getPlayerPosition();
        GameObject objectInCell = movePlayer(dir);
        boolean updateBoard = true;
        if (objectInCell != null) {
            if (objectInCell instanceof Enemy) { //Check if its an enemy to fight
                //Save game before fight in case of quitting during fight
                updateCurrentTime();
                saveGame();                
                //Start fight
                this.battleController = new BattleController(currentPlayerPos, this.dungeonCrawl);
                this.battleController.startBattle((Enemy) objectInCell, player);
                
            } else if (objectInCell instanceof Item) { //Check if its an item to pick up
                //Replace item
                
                
                new ItemReplacer().startItemReplacing((Item) objectInCell, this.player);
                
                
                displayBoard();
            } else if (objectInCell instanceof Door) { //Check if its the door to finish the level
                //Complete level as the player has finished the level
                completeLevel();
                updateBoard = false;
            }
        }
        
        //Everything BUT landing on a door can update the screen
        if (updateBoard == true) {
            displayBoard();
        }
    }
    
    public void movePlayerBack(GameObject objectInCell, PlayerPosition currentPlayerPos) {
        //Player lost, reset enemy and move player back
        Enemy enemyInCell = (Enemy)objectInCell;
        enemyInCell.resetEnemy();
        this.board.setCell(this.player.getPlayerPosition(), enemyInCell);
        this.board.setCell(currentPlayerPos, this.player);
        this.player.setPlayerPosition(currentPlayerPos);
        displayBoard();
    }
    
    private void completeLevel() {
        //Stop timer - Add to the time so loaded times count towards the final time
        int time = updateCurrentTime();

        //Add level time to completed level times list
        this.player.getPlayerData().setLevelTime(this.player.getCurrentLevel() - 1, time);

        //Display level completed screen
        String levelTimeAsString = TimeManager.convertSecondToStringTime(time);
        
        //Show level completed panel
        this.dungeonCrawl.getViewController().switchPanels(ViewController.Panel.LEVELCOMPLETED);
        
        //Update text on level completed panel
        this.dungeonCrawl.getViewController().getLevelCompletedPanel().updateText("LEVEL " + this.player.getCurrentLevel() + " COMPLETED!", levelTimeAsString);
        
        //Increase current level
        this.player.setCurrentLevel(this.player.getCurrentLevel() + 1);

        //Set up next level
        setUpLevel(this.player.getCurrentLevel() - 1);

        //Reset player
        this.player.resetPlayer();
        
        //Save game
        saveGame();
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
            switch (direction) {
                case "W": //Move up
                    newRow = oldRow - 1;
                    break;
                case "A": //Move left
                    newCol = oldCol - 1;
                    break;
                case "S": //Move down
                    newRow = oldRow + 1;
                    break;
                case "D": //Move right
                    newCol = oldCol + 1;
                    break;
            }
            if (this.board.getCell(newRow, newCol) != null) {
                gameObjectToReturn = this.board.getCell(newRow, newCol);
            }
            //Update player position on the board
            this.board.updatePlayerLocation(newRow, newCol, player);
            //Update the player
            this.player.setPlayerPosition(new PlayerPosition(newRow, newCol));
        } else {
            //Cannot move message
        }
        return gameObjectToReturn;
    }

    public void gameCompleted() {
        //Convert the seconds of each level completed time into string time
        String stringLevelTimes = "<html>";
        int finalTime = 0;
        for (int i = 0; i < this.player.getPlayerData().getLevelTimes().length; i++) {
            stringLevelTimes += "LEVEL " + (i + 1) + " TIME: " + TimeManager.convertSecondToStringTime(this.player.getPlayerData().getLevelTime(i)) + "<br>";
            finalTime += this.player.getPlayerData().getLevelTime(i); //Final time
        }
        stringLevelTimes += "</html>";

        //Add the final time to the PlayerData
        this.player.getPlayerData().setFinalTime(finalTime);

        //Try to add the player to the leaderboard (leader data is saved on file)
        Leaderboard lb = new Leaderboard();
        lb.tryToAdd(this.player.getPlayerData());
        
        //Delete current user data from database
        //TODO: DB
        
        //Convert final time from int to string time (formated time eg. 5 minutes and 3 seconds)
        String finalTimeAsString = TimeManager.convertSecondToStringTime(this.player.getPlayerData().getFinalTime());
        
        this.dungeonCrawl.getViewController().getGameCompletedPanel().updateText(finalTimeAsString, stringLevelTimes);
    }
    
    public int updateCurrentTime () {
        int time = -1;
        if (this.timeManager != null) {
            time = this.player.getCurrentLevelTime() + this.timeManager.timeFinished();
            this.player.setCurrentLevelTime(time);
        }
        return time;
    }

    public Player getPlayer() {
        return this.player;
    }
    
    public BattleController getBattleController() {
        return this.battleController;
    }
}