/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.dao;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import tamagotchi.domain.Pet;

/**
 *
 * @author Heli
 */

public class FilePetDaoTest {
    
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    FilePetDao dao;
       
    public FilePetDaoTest() throws Exception {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        File file = testFolder.newFile("test_save.txt");
        this.dao = new FilePetDao(file.getName());
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
    public void createSaveCreatesNewSave() {
        Pet test = this.dao.getPet();
        
        Pet fluffy = new Pet();
        fluffy.setName("Fluffy");
        
        this.dao.createSave(fluffy);
        
        assertNotEquals(test.getName(), this.dao.getPet().getName());
    }
    
    @Test
    public void loadReturnsCorrectPet() throws Exception {
        Pet test = new Pet(); // Zorblax, default pet
        
        Pet save = this.dao.getPet(); // Fluffy
        
        assertNotEquals(test.getName(), save.getName());
    }
    
    @Test
    public void newInformationIsSaved() {
        Pet save = this.dao.getPet();
        save.setName("Gogo");
        save.setBirthday("2020-11-30");
        
        Date time = new Date();
        long lastLogin = TimeUnit.MILLISECONDS.toSeconds(time.getTime());
        
        save.setLastLogin(lastLogin);
        save.setEnergy(100);
        save.setHappiness(100);
        save.setHealth(100);
        save.setHygiene(100);
        
        this.dao.createSave(save);
        
        assertEquals(this.dao.getPet().getName(), "Gogo");
        assertEquals("" + this.dao.getPet().getBirthday(), "2020-11-30");
        assertTrue(this.dao.getPet().getLastLogin() == lastLogin);
        assertTrue(this.dao.getPet().getEnergy().getValue() == 100.0);
        assertTrue(this.dao.getPet().getHappiness().getValue() == 100.0);
        assertTrue(this.dao.getPet().getHealth().getValue() == 100.0);
        assertTrue(this.dao.getPet().getHygiene().getValue() == 100.0);
    }
}
