package dungeon_crawl.Controllers;

import dungeon_crawl.BattleLostScreen;
import dungeon_crawl.BattleScreen;
import dungeon_crawl.BattleWonScreen;
import dungeon_crawl.Items.Item;
import dungeon_crawl.Enemies.Enemy;
import dungeon_crawl.Utilities.InputHandler;
import dungeon_crawl.ItemReplacer;
import dungeon_crawl.Player;
import dungeon_crawl.PlayerPosition;
import dungeon_crawl.WaitingForEnemyScreen;

/*
 * @author Kieran
 */
public class BattleController {
    private Enemy enemy;
    private Player player;
    //Used to reset player to last location if they die
    private PlayerPosition lastPlayerPos;
    private DungeonCrawl dungeonCrawl;
    
    public boolean enemyTurn = false;
    
    public BattleController(PlayerPosition currentPlayerPos, DungeonCrawl dungeonCrawl) {
        this.lastPlayerPos = currentPlayerPos;
        this.dungeonCrawl = dungeonCrawl;
    }
        
    public boolean startBattle(Enemy e, Player p) {
        this.enemy = e;
        this.player = p;
        this.player.resetPlayerCombat();
        
        //Update battle panel
        updateBattle();
        
        
        boolean playerWon = false;
        while (true) {
            if (this.player.getStunTime() == 0) { //Check if not stunned
                //Display the battle screen (items are shown)
                new BattleScreen(this.enemy, this.player, true).displayScreen();

                //Player attacks
                playerAttack();
                //Wait for enter key
                new InputHandler().getEnterKey();
            }
            
            //Display the battle screen afer player attack (items are hidden)
            new BattleScreen(this.enemy, this.player, false).displayScreen();
            
            //Check if the enemy is not dead
            if (this.enemy.getHealth() > 0) { 
                //Check if the enemy is not stunned
                if (this.enemy.getStunTime() == 0) {
                    //Display waiting for enemy screen
                    new WaitingForEnemyScreen(this.enemy).displayScreen();

                    //Try to wait 1 seconds to make it look like the enemy is thinking
                    try {
                        Thread.sleep(1000); //1000 = 1 seconds
                    } catch(InterruptedException x) {
                        Thread.currentThread().interrupt();
                    }

                    //Enemy attacks
                    this.enemy.attack(this.player);
                    //Wait for enter key
                    new InputHandler().getEnterKey();
                }
            } else { //Enemy is dead
                //Player won - set playerWon to true and break the battle loop
                playerWon = true;
                break;
            }
            
            //Check if the player is dead
            if (this.player.getHealth() <= 0) { 
                //player lost - break battle loop
                break;
            }
        }
        
        //Show the correct won/lost screen
        if (playerWon == true) {
            BattleWonScreen battleWonScreen = new BattleWonScreen(enemy);
            battleWonScreen.displayScreen();
        } else {
            BattleLostScreen battleLostScreen = new BattleLostScreen();
            battleLostScreen.displayScreen();
        }
        
        //Wait for enter key
        new InputHandler().getEnterKey();
        
        if (playerWon == true) {
            //Check if the enemy has a reward
            if (this.enemy.getReward() != null) {
                //Replace item with reward
                new ItemReplacer().startItemReplacing((Item) this.enemy.getReward(), this.player);
            }
        }
        
        //Return true if the player won
        return playerWon;
    }
    
    private void updateBattle() {
        //Display enemy and player info
        this.dungeonCrawl.getViewController().getBattlePanel().updateEnemy(this.enemy.getName(), "HEALTH: " + this.enemy.getHealth());
        this.dungeonCrawl.getViewController().getBattlePanel().updatePlayer("HEALTH: " + this.player.getHealth());
        
        //Display player items
        String[] playerItems = new String[this.player.getItems().size()];
        for (int i = 0; i < playerItems.length; i++) {
            playerItems[i] = this.player.getItems().get(i).getInfo();
        }
        this.dungeonCrawl.getViewController().getBattlePanel().updateItemButtions(playerItems);
    }
    
    public void playerAttack(String input) {
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
            this.player.useItem(itemInUse, this.enemy, this.dungeonCrawl);
            this.dungeonCrawl.getViewController().getBattlePanel().updateBattleActionLabel(input);
        }
    }
}