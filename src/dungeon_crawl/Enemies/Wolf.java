package dungeon_crawl.Enemies;

import dungeon_crawl.Items.Item;
import dungeon_crawl.Items.BigBite;
import dungeon_crawl.Items.Bite;
import dungeon_crawl.Player;
import java.util.Random;

/*
 * @author Kieran
 */
public class Wolf extends Enemy {

    public Wolf() {
        super("WOLF", 100, null);
        addAttack(new BigBite());
        addAttack(new Bite());
    }
    
    @Override
    public void attack(Player player) {
        Random rand = new Random();
        int rndNum = rand.nextInt(3); //0, 1, 2, or 3
        Item attackItem = null;
        
        //2 Attacks, 1 with a 25% chance and 1 with a 75% chance
        if (rndNum == 0) { //25% chance
            attackItem = getAttack(0);
        } else { //75% chance
            attackItem = getAttack(1);
        }
        
        //Attack the player
        this.useItem(attackItem, player);
    }
}