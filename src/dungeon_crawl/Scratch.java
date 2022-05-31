package dungeon_crawl;

/*
 * @author Kieran
 */
public class Scratch  extends Item {

    public Scratch() {
        super("SCRATCH", 10, 0, 0, false);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + getDamage() + " DMG)";
    }
}