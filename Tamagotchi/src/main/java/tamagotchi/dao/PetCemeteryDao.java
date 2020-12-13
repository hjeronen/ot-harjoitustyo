
package tamagotchi.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import tamagotchi.domain.Pet;

/**
 * Interface for saving dead Pets.
 * @author Heli
 */
public interface PetCemeteryDao {
    boolean createSQL() throws SQLException;
    void addPet(Pet pet) throws SQLException;
    ArrayList getAll() throws SQLException;
}
