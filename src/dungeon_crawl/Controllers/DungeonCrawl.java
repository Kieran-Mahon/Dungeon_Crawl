package dungeon_crawl.Controllers;

import dungeon_crawl.Items.Item;
import dungeon_crawl.Leaderboard;
import dungeon_crawl.Player;
import dungeon_crawl.PlayerData;

/*
 * @author Kieran
 */
public class DungeonCrawl {
    
    private ViewController viewController;
    private GameController gameController;
    
    public void startGUI() {
        this.viewController = new ViewController(this);
    }
    
    public void openMainMenu() {
        this.viewController.switchPanels(ViewController.Panel.START);
    }
    
    public void openLeaderboard() {
        this.viewController.getLeaderboardPanel().updateLearderboard(new Leaderboard().displayLeaderboard());
        this.viewController.switchPanels(ViewController.Panel.LEADERBOARD);
    }
    
    public void openNewGame() {
        this.viewController.switchPanels(ViewController.Panel.NEWGAME);
    }
    
    public void tryLoadGame() {
        this.gameController = new GameController();
        if (gameController.loadGame() == true) {
            //Start game
            this.viewController.switchPanels(ViewController.Panel.GAME);
            this.gameController.gameLogic(this);
        } else {
            //Show error found while loading panel
            this.viewController.switchPanels(ViewController.Panel.ERRORLOADING);
        }
    }
    
    public boolean tryMakeNewGame(String input) {
        if (input.isEmpty()) {
            return true;
        }
        this.gameController = new GameController(new Player(new PlayerData(input)));
        this.viewController.switchPanels(ViewController.Panel.GAME);
        this.gameController.gameLogic(this);
        return false;
    }

    public ViewController getViewController() {
        return this.viewController;
    }

    public GameController getGameController() {
        return this.gameController;
    }
    
    public void saveGameInGrid() {
        //Save game
        this.gameController.saveGame();
        //Quit program
        System.exit(0);
    }
    
    public void movePlayer(String dir) {
        this.gameController.playerMoveAction(dir);
    }
    
    public void levelContinue() {
        if (this.gameController.getPlayer().getCurrentLevel() == 3) {
            this.gameController.gameCompleted();
        } else {
            this.viewController.switchPanels(ViewController.Panel.GAME);
        }
    }
    
    public void userItem(String key) {
        BattleController bc = this.gameController.getBattleController();
        if (bc.getPlayersTurn() == true) {
            bc.playerAttack(key);
        }
    }
    
    public void enemyKilled() {
        if (this.gameController.getBattleController().getEnemy().getReward() != null) {
            //Item already set
            this.gameController.updateItemPickupPanelDisplay();
            this.viewController.switchPanels(ViewController.Panel.ITEMPICKUP);
        } else {
            this.viewController.switchPanels(ViewController.Panel.GAME);
            this.gameController.displayBoard();
        }
    }
    
    public void collectItem(int slot) {
        if (slot != -1) {
            Item.startItemReplacing(slot, this.gameController.getItemToBeCollected(), this.gameController.getPlayer());
        }
        this.viewController.switchPanels(ViewController.Panel.GAME);
        this.gameController.displayBoard();
    }
    
    public void playerDied() {
        BattleController bc = this.gameController.getBattleController();
        this.gameController.movePlayerBack(bc.getEnemy(), bc.getLastPlayerPos());
        this.viewController.switchPanels(ViewController.Panel.GAME);
    }
    
    public static void main(String[] args) {
        DungeonCrawl dc = new DungeonCrawl();
        dc.startGUI();
    }
}