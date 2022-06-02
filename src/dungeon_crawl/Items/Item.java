package dungeon_crawl.Items;

import dungeon_crawl.GameObject;
import dungeon_crawl.Player;

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
    
    public String getInfo(boolean nameWanted) {
        String toReturn = "<html>";
        if (nameWanted == true) {
            toReturn += "<b>" + this.name + "</b><br>";
        }
        if (this.damage > 0) {
            toReturn += this.damage + " DAMAGE<br>";
        }
        if (this.heal > 0) {
            toReturn += "+" + this.heal + " HEALTH<br>";
        }
        if (this.turnsStunned > 0) {
            toReturn += this.turnsStunned + " TURNS STUNNED<br>";
        }
        if (this.canBlock == true) {
            toReturn += "BLOCKS DMG NEXT TURN<br>";
        }
        
        return (toReturn + "</html>");
    }
    
    public static boolean startItemReplacing(int slot, Item newItem, Player player) {
        if (slot != -1) {
            //Replace item
            player.setItem(slot, newItem);
            return true;
        }
        return false;
    }
}