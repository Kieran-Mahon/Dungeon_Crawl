package dungeon_crawl.Items;

/*
 * @author Kieran
 */
public class HealthPotion extends Item {

    public HealthPotion() {
        super("HEALTH_POTION", 0, 30, 0, false);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (+" + getHeal() + " HP)";
    }
}        