package dungeon_crawl.Utilities;

/*
 * @author Kieran
 */
public class TimeManager {
    //Both in seconds
    private final int timeStart;

    public TimeManager() {
        this.timeStart = (int) System.currentTimeMillis() / 1000;
    }
    
    public int timePlayed(){
        return ((int) System.currentTimeMillis() / 1000) - this.timeStart;
    }
    
    public static String convertSecondToStringTime(int seconds) {
        int mins = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String toReturn = "";
        if (mins != 0) {
            toReturn = mins + " MINUTES AND ";
        }
        toReturn += secs + " SECONDS";
        
        return toReturn; 
    }
}