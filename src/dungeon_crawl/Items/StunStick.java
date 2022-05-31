package dungeon_crawl.Items;

import dungeon_crawl.Items.Item;

/*
 * @author Kieran
 */
public class StunStick extends Item {

    public StunStick() {
        super("STUN_STICK", 0, 0, 1, false);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + getTurnsStunned() + " TURNS STUNNED)";
    }
}