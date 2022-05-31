package dungeon_crawl;

/*
 * @author Kieran
 */
public class PlayerData implements Comparable<PlayerData> {
    private final String name;
    private int[] levelTimes;
    private Integer finalTime;

    //For first time
    public PlayerData(String name) {
        this.name = name;
        this.levelTimes = new int[] {-1,-1,-1};
        //Placeholders for final data
        this.finalTime = -1;
    }
    
    //For loading game
    public PlayerData(String name, int[] levelTimes, int finalTime) {
        this.name = name;
        this.levelTimes = levelTimes;
        this.finalTime = finalTime;
    }

    public String getName() {
        return this.name;
    }

    //Returns full array of level times
    public int[] getLevelTimes() {
        return this.levelTimes;
    }
    
    //Returns a value from level times
    public int getLevelTime(int index) {
        return this.levelTimes[index];
    }
    
    public void setLevelTime(int index, int time) {
        this.levelTimes[index] = time;
    }
    
    public int getFinalTime() {
        return this.finalTime;
    }

    public void setFinalTime(Integer finalTime) {
        this.finalTime = finalTime;
    }
    
    public void updateFinalTime() {
        this.finalTime = this.levelTimes[0] + this.levelTimes[1] + this.levelTimes[2];
    }
    
    @Override
    public String toString() {
        return this.name.toUpperCase() + " - " + TimeManager.convertSecondToStringTime(this.finalTime);
    }

    @Override
    public int compareTo(PlayerData o) {
        return this.finalTime.compareTo(o.finalTime);
    }
}