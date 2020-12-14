
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
    private boolean saveExists;
    
    public SQLPetCemeteryDao() throws SQLException, IOException {
        this.db = DriverManager.getConnection("jdbc:sqlite:petCemetery.db");
        this.sm = this.db.createStatement();
        this.saveExists = false;
    }
    
    public boolean getSaveExists() {
        return this.saveExists;
    }
    
    /**
    * Creates a SQL-table 'Pets'.
    * Returns true if table was created, false if an error occurred.
    * 
    * @return boolean true/false
    * @throws SQLException
    */
    public boolean createSQL() throws SQLException {
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
     * Pet is given as a parameter. If table is not found, creates a new table 
     * 'Pets' and adds the Pet there.
     * 
     * @param pet   the Pet that is being added to the table
     * @throws SQLException 
     */
    public void addPet(Pet pet) throws SQLException {
        try {
            this.sm.execute("INSERT INTO Pets (name, age) VALUES (\'" + pet.getName() + "\', " + pet.getAge() + " );");
        } catch (SQLException SQLException) {
            createSQL();
            this.sm.execute("INSERT INTO Pets (name, age) VALUES (\'" + pet.getName() + "\', " + pet.getAge() + " );");
        }
        
    }
    
    /**
     * Gets the Pets from the table 'Pets'.
     * Pet's information is added to an ArrayList. Returns the list.
     * If the table does not exist or is not found, returns an empty list.
     * @return ArrayList    ArrayList of the Pets in the table
     * @throws SQLException 
     */
    public ArrayList getAll() throws SQLException {
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
