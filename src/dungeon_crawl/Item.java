package dungeon_crawl;

/*
 * @author Kieran
 */
public class Item implements GameObject {
    
    private String name;
    private int damage;
    private int heal;
    private int turnsStunned;
    private boolean canBlock;

    public Item(String name, int damage, int heal, int turnsStunned, boolean canBlock) {
        this.name = name;
        this.damage = damage;
        this.heal = heal;
        this.turnsStunned = turnsStunned;
        this.canBlock = canBlock;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    public int getDamage() {
        return this.damage;
    }

    public int getHeal() {
        return this.heal;
    }

    public int getTurnsStunned() {
        return this.turnsStunned;
    }

    public boolean getCanBlock() {
        return this.canBlock;
    }
    
    @Override
    public String getGridName() {
        return "I";
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}