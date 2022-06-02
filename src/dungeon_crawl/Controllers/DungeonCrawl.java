package dungeon_crawl.Controllers;

import dungeon_crawl.Utilities.InputHandler;
import dungeon_crawl.Leaderboard;
import dungeon_crawl.LeaderboardScreen;
import dungeon_crawl.NewGameScreen;
import dungeon_crawl.Player;
import dungeon_crawl.PlayerData;
import dungeon_crawl.StartScreen;

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
    
    public void saveGameInGrid() {
        //Update current time
        this.gameController.updateCurrentTime();
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
        if (bc.enemyTurn == false) {
            bc.playerAttack(key);
        }
    }
    
    public static void main(String[] args) {
        DungeonCrawl dc = new DungeonCrawl();
        dc.startGUI();
    }
}