package dungeon_crawl.Controllers;

import dungeon_crawl.Panels.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/*
 * @author Kieran
 */
public class ViewController extends JFrame {
    
    //Enum used to change panels
    public static enum Panel {
        BATTLE,
        ENEMYKILLED,
        GAMECOMPLETED,
        GAME,
        ITEMPICKUP,
        LEADERBOARD,
        ERRORLOADING,
        ERRORSAVING,
        LEVELCOMPLETED,
        NEWGAME,
        PLAYERDIED,
        START
    }
    
    //Reference to dungeon crawl
    private final DungeonCrawl dungeonCrawl;
    
    //Panels
    private BattlePanel battlePanel;
    private EnemyKilledPanel enemyKilledPanel;
    private ErrorLoadingPanel errorLoadingPanel;
    private ErrorSavingPanel errorSavingPanel;
    private GameCompletedPanel gameCompletedPanel;
    private GamePanel gamePanel;
    private ItemPickUpPanel itemPickUpPanel;
    private LeaderboardPanel leaderboardPanel;
    private LevelCompletedPanel levelCompletedPanel;
    private NewGamePanel newGamePanel;
    private PlayerDiedPanel playerDiedPanel;
    private StartPanel startPanel;
    
    //Reference to the current panel
    private JPanel currentPanel;
    
    public ViewController(DungeonCrawl newDungeonCrawl) {
        //Set frame name
        super("Dungeon Crawl");        
        //Set dungeonCrawl reference
        this.dungeonCrawl = newDungeonCrawl;
        //Set up frame
        this.setSize(600, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //Set up panels
        initComponents();
        //Pack everything
        pack();
        //Show frame
        setVisible(true);
    }
    
    private void initComponents() {
        //Add all the panels
        battlePanel = new BattlePanel(dungeonCrawl);
        enemyKilledPanel = new EnemyKilledPanel(dungeonCrawl);
        errorLoadingPanel = new ErrorLoadingPanel(dungeonCrawl);
        errorSavingPanel = new ErrorSavingPanel();
        gameCompletedPanel = new GameCompletedPanel(dungeonCrawl);
        gamePanel = new GamePanel(dungeonCrawl);
        itemPickUpPanel = new ItemPickUpPanel(dungeonCrawl);
        leaderboardPanel = new LeaderboardPanel(dungeonCrawl);
        levelCompletedPanel = new LevelCompletedPanel(dungeonCrawl);
        newGamePanel = new NewGamePanel(dungeonCrawl);
        playerDiedPanel = new PlayerDiedPanel(dungeonCrawl);
        startPanel = new StartPanel(dungeonCrawl);
        
        add(battlePanel);
        add(enemyKilledPanel);
        add(errorLoadingPanel);
        add(errorSavingPanel);
        add(gameCompletedPanel);
        add(gamePanel);
        add(itemPickUpPanel);
        add(leaderboardPanel);
        add(levelCompletedPanel);
        add(newGamePanel);
        add(playerDiedPanel);
        add(startPanel);
        
        battlePanel.setVisible(false);
        enemyKilledPanel.setVisible(false);
        errorLoadingPanel.setVisible(false);
        errorSavingPanel.setVisible(false);
        gameCompletedPanel.setVisible(false);
        gamePanel.setVisible(false);
        itemPickUpPanel.setVisible(false);
        leaderboardPanel.setVisible(false);
        levelCompletedPanel.setVisible(false);
        playerDiedPanel.setVisible(false);
        newGamePanel.setVisible(false);
        
        //Set current panel
        currentPanel = startPanel;
    }
    
    public void switchPanels(Panel newPanelName) {
        currentPanel.setVisible(false);
        JPanel newPanel = startPanel;
        switch (newPanelName) {
            case BATTLE:
                newPanel = battlePanel;
                break;
            case ENEMYKILLED:
                newPanel = enemyKilledPanel;
                break;
            case ERRORLOADING:
                newPanel = errorLoadingPanel;
                break;
            case ERRORSAVING:
                newPanel = errorSavingPanel;
                break;
            case GAMECOMPLETED:
                newPanel = gameCompletedPanel;
                break;
            case GAME:
                newPanel = gamePanel;
                break;
            case ITEMPICKUP:
                newPanel = itemPickUpPanel;
                break;
            case LEADERBOARD:
                newPanel = leaderboardPanel;
                break;
            case LEVELCOMPLETED:
                newPanel = levelCompletedPanel;
                break;
            case PLAYERDIED:
                newPanel = playerDiedPanel;
                break;
            case NEWGAME:
                newPanel = newGamePanel;
                break;
            case START:
                newPanel = startPanel;
                break;
        }
        this.currentPanel = newPanel;
        newPanel.setVisible(true);
        add(newPanel);
    }
    
    //Getters
    public BattlePanel getBattlePanel() {
        return battlePanel;
    }

    public EnemyKilledPanel getEnemyKilledPanel() {
        return enemyKilledPanel;
    }

    public GameCompletedPanel getGameCompletedPanel() {
        return gameCompletedPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ItemPickUpPanel getItemPickUpPanel() {
        return itemPickUpPanel;
    }

    public LeaderboardPanel getLeaderboardPanel() {
        return leaderboardPanel;
    }

    public ErrorLoadingPanel getErrorLoadingPanel() {
        return errorLoadingPanel;
    }

    public ErrorSavingPanel getErrorSavingPanel() {
        return errorSavingPanel;
    }
    
    public LevelCompletedPanel getLevelCompletedPanel() {
        return levelCompletedPanel;
    }

    public PlayerDiedPanel getPlayerDiedPanel() {
        return playerDiedPanel;
    }
    
    public NewGamePanel getNewGamePanel() {
        return newGamePanel;
    }

    public StartPanel getStartPanel() {
        return startPanel;
    }
}