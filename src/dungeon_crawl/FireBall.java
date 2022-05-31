package dungeon_crawl;

/*
 * @author Kieran
 */
public class FireBall extends Item {

    public FireBall() {
        super("FIRE_BALL", 20, 0, 0, false);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + getDamage() + " DMG)";
    }
}