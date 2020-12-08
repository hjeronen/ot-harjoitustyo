package tamagotchi.logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import tamagotchi.logic.PetCare;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tamagotchi.dao.FakePetDao;

/**
 *
 * @author Heli
 */
public class PetCareTest {
    PetCare petCare;
    
    public PetCareTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.petCare = new PetCare(new FakePetDao());
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
    public void getPetDoesNotReturnNull() {
        assertTrue(this.petCare.getPet() != null);
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
    public void petIsAliveReturnsFalseWhenEnergyAndHealthAreAtZero() {
        this.petCare.getPet().setEnergy(0);
        this.petCare.getPet().setHealth(0);
        assertTrue(!this.petCare.petIsAlive());
    }
    
    @Test
    public void petIsAliveReturnsTrueWhenOnlyEnergyIsAtZero() {
        this.petCare.getPet().setEnergy(0);
        assertTrue(this.petCare.petIsAlive());
    }
    
    @Test
    public void petIsAliveReturnsTrueWhenOnlyHealthIsAtZero() {
        this.petCare.getPet().setHealth(0);
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
}
