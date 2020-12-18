
package tamagotchi.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tamagotchi.domain.Pet;

/**
 * Dead Pets are saved in a SQL-table.
 * @author Heli
 */
public class SQLPetCemeteryDao implements PetCemeteryDao {
    private Connection db;
    private Statement sm;
    private String address;
    private boolean saveExists;
    
    public SQLPetCemeteryDao(String connectionAddress) throws SQLException, IOException {
        this.address = connectionAddress;
        this.db = DriverManager.getConnection(this.address);
        this.sm = this.db.createStatement();
        this.saveExists = false;
    }
    
    @Override
    public boolean getSaveExists() {
        return this.saveExists;
    }
    
    /**
    * Creates a table 'Pets'.
    * Returns true if table was created, false if an error occurred.
    * 
    * @return boolean true/false
    */
    @Override
    public boolean createSave() {
        try {
            this.sm.execute("CREATE TABLE Pets (id INTEGER PRIMARY KEY, name TEXT, age TEXT);");
            this.saveExists = true;
            return true;
        } catch (SQLException SQLException) {
            return false;
        }
        
        
    }
    
    /**
     * Adds a Pet to the 'Pets'-table.
     * Pet is given as a parameter. If the Pet was added successfully, returns 
     * true. If table is not found, returns false.
     * 
     * @param pet   the Pet that is being added to the table
     * @return boolean  true/false if pet was added
     */
    @Override
    public boolean addPet(Pet pet) {
        try {
            this.sm.execute("INSERT INTO Pets (name, age) VALUES (\'" + pet.getName() + "\', " + pet.getAge() + " );");
            return true;
        } catch (SQLException SQLException) {
            return false;
        }
        
    }
    
    /**
     * Gets the Pets from the table 'Pets'.
     * Pet's information is added to an ArrayList. Returns the list.
     * If the table does not exist or is not found, returns an empty list.
     * @return ArrayList    ArrayList of the Pets in the table
     */
    @Override
    public ArrayList getAll() {
        ArrayList<String> pets = new ArrayList<>();
        try {
            ResultSet r = sm.executeQuery("SELECT * FROM Pets");
            
            while (r.next()) {
                String petInfo = "";
                petInfo = petInfo + r.getInt("id") + ";" + r.getString("name") + ";" + r.getString("age");
                pets.add(petInfo);
            }
            
        } catch (SQLException SQLException) {
            this.saveExists = false;
        }
        
        return pets;
    }
}
