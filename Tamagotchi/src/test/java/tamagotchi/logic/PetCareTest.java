package tamagotchi.logic;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import tamagotchi.logic.PetCare;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import tamagotchi.dao.FakePetCemeteryDao;
import tamagotchi.dao.FakePetDao;
import tamagotchi.dao.PetDao;
import tamagotchi.domain.Pet;

/**
 * Integration tests for PetCare-class.
 * 
 * @author Heli
 */
public class PetCareTest {
    PetCare petCare;
    
    
    @Before
    public void setUp() {
        this.petCare = new PetCare(new FakePetDao(), new FakePetCemeteryDao());
    }
    
    @Test
    public void setUpPetDaoSetsNewPetDao() {
        PetDao newDao = new FakePetDao();
        newDao.getPet().setName("Fluffy");
        String oldPetName = this.petCare.getPetDao().getPet().getName();
        this.petCare.setUpPetDao(newDao);
        assertNotEquals(oldPetName, this.petCare.getPetDao().getPet().getName());
    }
    
    @Test
    public void createNewPetSaveCreatesNewSave() throws Exception {
        this.petCare.getPetDao().getPet().setName("Fluffy");
        String oldPetName = this.petCare.getPetDao().getPet().getName();
        this.petCare.createNewPetSave();
        assertNotEquals(oldPetName, this.petCare.getPetDao().getPet().getName());
    }
    
    @Test
    public void saveGameSetsPetsAgeProperly() {
        this.petCare.saveGame();
        assertTrue(this.petCare.getPet().getAge() == 0);
    }
    
    @Test
    public void saveGameSavesPetInfo() {
        String oldPetName = this.petCare.getPetDao().getPet().getName();
        this.petCare.getPet().setName("Gogo");
        this.petCare.saveGame();
        assertNotEquals(oldPetName, this.petCare.getPetDao().getPet().getName());
    }
    
    @Test
    public void getPetDoesNotReturnNull() {
        assertTrue(this.petCare.getPet() != null);
    }
    
    @Test
    public void getStatManagerDoesNotReturnNull() {
        assertTrue(this.petCare.getStatManager() != null);
    }
    
    @Test
    public void feedPetIncreasesEnergyRight() {
        double originalValue = this.petCare.getPet().getEnergy().getValue();
        this.petCare.feedPet();
        double newValue = this.petCare.getPet().getEnergy().getValue();
        assertTrue(newValue - originalValue == 10.0);
    }
    
    @Test
    public void playIncreasesHappinessCorrectly() {
        double originalValue = this.petCare.getPet().getHappiness().getValue();
        this.petCare.play(3);
        double newValue = this.petCare.getPet().getHappiness().getValue();
        assertTrue(newValue - originalValue == 30);
    }
    
    @Test
    public void healPetIncreasesHealthRight() {
        double originalValue = this.petCare.getPet().getHealth().getValue();
        this.petCare.healPet();
        double newValue = this.petCare.getPet().getHealth().getValue();
        assertTrue(newValue - originalValue == 10.0);
    }
    
    @Test
    public void ifPetIsHealedToMaxPetIsNoLongerSick() {
        this.petCare.getPet().setIsSick(true);
        while (this.petCare.getPet().getHealth().getValue() < 100) {
            this.petCare.healPet();
        }
        assertTrue(this.petCare.getPet().getIsSick() == false);
    }
    
    @Test
    public void cleanPetSetsPetHygieneCorrectly() {
        this.petCare.cleanPet();
        assertTrue(this.petCare.getPet().getHygiene().getValue() == 100.0);
    }
    
    @Test
    public void cleanPetSetsNeedsWashValueToFalse() {
        this.petCare.getPet().setNeedsWash(true);
        this.petCare.cleanPet();
        assertTrue(this.petCare.getPet().getNeedsWash() == false);
    }
    
    @Test
    public void petIsAliveReturnsFalseWhenEnergyAndHealthAreAtZero() throws SQLException {
        this.petCare.getPet().getEnergy().setValue(0);
        this.petCare.getPet().getHealth().setValue(0);
        assertTrue(!this.petCare.petIsAlive());
    }
    
