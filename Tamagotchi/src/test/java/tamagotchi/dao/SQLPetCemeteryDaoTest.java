
package tamagotchi.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Unit and integration tests for SQLPetCemetery-class.
 * @author Heli
 */
public class SQLPetCemeteryDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File testFile;
    SQLPetCemeteryDao dao;
    
    public SQLPetCemeteryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() throws SQLException, IOException {
        this.testFile = testFolder.newFile("test.db");
        this.dao = new SQLPetCemeteryDao("jdbc:sqlite:" + testFile.getAbsolutePath());
        
    }

    @Test
    public void createSQLCreatesTheTablePets() throws SQLException {
        assertTrue(this.dao.createSQL());
    }
    
    @Test
    public void addPetAddsInformationToTheTable() throws SQLException, FileNotFoundException {
        this.dao.createSQL();
        this.dao.addPet(new Pet());
        ArrayList<String> pets = this.dao.getAll();
        assertTrue(pets.contains("1;Zorblax;0"));
    }
    
    @Test
    public void addPetCreatesATableIfTableIsNotFound() throws SQLException {
        this.dao.addPet(new Pet());
        ArrayList<String> pets = this.dao.getAll();
        assertTrue(pets.contains("1;Zorblax;0"));
    }
    
    @Test
    public void getAllReturnsAnEmptyArrayListIfThereIsNoTable() throws SQLException {
        assertTrue(this.dao.getAll().isEmpty());
    }
    
    @Test
    public void getAllReturnsAnEmptyArrayListIfTableContainsNothing() throws SQLException {
        this.dao.createSQL();
        assertTrue(this.dao.getAll().isEmpty());
    }
    
    @Test
    public void getAllReturnsAllThePetsInTheTable() throws SQLException {
        Pet one = new Pet();
        one.setAge(5);
        Pet two = new Pet();
        two.setName("Fluffy");
        two.setAge(2);
        Pet three = new Pet();
        three.setName("Gogo");
        three.setAge(3);
        
        this.dao.createSQL();
        this.dao.addPet(one);
        this.dao.addPet(two);
        this.dao.addPet(three);
        
        ArrayList<String> list = this.dao.getAll();
        
        assertTrue(list.contains("1;Zorblax;5"));
        assertTrue(list.contains("2;Fluffy;2"));
        assertTrue(list.contains("3;Gogo;3"));
    }
    
    @After
    public void tearDown() {
        this.testFile.delete();
    }
}
