package dungeon_crawl;

/*
 * @author Kieran
 */
public class Shield extends Item {

    public Shield() {
        super("SHIELD", 0, 0, 0, true);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (BLOCKS INCOMING DMG)";
    }
}