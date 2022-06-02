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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * @author Kieran
 */
public class IOController {
    //This class handles all the loading and saving within the program
    
    public static GameObject[][] loadBoard() {
        GameObject[][] gridToReturn = null;
        boolean errorFound = false;
        
        //Get data from file
        ArrayList<String> fileContents = new ArrayList<>();
        try {
            //Open file
            FileReader fileReader = new FileReader("./Grid.txt");
            BufferedReader br = new BufferedReader(fileReader);
            //Read file
            while (br.ready() == true) {
                fileContents.add(br.readLine());
            }
            //Close reader
            br.close();
        } catch (IOException e) {
            errorFound = true;
        }
        
        if (errorFound == false) {
            GameObject[][] newGrid = new GameObject[7][11];
            int row = 0;
            
            for (String line : fileContents) {
                if (errorFound == true) {
                    //Error in the grid
                    break;
                }
                
                //Split up each cell into a string array
                String[] cells = line.split(" ");
                //Check if the array is at length 11
                if (cells.length == 11) {
                    for (int col = 0; col < cells.length; col++) {
                        if (cells[col].equals("NULL") == false) { //NULL objects get added automatically so no need to add them
                            //Saved objects are encoded (eg. "EWOLF" which is an enemy of type wolf)
                            char type = cells[col].charAt(0);
                            
                            switch (type) {
                                case 'E':
                                    //Enemy
                                    Enemy enemyToAdd = EnemyNameLookUp.getItem(cells[col].substring(1));
                                    if (enemyToAdd != null) {
                                        newGrid[row][col] = enemyToAdd;
                                    } else {
                                        errorFound = true;
                                    }
                                    break;
                                case 'I':
                                    //Item
                                    Item itemToAdd = ItemNameLookUp.getItem(cells[col].substring(1));
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
                } else {
                    //The grid has been changed
                    errorFound = true;
                }
                row++;
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
        ArrayList<String> toWrite = new ArrayList<>();        
        //Add grid to the array list row by row
        for (int row = 0; row < grid.length; row++) {
            String lineToWrite = "";
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == null) {
                    lineToWrite += "NULL";
                } else {
                    //Dont save player in the grid file as its for an preview of
                    //where it is. The actual data of the player is in the player
                    //class. Player is replaced with NULL
                    if ((grid[row][col] instanceof Player) == false) {
                        lineToWrite += grid[row][col].getGridName() + grid[row][col].getName();
                    } else {
                        lineToWrite += "NULL";
                    }
                }
                //Include spaces between game objects
                if (col != (grid[0].length - 1)) {
                    lineToWrite += " ";
                }
            }
            //Add line to list to write
            toWrite.add(lineToWrite);
        }
        
        //Write to file
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("./Grid.txt"));
            for (String line : toWrite) {
                pw.println(line);
            }
            //Close writer
            pw.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public static Player loadPlayer() {
        boolean errorFound = false;
        Player playerToReturn = null;
        
        //Get data from file
        try {
            ArrayList<String> fileContents = new ArrayList<>();
            //Open file
            FileReader fileReader = new FileReader("./Player.txt");
            BufferedReader br = new BufferedReader(fileReader);
            //Read file
            while (br.ready() == true) {
                fileContents.add(br.readLine());
            }
            //Close reader
            br.close();
            
            //Holder variables
            String name = "";
            PlayerPosition playerPosition = new PlayerPosition(0,0);
            int currentLevel = -1;
            int currentLevelTime = -1;
            int[] levelTimes = new int[3];
            //Final time not needed
            //Items are saved in a different file
            int counter = 1;
            
            //Loop to get the data out of fileContents and put it in the correct
            //variables. Also change the error variable if an issue is found
            for (String line : fileContents) {
                //Check for missing data for lines below line 7 (line 7 is end of file)
                if (counter < 7) {
                    if (line.isEmpty()) {
                        //Data missing
                        errorFound = true;
                    }
                }
                //Check for errors found
                if (errorFound) {
                    break;
                }
                
                switch (counter) {
                    case (1): //Line 1 is the name
                        name = line;
                        break;
                    case (2): //Line 2 is for the player row position
                        try {
                            int row = Integer.parseInt(line);
                            //Make sure the number is within the grid
                            if ((row >= 0) && (row <= 6)) {
                                playerPosition.setRow(row);
                            } else {
                                errorFound = true;
                            }
                        } catch (NumberFormatException e){
                            errorFound = true;
                        }
                        break;
                    case (3): //Line 3 is for the player Col position
                        try {
                            int col = Integer.parseInt(line);
                            //Make sure the number is within the grid
                            if ((col >= 0) && (col <= 10)) {
                                playerPosition.setCol(col);
                            } else {
                                errorFound = true;
                            }
                        } catch (NumberFormatException e){
                            errorFound = true;
                        }
                        break;
                    case (4): //Line 4 is for the current level
                        try {
                            //Parse the string version of current level into it's integer version
                            currentLevel = Integer.parseInt(line);
                        } catch (NumberFormatException e){
                            errorFound = true;
                        }
                        break;
                    case (5): //Line 5 is for the current level time
                        try {
                            //Parse the string version of current level time into it's double version
                            currentLevelTime = Integer.parseInt(line);
                        } catch (NumberFormatException e){
                            errorFound = true;
                        }
                        break;
                    case (6): //Line 6 is the level completed times
                        String[] times = line.split(" ");
                        for (int i = 0; i < 3; i++) {
                            try {
                                if (times.length == 3) {
                                    //Parse the string version of level times into the double version
                                    levelTimes[i] = Integer.parseInt(times[i]);
                                } else {
                                    errorFound = true;
                                }
                            } catch (NumberFormatException e){
                                errorFound = true;
                            }
                        }
                        break;
                }
                counter++;
            }
            
            if (counter != 7) { //Less than 7 lines (file has been changed)
                errorFound = true;
            }
            
            //If no errors found then add the new player
            if (errorFound == false) {
                playerToReturn = new Player(new PlayerData(name, levelTimes, -1), playerPosition, currentLevel, currentLevelTime, null);
            }
        } catch (IOException e) {
        }
        
        return playerToReturn;
    }        
            
    public static boolean savePlayer(Player player) {
        boolean errorFound = false;
        
        //List to write to file
        ArrayList<String> toWrite = new ArrayList<>();
        //Reference to the player data which makes the code easier to read
        PlayerData playerData = player.getPlayerData();
        
        //Name
        toWrite.add(playerData.getName());
        //Player x position
        toWrite.add(String.valueOf(player.getPlayerPosition().getRow()));
        //Player y position
        toWrite.add(String.valueOf(player.getPlayerPosition().getCol()));
        //Current level
        toWrite.add(String.valueOf(player.getCurrentLevel()));
        //Current level time
        toWrite.add(String.valueOf(player.getCurrentLevelTime()));
        //Level times
        String toAdd = "";
        for (int i = 0; i < playerData.getLevelTimes().length; i++) {
            toAdd += playerData.getLevelTime(i);
            if (i != (playerData.getLevelTimes().length - 1)) {
                toAdd += " ";
            }
        }
        toWrite.add(toAdd);
        
        //Write to file
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("./Player.txt"));
            for (String line : toWrite) {
                pw.println(line);
            }
            //Close writer
            pw.close();
        } catch (FileNotFoundException e) {
            errorFound = true;
        }
        
        return errorFound;
    }
    
    public static Player loadPlayerItems(Player player) {
        boolean errorFound = false;
        Player playerToReturn = null;
        
        //Get item data from file
        try {
            ArrayList<String> fileContents = new ArrayList<>();
            //Open file
            FileReader fileReader = new FileReader("./PlayerItems.txt");
            BufferedReader br = new BufferedReader(fileReader);
            //Read file
            while (br.ready() == true) {
                fileContents.add(br.readLine());
            }
            //Close reader
            br.close();
            
            //Holder variables
            
            ArrayList<Item> items = new ArrayList<>();
            
            //Loop to get the data out of fileContents and put the items in the temp
            //player items list. Also change the error variable if an issue is found
            for (String line : fileContents) {
                //Check for errors found
                if (errorFound) {
                    break;
                }
                //Check if item exists
                Item toAdd = ItemNameLookUp.getItem(line.toUpperCase()); //Returns null if item does not exist
                if (toAdd != null) {
                   items.add(toAdd);
                } else {
                    errorFound = true;
                }
            }
            
            //If no errors found then add the new player
            if (errorFound == false) {
                playerToReturn = new Player(player.getPlayerData(), player.getPlayerPosition(), player.getCurrentLevel(), player.getCurrentLevelTime(), items);
            }
        } catch (IOException e) {
        }
        
        //Return player
        return playerToReturn;
    }
    
    public static boolean savePlayerItems(Player player) {
        boolean errorFound = false;
        //List to write to file
        ArrayList<String> toWrite = new ArrayList<>();
        //Items
        for (Item item : player.getItems()){
            toWrite.add(item.getName());
        }
        
        //Write to file
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("./PlayerItems.txt"));
            for (String line : toWrite) {
                pw.println(line);
            }
            //Close writer
            pw.close();
        } catch (FileNotFoundException e) {
            errorFound = true;
        }
        
