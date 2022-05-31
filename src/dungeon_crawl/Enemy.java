package dungeon_crawl;

import java.util.ArrayList;

/*
 * @author Kieran
 */
public abstract class Enemy implements GameObject {
    
    //Enemy name
    private final String name;
    //Current health
    private int health;
    //Max health
    private final int maxHealth;
    //Amont of turns stunned
    private int stunTime;
    //If the enemy is blocking the next attack
    private boolean blocking;
    //Reward for killing the enemy
    private final Item reward;
    //Enemy attacks
    private final ArrayList<Item> attacks = new ArrayList<Item>();

    public Enemy(String name, int maxHealth, Item reward) {
        this.name = name;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.stunTime = 0;
        this.blocking = false;
        this.reward = reward;
    }

    public int getHealth() {
        return health;
    }
    
    //Heal enemy but onlt to its max health
    public void heal(int amount) {
        if ((this.health + amount) > this.maxHealth) {
            this.health = this.maxHealth;
        } else {
            this.health += amount;
        }
    }
    
    public void takeDamage(int amount) {
        this.health -= amount;
    }

    public int getStunTime() {
        return this.stunTime;
    }
    
    public void stun(int turns) {
        this.stunTime = turns;
    }

    public boolean isBlocking() {
        return this.blocking;
    }

    public Item getReward() {
        return this.reward;
    }
    
    //Used in battle (opposite to the player version of useItem())
    public void useItem(Item item, Player player) {
        //Player effects
        if (player.isBlocking() == false) { //No effects if enemy is blocking
            player.takeDamage(item.getDamage());
            //Do not allow multiple stuns
            if (player.getStunTime() == 0) {
                player.stun(item.getTurnsStunned());
            }
        }
        //Enemy effects
        heal(item.getHeal());
        this.blocking = item.getCanBlock();
        
        //Show the item used screen
        new ItemUsedScreen(this.name, item, false).displayScreen();
        
        //Show stun message if player is stunned
        if (player.getStunTime() != 0) {
            //Display the stun screen
            new StunnedScreen().displayScreen();
        }
        
        //Show the enter to continue message
        System.out.println("| PRESS ENTER TO CONTINUE                   |");
        System.out.println("#-------------------------------------------#");
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    public void addAttack(Item newAttack) {
        this.attacks.add(newAttack);
    }

    public ArrayList<Item> getAttacks() {
        return this.attacks;
    }
    
    public Item getAttack(int index) {
        return this.attacks.get(index);
    }
    
    abstract public void attack(Player player);
    
    @Override
    public String getGridName() {
        return "E";
    }
}