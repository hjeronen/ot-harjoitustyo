package tamagotchi.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Tests for Stat-class.
 * 
 * @author Heli
 */
public class StatTest {
    
    Stat stat;
    
    
    @Before
    public void setUp() {
        stat = new Stat(10.0);
    }
    
    
    @Test
    public void constructorSetsInitialValueCorrectly() {
        assertTrue(stat.getValue() == 10.0);
    }
    
    @Test
    public void decreaseDecreasesValueByGivenAmount() {
        stat.decrease(5.0);
        assertTrue(stat.getValue() == 5.0);
    }
    
    @Test
    public void statValueWontGoBelowMin() {
        stat.decrease(20.0);
        assertTrue(stat.getValue() == 0.0);
    }
    
    @Test
    public void increaseIncreasesValueByGivenAmount() {
        stat.increase(10.0);
        assertTrue(stat.getValue() == 20.0);
    }
    
    @Test
    public void statValueWontGoAboveMax() {
        stat.increase(110.0);
        assertTrue(stat.getValue() == 100.0);
    }
    
    @Test
    public void setValueSetsNewValue() {
        stat.setValue(50.0);
        assertTrue(stat.getValue() == 50.0);
    }
    
    @Test
    public void toStringWorksProperly() {
        assertEquals(stat.toString(), "10.00");
    }
    
    @Test
    public void getMinReturnsZero() {
        assertTrue(stat.getMin() == 0.0);
    }
    
    @Test
    public void getMaxReturnsOneHundred() {
        assertTrue(stat.getMax() == 100.0);
    }
    
}
