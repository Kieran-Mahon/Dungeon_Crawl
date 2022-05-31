package dungeon_crawl;

/*
 * @author Kieran
 */
public interface Screen {
    public void displayScreen();
    
    public static String lineBorder(String text){
        //Make sure border is correct
        String line = "| " + text;
        int printLength = line.length();
        for (int j = 0; j < 43 - printLength; j++) {
            line += " ";
        }
        return line + " |";
    }
}