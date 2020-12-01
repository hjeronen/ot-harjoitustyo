package tamagotchi.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.LocalDate;
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
public class PetTest {
    Pet pet;
    
    public PetTest() {
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
    public void customConstructorWorks() {
        
    }
    
    @Test
    public void defaultNameWorks() {
        assertEquals(this.pet.getName(), "Zorblax");
    }
    
    @Test
    public void getNameReturnsName() {
        assertEquals(this.pet.getName(), "Zorblax");
    }
    
    @Test
    public void setNameSetsTheCorrectName() {
        String name = "Fluffy";
        this.pet.setName(name);
        assertEquals(this.pet.getName(), name);
    }
    
    @Test
    public void setBirthdaySetsCorrectDate() {
        String date = "2020-09-20";
        this.pet.setBirthday(date);
        LocalDate testDate = LocalDate.parse(date);
        assertEquals(this.pet.getBirthday().toString(), testDate.toString());
    }
    
    @Test
    public void setEnergySetsValueRight() {
        this.pet.setEnergy(100.0);
        assertTrue(this.pet.getEnergy().getValue() == 100.0);
    }
    
    @Test
    public void setHappinessSetsValueRight() {
        this.pet.setHappiness(100.0);
        assertTrue(this.pet.getHappiness().getValue() == 100.0);
    }
    
    @Test
    public void setHealthSetsValueRight() {
        this.pet.setHealth(100.0);
        assertTrue(this.pet.getHealth().getValue() == 100.0);
    }
    
    @Test
    public void setHygieneSetsValueRight() {
        this.pet.setHygiene(100.0);
        assertTrue(this.pet.getHygiene().getValue() == 100.0);
    }
    
    @Test
    public void getIsSickReturnsRightValue() {
        assertTrue(this.pet.getIsSick() == false);
    }
    
    @Test
    public void setIsSickSetsValueRight() {
        this.pet.setIsSick(true);
        assertTrue(this.pet.getIsSick() == true);
    }
    
    @Test
    public void getNeedsWashReturnsRightValue() {
        assertTrue(this.pet.getNeedsWash() == false);
    }
    
    @Test
    public void setNeedsWashSetsValueRight() {
        this.pet.setNeedsWash(true);
        assertTrue(this.pet.getNeedsWash() == true);
    }
}
