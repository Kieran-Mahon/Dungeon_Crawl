package dungeon_crawl;

import java.util.Random;

/*
 * @author Kieran
 */
public class Dragon extends Enemy {

    public Dragon() {
        super("DRAGON", 500, null);
        addAttack(new GroundSlam());
        addAttack(new FireBall());
    }
    
    @Override
    public void attack(Player player) {
        Random rand = new Random();
        int rndNum = rand.nextInt(9); //0 to 9 (10 values)
        Item attackItem = null;
        
        //2 Attacks, 1 with a 10% chance and 1 with a 90% chance
        if (rndNum == 0) { //10% chance
            attackItem = getAttack(0);
        } else { //90% chance
            attackItem = getAttack(1);
        }
        
        //Attack the player
        this.useItem(attackItem, player);
    }
}