package dungeon_crawl;

/*
 * @author Kieran
 */
public class BigBite extends Item {

    public BigBite() {
        super("BIG_BITE", 20, 3, 0, false);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (" + getDamage() + " DMG, +" + getHeal() + " HP)";
    }
}