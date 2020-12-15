
package tamagotchi.logic;

import java.time.LocalDate;
import java.time.ZoneId;
import org.junit.Before;
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
    public void getHappinessModifierReturnsOneWhenHappinessIsMaxed() {
        this.pet.getHappiness().setValue(100.0);
        assertTrue(this.manager.getHappinessModifier() == 1);
    }
    
    @Test
    public void getHappinessModifierReturnsTwoWhenHappinessIsUnderSeventyFive() {
        this.pet.getHappiness().setValue(74.0);
        assertTrue(this.manager.getHappinessModifier() == 2);
    }
    
    @Test
    public void getHappinessModifierReturnsThreeWhenHappinessIsUnderFifty() {
        this.pet.getHappiness().setValue(49.0);
        assertTrue(this.manager.getHappinessModifier() == 3);
    }
    
    
    @Test
    public void updateEnergyDecreasesEnergyRightWhenHappinessIsMaxed() {
        this.pet.getHappiness().setValue(100.0);
        this.manager.updateEnergy(10000);
        assertTrue(this.pet.getEnergy().getValue() == 45.5);
    }
    
    @Test
    public void updateEnergyDecreasesEnergyRightWhenHappinessModifierIsTwo() {
        this.pet.getHappiness().setValue(74.0);
        this.manager.updateEnergy(10000);
        assertTrue(this.pet.getEnergy().getValue() == 41.0);
    }
    
    @Test
    public void updateEnergyDecreasesEnergyRightWhenHappinessModifierIsThree() {
        this.pet.getHappiness().setValue(49.0);
        this.manager.updateEnergy(10000);
        assertTrue(this.pet.getEnergy().getValue() == 36.5);
    }
    
    @Test
    public void updateHappinessDecreasesHappinessRight() {
        this.manager.updateHappiness(10000);
        assertTrue(this.pet.getHappiness().getValue() == 41.0);
    }
    
    @Test
    public void updateHealthDoesNotDecreaseHealthIfPetIsNotSick() {
        this.pet.getHygiene().setValue(100.0);
        this.manager.updateHealth(10000);
        assertTrue(this.pet.getHealth().getValue() == 50.0);
    }
    
    @Test
    public void updateHealthDoesDecreaseHealthFastIfPetIsSick() {
        this.pet.setIsSick(true);
        this.pet.getHygiene().setValue(100.0);
        this.manager.updateHealth(10000);
        assertTrue(this.pet.getHealth().getValue() == 14);
    }
    
    @Test
    public void updateHealthDecreasesHealthIfEnergyIsAtZero() {
        this.pet.getEnergy().setValue(0);
        this.pet.getHygiene().setValue(100.0);
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
        
        assertTrue(this.pet.getEnergy().getValue() == 36.5);
        assertTrue(this.pet.getHappiness().getValue() == 41.0);
        assertTrue(this.pet.getHygiene().getValue() == 45.5);
        assertTrue(this.pet.getHealth().getValue() == 32.0);
    }
    
    @Test
    public void calculatePetStatsDecreasesHealthAndEnergyFromMaxToZeroInOneDayInStageOne() {
        this.pet.getEnergy().setValue(100.0);
        this.pet.getHappiness().setValue(100.0);
        this.pet.getHealth().setValue(100.0);
        this.pet.getHygiene().setValue(100.0);
        
        LocalDate date = LocalDate.now().minusDays(1);
        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = date.atStartOfDay(zoneId).toEpochSecond();
        this.pet.setLastLogin(epoch);
        this.manager.calculatePetStats();
        
        assertTrue(this.pet.getEnergy().getValue() == 0.0);
        assertTrue(this.pet.getHealth().getValue() == 0.0);
    }
    
    @Test
    public void calculatePetStatsDecreasesHealthAndEnergyFromMaxToZeroInOneDayInStageTwo() {
        this.pet.getEnergy().setValue(100.0);
        this.pet.getHappiness().setValue(100.0);
        this.pet.getHealth().setValue(100.0);
        this.pet.getHygiene().setValue(100.0);
        
        LocalDate date = LocalDate.now().minusDays(1);
        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = date.atStartOfDay(zoneId).toEpochSecond();
        this.pet.setLastLogin(epoch);
        
        LocalDate newBirthday = LocalDate.now().minusDays(4);
        String birthday = newBirthday.toString();
        this.pet.setBirthday(birthday);
        
        this.manager.calculatePetStats();
        
        assertTrue(this.pet.getEnergy().getValue() == 0.0);
        assertTrue(this.pet.getHealth().getValue() == 0.0);
    }
     
    @Test
    public void calculatePetStatsDecreasesHealthAndEnergyFromMaxToZeroInTwoDaysInStageThree() {
        this.pet.getEnergy().setValue(100.0);
        this.pet.getHappiness().setValue(100.0);
        this.pet.getHealth().setValue(100.0);
        this.pet.getHygiene().setValue(100.0);
        
        LocalDate date = LocalDate.now().minusDays(2);
        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = date.atStartOfDay(zoneId).toEpochSecond();
        this.pet.setLastLogin(epoch);
        
        LocalDate newBirthday = this.pet.getBirthday().minusDays(8);
        String birthday = newBirthday.toString();
        this.pet.setBirthday(birthday);
        
        this.manager.calculatePetStats();
        
        assertTrue(this.pet.getEnergy().getValue() == 0.0);
        assertTrue(this.pet.getHealth().getValue() == 0.0);
    }
    
    @Test
    public void calculatePetStatsDoesNotDecreaseEnergyAndHealthFromMaxToZeroInOneDayInStageThree() {
        this.pet.getEnergy().setValue(100.0);
        this.pet.getHappiness().setValue(100.0);
        this.pet.getHealth().setValue(100.0);
        this.pet.getHygiene().setValue(100.0);
        
        LocalDate date = LocalDate.now().minusDays(1);
        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = date.atStartOfDay(zoneId).toEpochSecond();
        this.pet.setLastLogin(epoch);
        
        LocalDate newBirthday = this.pet.getBirthday().minusDays(8);
        String birthday = newBirthday.toString();
        this.pet.setBirthday(birthday);
        
        this.manager.calculatePetStats();
        
        assertFalse(this.pet.getEnergy().getValue() == 0.0);
        assertFalse(this.pet.getHealth().getValue() == 0.0);
    }
    
}
