
package tamagotchi.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import tamagotchi.domain.Pet;

/**
 * Interface for saving dead Pets.
 * @author Heli
 */
public interface PetCemeteryDao {
    boolean getSaveExists();
    boolean createSave();
    boolean addPet(Pet pet);
    ArrayList getAll();
}
