package tamagotchi.domain;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit and integration tests for Pet-class.
 * 
 * @author Heli
 */
public class PetTest {
    Pet pet;
    
    
    @Before
    public void setUp() {
        this.pet = new Pet();
    }
    
    
    @Test
    public void defaultNameWorks() {
        assertEquals(this.pet.getName(), "Zorblax");
    }
    
    @Test
    public void setNameDoesNotSetNullNameButInsteadUsesDefaultName() {
        String name = null;
        this.pet.setName(name);
        assertEquals(this.pet.getName(), "Zorblax");
    }
    
    @Test
    public void setNameDoesNotSetEmptyStringButInsteadUsesDefaultName() {
        String name = "";
        this.pet.setName(name);
        assertEquals(this.pet.getName(), "Zorblax");
    }
    
    @Test
    public void setNameDoesNotSetNameContainingForbiddenCharactersButUsesDefaultName() {
        String name = "Go;go";
        this.pet.setName(name);
        assertEquals(this.pet.getName(), "Zorblax");
    }
    
    @Test
    public void setNameSetsTheCorrectName() {
        String name = "Fluffy";
        this.pet.setName(name);
        assertEquals(this.pet.getName(), name);
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
    
    @Test
    public void toStringReturnsCorrectInformation() {
        LocalDate testDate = LocalDate.now();
        assertEquals(this.pet.toString(), "Name: Zorblax"
                        + "\nBirthday: " + testDate 
                        + "\nAge: 0 days"
                        + "\nEnergy: 50.00"
                        + "\nHappiness: 50.00"
                        + "\nHealth: 50.00"
                        + "\nHygiene: 50.00");
    }
    
    @Test
    public void calculateAgeSetsTheAgeRight() {
        this.pet.calculateAge();
        assertTrue(this.pet.getAge() == 0);
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
    
    @Test
    public void setDevelopmentStageSetsFirstStageCorrectly() {
        this.pet.setDevelopmentStage();
        assertTrue(this.pet.getDevelopmentStage() == 1);
    }
    
    @Test
    public void setDevelopmentStageSetsSecondStageCorrectly() {
        this.pet.setAge(4);
        this.pet.setDevelopmentStage();
        assertTrue(this.pet.getDevelopmentStage() == 2);
    }
    
    @Test
    public void setDevelopmentStageSetsThirdStageCorrectly() {
        this.pet.setAge(8);
        this.pet.setDevelopmentStage();
        assertTrue(this.pet.getDevelopmentStage() == 3);
    }
}
