
package tamagotchi.dao;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import tamagotchi.domain.Pet;

/**
 * Tests for the class FilePetDao.
 * @author Heli
 */

public class FilePetDaoTest {
    
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File testFile;
    FilePetDao dao;
    
    
    @Before
    public void setUp() throws Exception {
        this.testFile = testFolder.newFile("test_save.txt");
        this.dao = new FilePetDao(this.testFile.getAbsolutePath());
    }
    
    
    @Test
    public void createSaveCreatesNewSave() {
        Pet fluffy = new Pet();
        fluffy.setName("Fluffy");
        
        this.dao.createSave(fluffy);
        
        assertEquals(this.dao.getPet().getName(), "Fluffy");
    }
    
    @Test
    public void getPetLoadsAndReturnsSavedPet() {
        this.dao.getPet().setName("Fluffy");
        this.dao.save();
        
        Pet defaultPet = new Pet();
        
        Pet save = this.dao.getPet();
        
        assertNotEquals(defaultPet.getName(), save.getName());
    }
    
    @Test
    public void getPetReturnsDefaultPetIfThereIsNoSave() {
        Pet defaultPet = new Pet();
        Pet defaultSavePet = this.dao.getPet();
        assertEquals(defaultPet.getName(), defaultSavePet.getName());
    }
    
    @Test
    public void newInformationIsSaved() {
        Pet save = this.dao.getPet();
        save.setName("Gogo");
        save.setBirthday("2020-11-30");
        
        Date time = new Date();
        long lastLogin = TimeUnit.MILLISECONDS.toSeconds(time.getTime());
        
        save.setLastLogin(lastLogin);
        save.getEnergy().setValue(100);
        save.getHappiness().setValue(100);
        save.getHealth().setValue(100);
        save.getHygiene().setValue(100);
        save.setNeedsWash(true);
        save.setIsSick(true);
        
        this.dao.save();
        
        assertEquals(this.dao.getPet().getName(), "Gogo");
        assertEquals("" + this.dao.getPet().getBirthday(), "2020-11-30");
        assertTrue(this.dao.getPet().getLastLogin() == lastLogin);
        assertTrue(this.dao.getPet().getEnergy().getValue() == 100.0);
        assertTrue(this.dao.getPet().getHappiness().getValue() == 100.0);
        assertTrue(this.dao.getPet().getHealth().getValue() == 100.0);
        assertTrue(this.dao.getPet().getHygiene().getValue() == 100.0);
        assertTrue(this.dao.getPet().getNeedsWash());
        assertTrue(this.dao.getPet().getIsSick());
    }
    
    
    @After
    public void tearDown() {
        this.testFile.delete();
    }
}
