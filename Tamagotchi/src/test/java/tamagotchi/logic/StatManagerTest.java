
package tamagotchi.logic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tamagotchi.domain.Pet;

/**
 * Integration tests for StatManager-class.
 * 
 * @author Heli
 */
public class StatManagerTest {
    StatManager manager;
    Pet pet;
    
    
    @Before
    public void setUp() {
        this.pet = new Pet();
        this.manager = new StatManager(this.pet);
    }
    
    @Test
    public void setPetSetsNewPet() {
        Pet newPet = new Pet();
        newPet.setName("Fluffy");
        Pet oldPet = this.manager.getPet();
        assertEquals(this.manager.getPet().getName(), oldPet.getName());
    }
    
    @Test
    public void updateEnergyDecreasesEnergyRight() {
        this.manager.updateEnergy(10000);
        assertTrue(this.pet.getEnergy().getValue() == 41.0);
    }
    
    @Test
    public void updateHappinessDecreasesHappinessRight() {
        this.manager.updateHappiness(10000);
        assertTrue(this.pet.getHappiness().getValue() == 32.0);
    }
    
    @Test
    public void updateHealthDoesNotDecreaseHealthIfPetIsNotSick() {
        this.pet.setHygiene(100.0);
        this.manager.updateHealth(10000);
        assertTrue(this.pet.getHealth().getValue() == 50.0);
    }
    
    @Test
    public void updateHealthDoesDecreaseHealthFastIfPetIsSick() {
        this.pet.setIsSick(true);
        this.pet.setHygiene(100.0);
        this.manager.updateHealth(10000);
        assertTrue(this.pet.getHealth().getValue() == 14);
    }
    
    @Test
    public void updateHealthDecreasesHealthIfEnergyIsAtZero() {
        this.pet.setEnergy(0);
        this.pet.setHygiene(100.0);
        this.manager.updateHealth(10000);
        assertTrue(this.pet.getHealth().getValue() == 32.0);
    }
    
    @Test
    public void updateHealthDecreasesHealthIfHygieneIsAtFifty() {
        this.manager.updateHealth(10000);
        assertTrue(this.pet.getHealth().getValue() == 32.0);
    }
    
    @Test
    public void updateHygieneDecreasesHygieneFastIfPetNeedsWash() {
        this.pet.setNeedsWash(true);
        this.manager.updateHygiene(10000);
        assertTrue(this.pet.getHygiene().getValue() == 14.0);
    }
    
    @Test
    public void updateHygieneDecreasesHygieneSlowIfPetDoesNotNeedWash() {
        this.pet.setNeedsWash(false);
        this.manager.updateHygiene(10000);
        assertTrue(this.pet.getHygiene().getValue() == 45.5);
    }
    
    @Test
    public void updateStatsUpdatesAllStatsCorrectly() {
        this.manager.updateStats(10000);
        
        assertTrue(this.pet.getEnergy().getValue() == 41.0);
        assertTrue(this.pet.getHappiness().getValue() == 32.0);
        assertTrue(this.pet.getHygiene().getValue() == 45.5);
        assertTrue(this.pet.getHealth().getValue() == 32.0);
    }
    
    @Test
    public void calculatePetStatsDecreasesAllStatsFromFiftyToZeroInOneDayInStageOne() {
        LocalDate date = this.pet.getBirthday().minusDays(1);
        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = date.atStartOfDay(zoneId).toEpochSecond();
        this.pet.setLastLogin(epoch);
        this.manager.calculatePetStats();
        
        assertTrue(this.pet.getEnergy().getValue() == 0.0);
        assertTrue(this.pet.getHappiness().getValue() == 0.0);
        assertTrue(this.pet.getHygiene().getValue() == 0.0);
        assertTrue(this.pet.getHealth().getValue() == 0.0);
    }
    
    @Test
    public void calculatePetStatsDecreasesAllStatsFromFiftyToZeroInTwoDaysInStageTwo() {
        LocalDate date = this.pet.getBirthday().minusDays(2);
        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = date.atStartOfDay(zoneId).toEpochSecond();
        this.pet.setLastLogin(epoch);
        this.manager.calculatePetStats();
        
        assertTrue(this.pet.getEnergy().getValue() == 0.0);
        assertTrue(this.pet.getHappiness().getValue() == 0.0);
        assertTrue(this.pet.getHygiene().getValue() == 0.0);
        assertTrue(this.pet.getHealth().getValue() == 0.0);
    }
    
    @Test
    public void calculatePetStatsDecreasesAllStatsFromFiftyToZeroInTwoDaysInStageThree() {
        LocalDate date = this.pet.getBirthday().minusDays(2);
        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = date.atStartOfDay(zoneId).toEpochSecond();
        this.pet.setLastLogin(epoch);
        this.manager.calculatePetStats();
        
        assertTrue(this.pet.getEnergy().getValue() == 0.0);
        assertTrue(this.pet.getHappiness().getValue() == 0.0);
        assertTrue(this.pet.getHygiene().getValue() == 0.0);
        assertTrue(this.pet.getHealth().getValue() == 0.0);
    }
    
}
