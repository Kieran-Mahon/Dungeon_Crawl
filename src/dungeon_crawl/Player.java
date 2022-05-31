package dungeon_crawl;

import dungeon_crawl.Items.Item;
import dungeon_crawl.Enemies.Enemy;
import dungeon_crawl.Items.Punch;
import java.util.ArrayList;
/*
 * @author Kieran
 */
public class Player implements GameObject {
    
    //Player data (name, level completion times)
    private PlayerData playerData;
    //Player position
    private PlayerPosition playerPosition = new PlayerPosition(0,0);
    //Player current Level
    private int currentLevel;
    //Player current level time
    private int currentLevelTime;
    //Current health
    private int health;
    //Max health
    private final int maxHealth = 100;
    //Amount of turns stunned for
    private int stunTime;
    //If the next move is a blocking move (cancels all damage)
    private boolean blocking;
    //Current items the player has (max of 4 items)
    private ArrayList<Item> items;
    
    //Used for new players
    public Player(PlayerData playerData) {
        this.playerData = playerData;
        this.currentLevel = 1;
        //Set up player
        resetPlayer();
    }
    
    //Used for loading saved players
    public Player(PlayerData playerData, PlayerPosition playerPosition, int currentLevel, int currentLevelTime, ArrayList<Item> items) {
        this.playerData = playerData;
        this.playerPosition = playerPosition;
        this.currentLevel = currentLevel;
        this.currentLevelTime = currentLevelTime;
        //Load default items
        loadDefaultItems();
        //Reset player for combat
        resetPlayerCombat();
        if (items != null) {
            //Replace default item with the loaded items
            for (int i = 0; i < items.size(); i++) {
                this.items.set(i, items.get(i));
            }
        }
    }
    
    private void loadDefaultItems(){
        items = new ArrayList<>();
        //Default 4 items
        items.add(new Punch());
        items.add(new Punch());
        items.add(new Punch());
        items.add(new Punch());
    }

    public PlayerData getPlayerData() {
        return this.playerData;
    }

    public PlayerPosition getPlayerPosition() {
        return this.playerPosition;
    }

    public void setPlayerPosition(PlayerPosition playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    
    public int getCurrentLevelTime() {
        return this.currentLevelTime;
    }

    public void setCurrentLevelTime(int currentLevelTime) {
        this.currentLevelTime = currentLevelTime;
    }
    
    public ArrayList<Item> getItems() {
        return this.items;
    }
    
    public void setItem(int index, Item item) {
        this.items.set(index, item);
    }
    
    public void addItem(Item item) {
        this.items.add(item);
    }
    
    public int getHealth() {
        return this.health;
    }

    public void takeDamage(int amount) {
        this.health -= amount;
    }
    
    public void heal(int amount) {
        if ((this.health + amount) > this.maxHealth) {
            this.health = this.maxHealth;
        } else {
            this.health += amount;
        }
    }

    public boolean isBlocking() {
        return this.blocking;
    }

    public int getStunTime() {
        return this.stunTime;
    }

    public void stun(int stunnedTime) {
        this.stunTime = stunnedTime;
    }
    
    //Use item (used in battle)
    public void useItem(Item item, Enemy enemy) {
        //Enemy effects
        if (enemy.isBlocking() == false) { //No effects if enemy is blocking
            enemy.takeDamage(item.getDamage());
            //Do not allow multiple stuns
            if (enemy.getStunTime() == 0) {
                enemy.stun(item.getTurnsStunned());
            }
        }
        //Player effects
        heal(item.getHeal());
        this.blocking = item.getCanBlock();
        
        //Show the item used screen
        new ItemUsedScreen("YOU", item, true).displayScreen();
    }
    
    public void resetPlayer() {
        this.currentLevelTime = 0;
        resetPlayerCombat();
        loadDefaultItems();
    }
    
    public void resetPlayerCombat() {
        this.health = maxHealth;
        this.stunTime = 0;
        this.blocking = false;
    }
    
    @Override
    public String getGridName() {
        return "X";
    }

    @Override
    public String getName() {
        return "PLAYER";
    }
}