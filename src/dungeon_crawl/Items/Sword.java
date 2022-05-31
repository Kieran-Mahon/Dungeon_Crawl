package dungeon_crawl.Items;

/*
 * @author Kieran
 */
public class Sword extends Item {

    public Sword() {
        super("SWORD", 40, 0, 0, false);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + getDamage() + " DMG)";
    }
}