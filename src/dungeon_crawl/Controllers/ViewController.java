package dungeon_crawl.Controllers;

import dungeon_crawl.Panels.*;
import javax.swing.*;

/*
 * @author Kieran
 */
public class ViewController extends JFrame {

    //Panels
    private StartPanel startPanel;
    private LoadErrorPanel loadErrorPanel;

    public ViewController() {
        super("Dungeon Crawl");
        //Set up frame
        this.setSize(600, 400);
        this.setResizable(false);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        //Set up panels
        initComponents();
        
        //Pack everything
        pack();
    }
    
    private void initComponents() {
        /*this.loadErrorPanel = new LoadErrorPanel();
        this.loadErrorPanel.setVisible(false);
        add(this.loadErrorPanel);
        
        this.startPanel = new StartPanel();
        this.startPanel.setVisible(true);
        add(this.startPanel);*/
        
        add(new GamePanel());
    }
    
    
    public static void main(String[] args) {
        ViewController view = new ViewController();
        view.setVisible(true);
    }
}