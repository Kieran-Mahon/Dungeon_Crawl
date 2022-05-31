package dungeon_crawl.Enemies;

import dungeon_crawl.Items.Item;
import dungeon_crawl.Items.Scratch;
import dungeon_crawl.Items.Flight;
import dungeon_crawl.Player;
import java.util.Random;

/*
 * @author Kieran
 */
public class Bird extends Enemy {

    public Bird() {
        super("BIRD", 30, null);
        addAttack(new Scratch());
        addAttack(new Flight());
    }
    
    @Override
    public void attack(Player player) {
        Random rand = new Random();
        int rndNum = rand.nextInt(9); //0 to 19 (20 values)
        Item attackItem = null;
        
        //2 Attacks, 1 with a 5% chance and 1 with a 95% chance
        if (rndNum == 0) { //5% chance
            attackItem = getAttack(0);
        } else { //95% chance
            attackItem = getAttack(1);
        }
        
        //Attack the player
        this.useItem(attackItem, player);
    }
}