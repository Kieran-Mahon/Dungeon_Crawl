package dungeon_crawl.Controllers;

import dungeon_crawl.Items.Item;
import dungeon_crawl.Enemies.Enemy;
import dungeon_crawl.Player;
import dungeon_crawl.PlayerPosition;

/*
 * @author Kieran
 */
public class BattleController {
    private final Enemy enemy;
    private final Player player;
    //Used to reset player to last location if they die
    private final PlayerPosition lastPlayerPos;
    private final DungeonCrawl dungeonCrawl;
    private final GameController gameController;
    private boolean playersTurn = false;
    
    public BattleController(PlayerPosition currentPlayerPos, DungeonCrawl dungeonCrawl, Enemy enemy, Player player) {
        this.lastPlayerPos = currentPlayerPos;
        this.dungeonCrawl = dungeonCrawl;
        this.gameController = this.dungeonCrawl.getGameController();
        this.enemy = enemy;
        this.player = player;
        this.player.resetPlayerCombat();
        this.playersTurn = true;
        //Switch panels
        this.dungeonCrawl.getViewController().switchPanels(ViewController.Panel.BATTLE);
        //Update battle panel
        this.dungeonCrawl.getViewController().getBattlePanel().updateBattleActionLabel("");
        updateBattle();
    }
    
    private void updateBattle() {
        //Display enemy and player info
        this.dungeonCrawl.getViewController().getBattlePanel().updateEnemy(this.enemy.getName(), "HEALTH: " + this.enemy.getHealth());
        this.dungeonCrawl.getViewController().getBattlePanel().updatePlayer("HEALTH: " + this.player.getHealth());
        
        //Display player items
        String[] playerItems = new String[this.player.getItems().size()];
        for (int i = 0; i < playerItems.length; i++) {
            playerItems[i] = this.player.getItems().get(i).getInfo(true);
        }
        this.dungeonCrawl.getViewController().getBattlePanel().updateItemButtions(playerItems);
    }
    
    public void playerAttack(String input) {
        String actionsTaken = "<html>";
        //Block player attacks
        this.playersTurn = false;
        if (this.player.getStunTime() == 0) { //Check if not stunned
            //Get the item
            //Is only set as null at the start till the input is check (no
            //invalid input as that is checked in the InputHandler)
            Item itemInUse = null;
            switch (input) {
                case "Q": //Q slot item
                    itemInUse = this.player.getItems().get(0);
                    break;
                case "W": //W slot item
                    itemInUse = this.player.getItems().get(1);
                    break;
                case "E": //E slot item
                    itemInUse = this.player.getItems().get(2);
                    break;
                case "R": //R slot item
                    itemInUse = this.player.getItems().get(3);
                    break;
            }
            this.player.useItem(itemInUse, this.enemy);
            
            //Update display
            updateBattle();
            
            //Display battle action message
            actionsTaken += "YOU USED " + itemInUse.getName() + "!<br>";
        }
        
        //Check if the enemy is dead
        if (this.enemy.getHealth() <= 0) {
            this.gameController.setItemToBeCollected(this.enemy.getReward());
            this.dungeonCrawl.getViewController().switchPanels(ViewController.Panel.ENEMYKILLED);
            String rewardText = "";
            if (this.enemy.getReward() != null) {
                rewardText = "REWARD: " + this.enemy.getReward().getName();
            }
            this.dungeonCrawl.getViewController().getEnemyKilledPanel().updateLabels("YOU KILLED " + this.enemy.getName() + "!", rewardText);
            this.playersTurn = true;
            return;
        }

        //Enemy attacks
        actionsTaken += enemyAttack();

        //Check if player is dead
        if (this.player.getHealth() <= 0) { 
            this.dungeonCrawl.getViewController().switchPanels(ViewController.Panel.PLAYERDIED);
        }
        
        this.dungeonCrawl.getViewController().getBattlePanel().updateBattleActionLabel(actionsTaken);
        
        //Allow another player attack
        this.playersTurn = true;
    }
    
    private String enemyAttack() {
        String actionsTaken = "";
        //Check if the enemy is not stunned
        if (this.enemy.getStunTime() == 0) {
            //Enemy attacks
            Item enemyItem = this.enemy.attack(this.player);
            
            //Update display
            updateBattle();
            
            //Display battle action message
            actionsTaken += this.enemy.getName() + " USED " + enemyItem.getName() + "!<br>";
            if (this.player.getStunTime() > 0) {
                actionsTaken += "YOU ARE STUNNED!<br>";
            }
        } else {
            actionsTaken += this.enemy.getName() + " IS STUNNED!<br>";
        }
        return actionsTaken;
    }
    
    public Enemy getEnemy() {
        return this.enemy;
    }

    public PlayerPosition getLastPlayerPos() {
        return this.lastPlayerPos;
    }
    
    public boolean getPlayersTurn() {
        return this.playersTurn;
    }
}