        return errorFound;
    }
    
    public static ArrayList<PlayerData> loadLeaderBoard() {
        boolean errorFound = false;
        ArrayList<PlayerData> leaders = new ArrayList<>();
        
        //Get data from file
        ArrayList<String> fileContents = new ArrayList<>();
        try {
            //Open file
            FileReader fileReader = new FileReader("./Leaderboard.txt");
            BufferedReader br = new BufferedReader(fileReader);
            //Read file
            while (br.ready() == true) {
                fileContents.add(br.readLine());
            }
            //Close reader
            br.close();
        } catch (IOException e) {
            errorFound = true;
        }
        
        //Import leaderboard data
        if (errorFound == false) {
            //Not all PlayerData variables are used
            String name = "";
            int[] levelTimes = new int[3];
            int finalTime = -1;
            int counter = 0;
            
            for (String line : fileContents) {
                counter++;
                
                //Check for empty lines
                if (line.isEmpty()) {
                    //Skip leader
                    counter = 0;
                } else {
                    switch (counter){
                        //Name
                        case 1:
                            name = line.toUpperCase();
                            break;
                        //Level times
                        case 2: 
                            try {
                                //Parse the string version of level times into the int version
                                String[] times = line.split(" ");
                                for (int i = 0; i < 3; i++) {
                                    if (i < times.length) {
                                        levelTimes[i] = Integer.parseInt(times[i]);
                                    }
                                }
                            } catch (NumberFormatException e){
                                //Error found, skip this leader
                                counter = 0;
                            }
                            break;
                        //Final time + add to list
                        case 3:
                            try {
                                //Parse the final time
                                finalTime = Integer.parseInt(line);
                                
                                //Add to leaderboard list
                                leaders.add(new PlayerData(name, levelTimes, finalTime));
                                
                            } catch (NumberFormatException e){
                                //Error found, skip this leader
                                counter = 0;
                            }
                            counter = 0;
                            break;
                    }
                }
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
        ArrayList<String> toWrite = new ArrayList<>();
        
        //Convert leaders to a string array list
        for (PlayerData leader : leaders){
            //Name
            toWrite.add(leader.getName());
            //Level times
            String toAdd = "";
            for (int i = 0; i < leader.getLevelTimes().length; i++) {
                toAdd += leader.getLevelTime(i);
                if (i != (leader.getLevelTimes().length - 1)) {
                    toAdd += " ";
                }
            }
            toWrite.add(toAdd);
            //Final time
            toWrite.add(String.valueOf(leader.getFinalTime()));
        }
        
        //Write to file
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("./Leaderboard.txt"));
            for (String line : toWrite) {
                pw.println(line);
            }
            //Close writer
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}