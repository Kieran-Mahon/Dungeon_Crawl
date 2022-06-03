/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon_crawl;

import dungeon_crawl.Enemies.Enemy;
import dungeon_crawl.Items.Item;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kieran
 */
public class PlayerTest {
    private Player instance;
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.instance = new Player(new PlayerData("fake name"), new PlayerPosition(10, 10), 2, 30, new ArrayList<>());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPlayerData method, of class Player.
     */
    @Test
    public void testGetPlayerDataNull() {
        System.out.println("getPlayerDataNull");
        this.instance = new Player(null);
        PlayerData result = instance.getPlayerData();
        assertEquals(null, result);
    }

    /**
     * Test of getPlayerPosition method, of class Player.
     */
    @Test
    public void testGetPlayerPosition() {
        System.out.println("getPlayerPosition");
        PlayerPosition result = instance.getPlayerPosition();
        assertNotEquals(new PlayerPosition(10, 10), result);
    }

    /**
     * Test of getPlayerPosition method, of class Player.
     */
    @Test
    public void testGetPlayerPositionNull() {
        System.out.println("getPlayerPositionNull");
        this.instance = new Player(null);
        PlayerPosition exp = new PlayerPosition(123,123);
        this.instance.setPlayerPosition(exp);
        PlayerPosition result = instance.getPlayerPosition();
        assertEquals(exp, result);
    }
    
    /**
     * Test of setPlayerPosition method, of class Player.
     */
    @Test
    public void testSetPlayerPosition() {
        System.out.println("setPlayerPosition");
        PlayerPosition exp = new PlayerPosition(123,123);
        this.instance.setPlayerPosition(exp);
        PlayerPosition result = instance.getPlayerPosition();
        assertEquals(exp, result);
    }
    
    /**
     * Test of setPlayerPosition method, of class Player.
     */
    @Test
    public void testSetPlayerPositionNull() {
        System.out.println("setPlayerPositionNull");
        this.instance = new Player(null);
        PlayerPosition exp = new PlayerPosition(123,123);
        this.instance.setPlayerPosition(exp);
        PlayerPosition result = instance.getPlayerPosition();
        assertEquals(exp, result);
    }

    /**
     * Test of setPlayerPosition method, of class Player.
     */
    @Test
    public void testSetPlayerPositionNull2() {
        System.out.println("setPlayerPosition");
        PlayerPosition exp = null;
        this.instance.setPlayerPosition(exp);
        PlayerPosition result = instance.getPlayerPosition();
        assertEquals(exp, result);
    }
}