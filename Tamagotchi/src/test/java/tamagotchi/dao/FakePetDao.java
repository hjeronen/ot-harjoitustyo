
package tamagotchi.dao;

import tamagotchi.dao.PetDao;
import tamagotchi.domain.Pet;

/**
 * Used as a fake game save for testing the PetCare-class.
 * @author Heli
 */
public class FakePetDao implements PetDao {
    private Pet pet;
    private boolean saveExists;
    
    
    public FakePetDao() {
        this.pet = new Pet();
        this.saveExists = false;
    }

    @Override
    public void createSave(Pet pet) {
        this.pet = pet;
    }

    @Override
    public Pet getPet() {
        return this.pet;
    }
    

    @Override
    public boolean getSaveExists() {
        return this.saveExists;
    }

    @Override
    public void save() {
        this.saveExists = true;
    }

    @Override
    public void load() {
        this.saveExists = true;
    }
}