    @Test
    public void petIsAliveReturnsTrueWhenOnlyEnergyIsAtZero() throws SQLException {
        this.petCare.getPet().getEnergy().setValue(0);
        assertTrue(this.petCare.petIsAlive());
    }
    
    @Test
    public void petIsAliveReturnsTrueWhenOnlyHealthIsAtZero() throws SQLException {
        this.petCare.getPet().getHealth().setValue(0);
        assertTrue(this.petCare.petIsAlive());
    }
    
    @Test
    public void petDoesNotGetSickIfHealthIsMaxed() {
        this.petCare.getPet().getHealth().setValue(100.0);
        this.petCare.checkIfPetGetsSick();
        assertTrue(!this.petCare.getPet().getIsSick());
    }
    
    @Test
    public void petGetsSickIfHealthIsZero() {
        this.petCare.getPet().getHealth().setValue(0.0);
        this.petCare.checkIfPetGetsSick();
        assertTrue(this.petCare.getPet().getIsSick());
    }
    
    @Test
    public void checkIfPetGetsSickDoesNotChangeValueIfPetIsAlreadySick() {
        this.petCare.getPet().setIsSick(true);
        this.petCare.checkIfPetGetsSick();
        assertTrue(this.petCare.getPet().getIsSick());
    }
    
    @Test
    public void petDoesNotNeedCleaningIfHygieneIsMaxed() {
        this.petCare.getPet().getHygiene().setValue(100);
        this.petCare.checkIfPetNeedsCleaning();
        assertTrue(this.petCare.getPet().getNeedsWash() == false);
    }
    
    @Test
    public void petDoesNeedCleaningIfHygieneIsAtZero() {
        this.petCare.getPet().getHygiene().setValue(0);
        this.petCare.checkIfPetNeedsCleaning();
        assertTrue(this.petCare.getPet().getNeedsWash() == true);
    }
    
    @Test
    public void checkIfPetNeedsCleaningDoesNotChangeIfItIsAlreadyTrue() {
        this.petCare.getPet().setNeedsWash(true);
        this.petCare.checkIfPetNeedsCleaning();
        assertTrue(this.petCare.getPet().getNeedsWash() == true);
    }
    
    @Test
    public void updatePetStatusUpdatesPetStatsRight() {
        this.petCare.updatePetStatus(10000);
        
        assertTrue(this.petCare.getPet().getEnergy().getValue() == 36.5);
        assertTrue(this.petCare.getPet().getHappiness().getValue() == 41.0);
        assertTrue(this.petCare.getPet().getHygiene().getValue() == 45.5);
        assertTrue(this.petCare.getPet().getHealth().getValue() == 32.0);
    }
    
    @Test
    public void updatePetStatusAddsDeadPetToCemetery() {
        this.petCare.updatePetStatus(56000);
        
        assertTrue(this.petCare.getPetCemetery().getAll().contains("1;Zorblax;0"));
    }
    
    @Test
    public void calculatePetStatusCalculatesPetStatsRight() {
        LocalDateTime date = LocalDateTime.now().minusHours(2);
        long epoch = date.toEpochSecond(ZoneOffset.UTC);
        this.petCare.getPet().setLastLogin(epoch);
        
        this.petCare.calculatePetStatus();
        
        assertTrue(this.petCare.getPet().getEnergy().getValue() == 45.14);
        assertTrue(this.petCare.getPet().getHappiness().getValue() == 46.76);
        assertTrue(this.petCare.getPet().getHygiene().getValue() == 48.38);
        assertTrue(this.petCare.getPet().getHealth().getValue() == 43.52);
    }
    
    @Test
    public void calculatePetStatusAddsDeadPetToCemetery() {
        LocalDate date = LocalDate.now().minusDays(1);
        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = date.atStartOfDay(zoneId).toEpochSecond();
        
        this.petCare.getPet().setLastLogin(epoch);
        
        this.petCare.calculatePetStatus();
        
        assertTrue(this.petCare.getPetCemetery().getAll().contains("1;Zorblax;0"));
    }
}
