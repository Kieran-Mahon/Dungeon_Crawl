package dungeon_crawl.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void createGridTable() {
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
                createTable += "COL" + i +" VARCHAR(50)";
                if (i != 10) {
                    createTable += ", ";
                } else {
                    createTable += ")";
                }     
            }
            statement.executeUpdate(createTable);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    public void createItemTable() {
        try {
            String tableName = "Items";
            Statement statement = getConnection().createStatement();
            
            //Make sure no table exists but if it does then drop it
            if (tableExists(tableName) == true) {
                statement.executeUpdate("DROP TABLE " + tableName);
            }
            
            //Create table
            String createTable = "CREATE TABLE " + tableName + " (ITEM1 VARCHAR(50), ITEM2 VARCHAR(50), ITEM3 VARCHAR(50), ITEM4 VARCHAR(50))";
            statement.executeUpdate(createTable);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    //Check if a table exists
    public boolean tableExists(String table) {
        boolean exists = false;
        
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
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exists;
    }
    
    
    
    
    
    

    public static void main(String[] args) {
        DatabaseController dbManager = new DatabaseController();
        System.out.println(dbManager.getConnection());
        dbManager.createGridTable();
            
            
        try {
            dbManager.getConnection().createStatement().execute("DESCRIBE Grid");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}