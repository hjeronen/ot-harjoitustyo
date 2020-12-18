
package tamagotchi.dao;

import tamagotchi.domain.Pet;

/**
 * Interface for saving methods.
 * @author Heli
 */
public interface PetDao {
    void createSave(Pet pet);
    Pet getPet();
    boolean getSaveExists();
    void save();
    void load();


    
}
