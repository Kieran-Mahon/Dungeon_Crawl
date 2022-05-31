package dungeon_crawl;

/*
 * @author Kieran
 */
public class Bite extends Item {

    public Bite() {
        super("BITE", 10, 0, 0, false);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + getDamage() + " DMG)";
    }
}