package dungeon_crawl.Utilities;

import java.util.Scanner;
/*
 * @author Kieran
 */
public class InputHandler {
    private final String[] allowedStrings;
    
    public InputHandler() {
        this.allowedStrings = new String[]{};
    }
    
    public InputHandler(String[] allowedStrings) {
        this.allowedStrings = allowedStrings;
    }
    
    //Only alloweds allowed strings to be inputted
    public String getInput() {
        String input = "";
        while (true) {
            //Get input
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();

            //Check if input is valid
            boolean validInput = false;
            for (int i = 0; i < this.allowedStrings.length; i++) {
                if (input.equalsIgnoreCase(this.allowedStrings[i])) {
                    validInput = true;
                    input = input.toUpperCase();
                }
            }

            //Give error message is invalid
            if (validInput == false) {
                input = null;
                invalidInputMessage();
            }
            
            //If valid input then break;
            if (validInput == true) {
                break;
            }
        }
        //Return the input
        return input;
    }
    
    //Looks for the enter key
    public void getEnterKey() {
        //Waits for enter key to be pressed
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
    //Allow all but empty inputs (0 max chars means no limit)
    public String getNonEmptyInput(int maxChars) {
        String input = "";
        while (true) {
            //Check for non empty input
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if (input.isEmpty()) {
                input = null;
                //Display invalid input message
                invalidInputMessage();
            } else {
                //Check if the input is equal or less than the max allowed or if it has no limit
                if ((maxChars == 0) || (input.length() <= maxChars)) {
                    //Break if allowed input is found
                    break;
                } else {
                    //Tell the user that they used too many characters
                    System.out.println("#-------------------------------------------#");
                    System.out.println("| TOO MANY CHARACTERS INPUTTED              |");
                    System.out.println("#-------------------------------------------#");
                }
            }
        }
        return input;
    }
    
    private void invalidInputMessage(){
        System.out.println("#-------------------------------------------#");
        System.out.println("| INVALID INPUT                             |");
        System.out.println("#-------------------------------------------#");
    }
}