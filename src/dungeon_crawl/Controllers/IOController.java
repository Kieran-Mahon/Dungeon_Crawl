package dungeon_crawl.Controllers;

import dungeon_crawl.Door;
import dungeon_crawl.Items.Item;
import dungeon_crawl.Enemies.Enemy;
import dungeon_crawl.Utilities.EnemyNameLookUp;
import dungeon_crawl.GameObject;
import dungeon_crawl.Utilities.ItemNameLookUp;
import dungeon_crawl.Player;
import dungeon_crawl.PlayerData;
import dungeon_crawl.PlayerPosition;
import java.util.ArrayList;

/*
 * @author Kieran
 */
public class IOController {
    //This class handles all the loading and saving within the program
    
    public static DatabaseController dbc = new DatabaseController();
    
    public static GameObject[][] loadBoard() {
        GameObject[][] gridToReturn = null;
        boolean errorFound = false;
        
        //Get data from database
        String[][] gridSaved = dbc.gridQuery();
        
        //Make sure no errors in grid from databse
        if (gridSaved == null) {
            errorFound = true;
        }
        
        if (errorFound == false) {
            GameObject[][] newGrid = new GameObject[7][11];
            for (int row = 0; row < newGrid.length; row++) {
                if (errorFound == true) {
                    //Error in the grid
                    break;
                }
                for (int col = 0; col < newGrid[0].length; col++) {
                    if (gridSaved[row][col] == null) {
                        errorFound = true;
                    }
                    if (gridSaved[row][col].equals("NULL") == false) { //NULL objects get added automatically so no need to add them
                        //Saved objects are encoded (eg. "EWOLF" which is an enemy of type wolf)
                        char type = gridSaved[row][col].charAt(0);

                        switch (type) {
                            case 'E':
                                //Enemy
                                Enemy enemyToAdd = EnemyNameLookUp.getItem(gridSaved[row][col].substring(1));
                                if (enemyToAdd != null) {
                                    newGrid[row][col] = enemyToAdd;
                                } else {
                                    errorFound = true;
                                }
                                break;
                            case 'I':
                                //Item
                                Item itemToAdd = ItemNameLookUp.getItem(gridSaved[row][col].substring(1));
                                if (itemToAdd != null) {
                                    newGrid[row][col] = itemToAdd;
                                } else {
                                    errorFound = true;
                                }
                                break;
                            case 'D':
                                //Door
                                newGrid[row][col] = new Door();
                                break;
                        }
                    }
                }
            }
            //If no errors then update to the new grid
            if (errorFound == false) {
                gridToReturn = newGrid;
            }
        }
        
        //Return grid
        return gridToReturn;
    }

