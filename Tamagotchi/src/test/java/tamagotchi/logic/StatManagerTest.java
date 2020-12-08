/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tamagotchi.domain.Pet;

/**
 *
 * @author Heli
 */
public class StatManagerTest {
    StatManager manager;
    Pet pet;
    public StatManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.pet = new Pet();
        this.manager = new StatManager(this.pet);
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
    public void updateEnergyDecreasesEnergyRight() {
        this.manager.updateEnergy(10000);
        assertTrue(this.pet.getEnergy().getValue() == 41.0);
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
}
