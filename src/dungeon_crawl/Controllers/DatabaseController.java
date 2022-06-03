package dungeon_crawl.Controllers;

import dungeon_crawl.GameObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * @author Kieran
 */
public class DatabaseController {
    
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:Dungeon_Crawl_DB; create=true";

    private Connection connection;

    public DatabaseController() {
        establishConnection();
    }
    
    //Establish connection
    public void establishConnection() {
        if (this.connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println("[" + URL + "] CONNECTED");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    //Close connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public Connection getConnection() {
        //Make sure there is a connection
        if (this.connection == null) {
            establishConnection();
        }
        return this.connection;
    }

    public boolean createGridTable() {
        try {
            String tableName = "Grid";
            Statement statement = getConnection().createStatement();
            
            //Make sure no table exists but if it does then drop it
            if (tableExists(tableName) == true) {
                statement.executeUpdate("DROP TABLE " + tableName);
            }
            
            //Create table
            String createTable = "CREATE TABLE " + tableName + " (";
            for (int i = 0; i < 11; i++) { //Make 11 columns
                createTable += "COL" + i + " VARCHAR(50)";
                if (i != 10) {
                    createTable += ", ";
                } else {
                    createTable += ")";
                }     
            }
            statement.executeUpdate(createTable);
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean createPlayerTable() {
        try {
            String tableName = "Player";
            Statement statement = getConnection().createStatement();
            
            //Make sure no table exists but if it does then drop it
            if (tableExists(tableName) == true) {
                statement.executeUpdate("DROP TABLE " + tableName);
            }
            
            //Create table
            String createTable = "CREATE TABLE " + tableName + " (NAME VARCHAR(50), XPOS INT, YPOS INT, CURRENTLEVEL INT, CURRENTTIME INT, LEVEL1TIME INT, LEVEL2TIME INT, LEVEL3TIME INT)";
            statement.executeUpdate(createTable);
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean createItemsTable() {
       try {
           String tableName = "Items";
           Statement statement = getConnection().createStatement();

           //Make sure no table exists but if it does then drop it
           if (tableExists(tableName) == true) {
               statement.executeUpdate("DROP TABLE " + tableName);
           }

            //Create table
            String createTable = "CREATE TABLE " + tableName + " (ITEM VARCHAR(50))";
            statement.executeUpdate(createTable);
            statement.close();
            return true;
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
       }
   }

    public boolean createLeaderboardTable() {
        try {
            String tableName = "Leaderboard";
            Statement statement = getConnection().createStatement();
            
            //Make sure no table exists but if it does then drop it
            if (tableExists(tableName) == true) {
                statement.executeUpdate("DROP TABLE " + tableName);
            }
            
            //Create table
            String createTable = "CREATE TABLE " + tableName + " (NAME VARCHAR(50), LEVEL1TIME INT, LEVEL2TIME INT, LEVEL3TIME INT, FINALTIME INT)";
            statement.executeUpdate(createTable);
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    //Check if a table exists
    public boolean tableExists(String table) {
        boolean exists = false;
        table = table.toUpperCase();
        
        try {
            ResultSet results;
            results = getConnection().getMetaData().getTables(null, null, table, null);
            while (results.next() == true) {
                String tableName = results.getString("TABLE_NAME");
                if (tableName != null) {
                    if (tableName.equals(table)) {
                        exists = true;
                        break;
                    }
                }
            }
            results.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return exists;
    }
    
    public boolean gridInsert(String[] row) {
        try {
            String tableName = "Grid";
            Statement statement = getConnection().createStatement();
            
            String insert = "INSERT INTO " + tableName + " VALUES ('";
            
            for (int i = 0; i < 11; i++) { //Insert into 11 columns
                if (i < row.length) {
                    insert += row[i];
                } else {
                    insert += "NULL";
                }
                if (i != 10) {
                    insert += "', '";
                } else {
                    insert += "')";
                }     
            }
            statement.executeUpdate(insert);
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean playerInsert(String name, int xPos, int yPos, int currentLevel, int currentTime, int[] levelTimes) {
        try {
            String tableName = "Player";
            Statement statement = getConnection().createStatement();
            
            String insert = "INSERT INTO " + tableName + " VALUES ('";
            insert += name + "', ";
            insert += xPos + ", ";
            insert += yPos + ", ";
            insert += currentLevel + ", ";
            insert += currentTime + ", ";
            insert += levelTimes[0] + ", ";
            insert += levelTimes[1] + ", ";
            insert += levelTimes[2] + ")";
            
            statement.executeUpdate(insert);
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean itemsInsert(String item) {
        try {
            String tableName = "Items";
            Statement statement = getConnection().createStatement();
            
            String insert = "INSERT INTO " + tableName + " VALUES ('" + item + "')";
            
            statement.executeUpdate(insert);
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean leaderboardInsert(String name, int level1Time, int level2Time, int level3Time, int finalTime) {
        try {
            String tableName = "Leaderboard";
            Statement statement = getConnection().createStatement();
            
            String insert = "INSERT INTO " + tableName + " VALUES ('";
            insert += name + "', ";
            insert += level1Time + ", ";
            insert += level2Time + ", ";
            insert += level3Time + ", ";
            insert += finalTime + ")";
            
            statement.executeUpdate(insert);
            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public String[][] gridQuery() {
        String[][] grid = new String[7][11]; //ROW (7), COL (11)
        try {
            String tableName = "Grid";
            Statement statement = getConnection().createStatement();
            
            //Create statement
            String query = "SELECT ";
            for (int i = 0; i < 11; i++) { //Make 11 columns
                query += "'COL" + i + "'";
                if (i != 10) {
                    query += ", ";
                } else {
                    query += "FROM " + tableName;
                }     
            }
            
            //Get results and assign to the return variable
            ResultSet rs = statement.executeQuery(query);
            int row = 0;
            while (rs.next()) {
                if (row < 7) {
                    for (int i = 0; i < 11; i++) { //Get all 11 columns
                        grid[row][i] = rs.getString("COL" + i);
                    }
                    row++;
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            grid = null;
        }
        return grid;
    }
    
    public String playerQuery(String colName) {
        switch(colName.toLowerCase()) {
            case "name":
            case "xpos":
            case "ypos":
            case "currentlevel":
            case "currenttime":
            case "level1time":
            case "level2time":
            case "level3time":
                //Case fall through
                break;
            default:
                //Invalid table col
                return null;
        }
        try {
            String tableName = "Player";
            Statement statement = getConnection().createStatement();
            
            //Create statement
            String query = "SELECT " + colName.toUpperCase() + " FROM " + tableName;
            
            //Get results and assign to the return variable
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Only one row
                String toReturn = rs.getString(colName);
                rs.close();
                statement.close();
                return toReturn;
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public String itemsQuery(int slot) {
        if (slot > 3) {
            return null;
        }
        try {
            String tableName = "Items";
            Statement statement = getConnection().createStatement();
            
            //Create statement
            String query = "SELECT ITEM FROM " + tableName;
            
            //Get results and assign to the return variable
            ResultSet rs = statement.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                if (count == slot) {
                    String toReturn = rs.getString("ITEM");
                    rs.close();
                    statement.close();
                    return toReturn;
                } else {
                    count++;
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public String[] leaderboardQuery(String colName) {
        switch(colName.toLowerCase()) {
            case "name":
            case "level1time":
            case "level2time":
            case "level3time":
            case "finaltime":
                //Case fall through
                break;
            default:
                //Invalid table col
                return null;
        }
        try {
            String tableName = "Leaderboard";
            Statement statement = getConnection().createStatement();
            
            //Create statement
            String query = "SELECT " + colName.toUpperCase() + " FROM " + tableName;
            
            //Get results and assign to the return variable
            ResultSet rs = statement.executeQuery(query);
            ArrayList<String> data = new ArrayList<>();
            while (rs.next()) {
                data.add(rs.getString(colName));
            }
            rs.close();
            statement.close();
            return ((String[]) data.toArray());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
        DatabaseController dbManager = new DatabaseController();
        System.out.println(dbManager.getConnection());
        dbManager.createGridTable();
        dbManager.createPlayerTable();
        dbManager.createItemsTable();
        dbManager.createLeaderboardTable();
    }
}