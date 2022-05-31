package dungeon_crawl;

/*
 * @author Kieran
 */
public class Flight extends Item {

    public Flight() {
        super("FLIGHT", 0, 1, 1, false);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (+" + getHeal() + " HP, " + getTurnsStunned() + " TURNS STUNNED)";
    }
}