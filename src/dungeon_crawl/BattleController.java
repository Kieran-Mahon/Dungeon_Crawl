package dungeon_crawl;

/*
 * @author Kieran
 */
public class BattleController {
    private Enemy enemy;
    private Player player;
    
    public boolean battle(Enemy e, Player p) {
        this.enemy = e;
        this.player = p;
        this.player.resetPlayerCombat();
        
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
    
    private void playerAttack() {
        //Get player item input
        InputHandler battleInput = new InputHandler(new String[] {"Q", "W", "E", "R"});
        String input = battleInput.getInput();

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
    }
}