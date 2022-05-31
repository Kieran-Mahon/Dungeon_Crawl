package dungeon_crawl;

/*
 * @author Kieran
 */
public class Punch extends Item {

    public Punch() {
        super("PUNCH", 20, 0, 0, false);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + getDamage() + " DMG)";
    }
}