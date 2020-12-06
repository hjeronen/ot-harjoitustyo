package tamagotchi.domain;

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
    public void healPetIncreasesHealthRight() {
        double originalValue = this.petCare.getPet().getHealth().getValue();
        this.petCare.healPet();
        double newValue = this.petCare.getPet().getHealth().getValue();
        assertTrue(newValue - originalValue == 10.0);
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
    public void updateEnergyDecreasesEnergyRight() {
        this.petCare.updateEnergy(10000);
        assertTrue(this.petCare.getPet().getEnergy().getValue() == 41.0);
    }
    
    @Test
    public void updateHealthDoesNotDecreaseHealthIfPetIsNotSick() {
        this.petCare.getPet().setHygiene(100.0);
        this.petCare.updateHealth(10000);
        assertTrue(this.petCare.getPet().getHealth().getValue() == 50.0);
    }
    
    @Test
    public void updateHealthDoesDecreaseHealthFastIfPetIsSick() {
        this.petCare.getPet().setIsSick(true);
        this.petCare.getPet().setHygiene(100.0);
        this.petCare.updateHealth(10000);
        assertTrue(this.petCare.getPet().getHealth().getValue() == 14);
    }
    
    @Test
    public void updateHealthDecreasesHealthIfEnergyIsAtZero() {
        this.petCare.getPet().setEnergy(0);
        this.petCare.getPet().setHygiene(100.0);
        this.petCare.updateHealth(10000);
        assertTrue(this.petCare.getPet().getHealth().getValue() == 32.0);
    }
    
    @Test
    public void updateHealthDecreasesHealthIfHygieneIsAtFifty() {
        this.petCare.updateHealth(10000);
        assertTrue(this.petCare.getPet().getHealth().getValue() == 32.0);
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
}
