package dungeon_crawl;

/*
 * @author Kieran
 */
public class Door implements GameObject {
    
    private final String name = "DOOR";

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getGridName() {
        return "D";
    }
}