    public static boolean saveBoard(GameObject[][] grid) {
        boolean errorFound = false;
        //Create new table
        dbc.createGridTable();
        
        //Add grid to database
        for (int row = 0; row < grid.length; row++) {
            String[] cellsToSave = new String[11];
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == null) {
                    cellsToSave[col] = "NULL";
                } else {
                    //Dont save player in the grid file as its for an preview of
                    //where it is. The actual data of the player is in the player
                    //class. Player is replaced with NULL
                    if ((grid[row][col] instanceof Player) == false) {
                        cellsToSave[col] = grid[row][col].getGridName() + grid[row][col].getName();
                    } else {
                        cellsToSave[col] = "NULL";
                    }
                }
            }
            //Add cells to database
            boolean noError = dbc.gridInsert(cellsToSave);
            if (noError == false) {
                errorFound = true;
            }
        }
        return !errorFound; //All fine then true, false if error found
    }

    public static Player loadPlayer() {
        boolean errorFound = false;
        Player playerToReturn = null;

        //Holder variables
        String name;
        PlayerPosition playerPosition = new PlayerPosition(0,0);
        int currentLevel = -1;
        int currentLevelTime = -1;
        int[] levelTimes = new int[3];
        //Final time not needed
        //Items are saved in a different file
            
        //Name
        name = dbc.playerQuery("name");
        if (name == null) {
            errorFound = true;
        }

        //Row
        try {
            String rowString = dbc.playerQuery("xpos");
            if (rowString == null) {
                errorFound = true;
            } else {
                int row = Integer.parseInt(rowString);
                //Make sure the number is within the grid
                if ((row >= 0) && (row <= 6)) {
                    playerPosition.setRow(row);
                } else {
                    errorFound = true;
                }
            }
        } catch (NumberFormatException e){
            errorFound = true;
        }

        //Col
        try {
            String colString = dbc.playerQuery("ypos");
            if (colString == null) {
                errorFound = true;
            } else {
                int col = Integer.parseInt(colString);
                //Make sure the number is within the grid
                if ((col >= 0) && (col <= 10)) {
                    playerPosition.setCol(col);
                } else {
                    errorFound = true;
                }
            }
        } catch (NumberFormatException e){
            errorFound = true;
        }

        //Current level
        try {
            String currentLevelString = dbc.playerQuery("currentlevel");
            if (currentLevelString == null) {
                errorFound = true;
            } else {
                //Parse the string version of current level into it's integer version
                currentLevel = Integer.parseInt(currentLevelString);
            }
        } catch (NumberFormatException e){
            errorFound = true;
        }

        //Current level time
        try {
            String currentLevelTimeString = dbc.playerQuery("currenttime");
            if (currentLevelTimeString == null) {
                errorFound = true;
            } else {
                //Parse the string version of current level time into it's double version
                currentLevelTime = Integer.parseInt(currentLevelTimeString);
            }
        } catch (NumberFormatException e){
            errorFound = true;
        }

        //Level times
        for (int i = 0; i < 3; i++) {
            try {
                String levelTimeString = dbc.playerQuery("level" + (i + 1) + "time");
                if (levelTimeString == null) {
                    errorFound = true;
                } else {
                    //Parse the string version of level times into the double version
                    levelTimes[i] = Integer.parseInt(levelTimeString);
                }
            } catch (NumberFormatException e){
                errorFound = true;
            }
        }
        
        //If no errors found then add the new player
        if (errorFound == false) {
            playerToReturn = new Player(new PlayerData(name, levelTimes, -1), playerPosition, currentLevel, currentLevelTime, null);
        }
        
        return playerToReturn;
    }        
            
    public static boolean savePlayer(Player player) {
        //Create new player table
        dbc.createPlayerTable();
        
        //Reference to the player data which makes the code easier to read
        PlayerData playerData = player.getPlayerData();
        
        //Name
        String name = playerData.getName();
        //Player x position
        int xPos = player.getPlayerPosition().getRow();
        //Player y position
        int yPos = player.getPlayerPosition().getCol();
        //Current level
        int currentLevel = player.getCurrentLevel();
        //Current level time
        int currentTime = player.getCurrentLevelTime();
        //Level times
        int[] levelTimes = playerData.getLevelTimes();
        
        return !dbc.playerInsert(name, xPos, yPos, currentLevel, currentTime, levelTimes);
    }
    
    public static Player loadPlayerItems(Player player) {
        boolean errorFound = false;
        Player playerToReturn = null;
        ArrayList<Item> items = new ArrayList<>();
        
        //Get item data from database
        for (int i = 0; i < 3; i++) {
            String item = dbc.itemsQuery(i);
            if (item == null) {
                errorFound = true;
            } else {
                Item toAdd = ItemNameLookUp.getItem(item.toUpperCase()); //Returns null if item does not exist
                if (toAdd != null) {
                   items.add(toAdd);
                } else {
                    errorFound = true;
                }
            }
        }
        
        //If no errors found then add the new player
        if (errorFound == false) {
            playerToReturn = new Player(player.getPlayerData(), player.getPlayerPosition(), player.getCurrentLevel(), player.getCurrentLevelTime(), items);
        }
        
        //Return player
        return playerToReturn;
    }
    
    public static boolean savePlayerItems(Player player) {
        boolean errorFound = false;
        //Create new item table
        dbc.createItemsTable();
        
        //Items
        for (Item item : player.getItems()){
            boolean noError = dbc.itemsInsert(item.getName());
            if (noError == false) {
                errorFound = true;
            }
        }
        return errorFound;
    }
    
    public static ArrayList<PlayerData> loadLeaderBoard() {
        boolean errorFound = false;
        ArrayList<PlayerData> leaders = new ArrayList<>();
        
        //Names
        String[] names = dbc.leaderboardQuery("name");
        if (names == null) {
            errorFound = true;
        }
        
        //Level 1 times
        String[] level1Times = dbc.leaderboardQuery("level1time");
        if (level1Times == null) {
            errorFound = true;
        }

        //Level 2 times
        String[] level2Times = dbc.leaderboardQuery("level2time");
        if (level2Times == null) {
            errorFound = true;
        }
        
        //Level 3 times
        String[] level3Times = dbc.leaderboardQuery("level3time");
        if (level3Times == null) {
            errorFound = true;
        }
        
        //Final time
        String[] finalTimes = dbc.leaderboardQuery("finaltime");
        if (finalTimes == null) {
            errorFound = true;
        }
        
        if (errorFound == false) {
            for (int i = 0; i < names.length; i++) {
                int[] levelTimes = new int[3];
                int finalTime = 0;
                try {
                    levelTimes[0] = Integer.parseInt(level1Times[i]);
                    levelTimes[1] = Integer.parseInt(level2Times[i]);
                    levelTimes[2] = Integer.parseInt(level3Times[i]);
                    finalTime = Integer.parseInt(finalTimes[i]);
                } catch (NumberFormatException e){
                    //Error found, skip this leader
                    continue;
                }
                //Add Leader
                leaders.add(new PlayerData(names[i], levelTimes, finalTime));
            }
        }
        
        //If an error found change the return variable to null
        if (errorFound == true) {
            leaders = null;
        }
        
        //Return leaders
        return leaders;
    }
    
    public static void saveLeaderBoard(ArrayList<PlayerData> leaders) {
        //Create new leaderboard table
        dbc.createLeaderboardTable();
        
        //Convert leaders to a string array list
        for (PlayerData leader : leaders){
            //Name
            String name = leader.getName();
            
            //Level times
            int level1Time = leader.getLevelTime(0);
            int level2Time = leader.getLevelTime(0);
            int level3Time = leader.getLevelTime(0);
            
            //Final time
            int finalTime = leader.getFinalTime();
            //Add to database
            dbc.leaderboardInsert(name, level1Time, level3Time, level2Time, finalTime);
        }
    }
}