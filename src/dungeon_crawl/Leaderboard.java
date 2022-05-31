/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon_crawl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Kieran
 */
public class Leaderboard {
    
    //Array list to hold 5 leaderboard leaders
    private ArrayList<PlayerData> leaders;
    //Used to only load the leaderboard data on the first time loaded to save
    //on reads from file
    private boolean firstLoad = true;
    
    public Leaderboard() {
        this.leaders = new ArrayList<>();
        loadLeaderboard();
    }
        
    public void displayLeaderboard(){
        //Check if the leaderboard list is not empty
        if (this.leaders.isEmpty() == false) {
            int i = 1;
            for (PlayerData leader : this.leaders) {
                System.out.println(Screen.lineBorder(i + ": " + leader.toString()));
                i++;
            }
        } else {
            System.out.println("| NO LEADERS                                |");
        }
    }
    
    public void tryToAdd(PlayerData player) {
        //Check for space
        if (this.leaders.size() < 5) {
            //Add leader to list
            addNewLeader(player);
        } else {
            //Check last place to see if the new player is better than the worst best player
            if (this.leaders.get(4).getFinalTime() > player.getFinalTime()) {
                //Remove worst best player
                this.leaders.remove(4);
                //Add leader to list
                addNewLeader(player);
            }
        }
    }
    
    private void addNewLeader(PlayerData leader) {
        //Add new player
        this.leaders.add(leader);
        //Sort leaderboard
        Collections.sort(this.leaders);
        //Save leaderboard to file
        IOController.saveLeaderBoard(this.leaders);
    }
    
    private void loadLeaderboard() {
        //Make sure only load leaderboard data the first time
        if (this.firstLoad == false) {
            return;
        }
        this.firstLoad = false;
        
        ArrayList<PlayerData> tempLeaders = IOController.loadLeaderBoard();
        //Check if the board is not empty
        if (tempLeaders != null) {
            this.leaders = tempLeaders;
        }
    }
}