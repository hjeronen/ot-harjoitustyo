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
    public void setBirthdaySetsCorrectDate() {
        String date = "2020-09-20";
        this.pet.setBirthday(date);
        LocalDate testDate = LocalDate.parse(date);
        assertEquals(this.pet.getBirthday().toString(), testDate.toString());
    }
}
