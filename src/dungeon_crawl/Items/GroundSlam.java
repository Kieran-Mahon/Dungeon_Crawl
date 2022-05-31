package dungeon_crawl.Items;

import dungeon_crawl.Items.Item;

/*
 * @author Kieran
 */
public class GroundSlam extends Item {

    public GroundSlam() {
        super("GROUND_SLAM", 50, 0, 1, false);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + getDamage() + " DMG, " + getTurnsStunned() + " TURNS STUNNED)";
    }
}