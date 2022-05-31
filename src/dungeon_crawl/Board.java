package dungeon_crawl;

/*
 * @author Kieran
 */
public class Board {

    //Board grid - all grids are 7 rows by 11 cols
    private GameObject[][] grid; //ROW (7), COL (11)

    public void setUpBoard(GameObject[][] grid) {
        this.grid = grid;
    }
    
    public void setCell(int row, int col, GameObject object) {
        this.grid[row][col] = object;
    }
    
    public void setCell(PlayerPosition playerPosition, GameObject object) {
        int row = playerPosition.getRow();
        int col = playerPosition.getCol();
        this.grid[row][col] = object;
    }
    
    public GameObject getCell(int row, int col){
        return this.grid[row][col];
    }
    
    public void displayBoard() {
        for (int row = 0; row < this.grid.length; row++) {
            String line = "|";
            for (int col = 0; col < this.grid[0].length; col++) {
                if (this.grid[row][col] == null) {
                    line += "   |";
                } else {
                    line += " " + this.grid[row][col].getGridName() + " |";
                }
            }
            System.out.println(line);
            if (row != (this.grid.length -1)) {
                System.out.println("|---+---+---+---+---+---+---+---+---+---+---|");
            }
        }
    }
    
    public boolean loadBoard() {
        GameObject[][] tempGrid = IOController.loadBoard();
        boolean errorFound = false;
        //Check if the board is empty
        if (tempGrid == null) {
            errorFound = true;
        } else {
            this.grid = tempGrid;
        }
        return errorFound;
    }
    
    public void saveBoard() {
        IOController.saveBoard(this.grid);
    }
    
    public void updatePlayerLocation(int newRow, int newCol, Player player) {
        //Get old location
        int oldRow = player.getPlayerPosition().getRow();
        int oldCol = player.getPlayerPosition().getCol();
        //Remove player from old location
        this.grid[oldRow][oldCol] = null;
        //Add player to the new location
        this.grid[newRow][newCol] = player;
    }
}