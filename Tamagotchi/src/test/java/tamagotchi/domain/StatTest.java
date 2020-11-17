package tamagotchi.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Heli
 */
public class StatTest {
    
    Stat stat;
    
    public StatTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stat = new Stat(10);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {}
    
    @Test
    public void constructorSetsInitialValueCorrectly() {
        assertTrue(stat.getValue() == 10);
    }
    
    @Test
    public void defaultDecreaseDecreasesValueByOne() {
        stat.decrease();
        assertTrue(stat.getValue() == 9);
    }
    
    @Test
    public void decreaseDecreasesValueByGivenAmount() {
        stat.decrease(5);
        assertTrue(stat.getValue() == 5);
    }
    
    @Test
    public void statValueWontGoBelowMin() {
        stat.decrease(20);
        assertTrue(stat.getValue() == 0);
    }
    
    @Test
    public void defaultIncreaseIncreasesValueByOne() {
        stat.increase();
        assertTrue(stat.getValue() == 11);
    }
    
    @Test
    public void increaseIncreasesValueByGivenAmount() {
        stat.increase(10);
        assertTrue(stat.getValue() == 20);
    }
    
    @Test
    public void statValueWontGoAboveMax() {
        stat.increase(110);
        assertTrue(stat.getValue() == 100);
    }
    
    @Test
    public void setValueSetsNewValue() {
        stat.setValue(50);
        assertTrue(stat.getValue() == 50);
    }
    
    @Test
    public void toStringWorksProperly() {
        assertEquals(stat.toString(), "10");
    }
    
    @Test
    public void getMinReturnsZero() {
        assertTrue(stat.getMin() == 0);
    }
    
    @Test
    public void getMaxReturnsOneHundred() {
        assertTrue(stat.getMax() == 100);
    }
    
}
