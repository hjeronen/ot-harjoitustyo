
package tamagotchi.dao;

import java.util.ArrayList;
import tamagotchi.domain.Pet;

/**
 * Fake version of PetCemeteryDao.
 * This class is used for testing PetCare-class.
 * @author Heli
 */
public class FakePetCemeteryDao implements PetCemeteryDao {
    private ArrayList<String> pets;
    private boolean saveExists;
    
    public FakePetCemeteryDao() {
        this.pets = new ArrayList<>();
        this.saveExists = false;
    }
    
    @Override
    public boolean getSaveExists() {
        return this.saveExists;
    }

    @Override
    public boolean createSave() {
        this.pets = new ArrayList<>();
        this.saveExists = true;
        return true;
    }

    @Override
    public boolean addPet(Pet pet) {
        int id = this.pets.size();
        if (id == 0) {
            id++;
        }
        String petInfo = id + ";" + pet.getName() + ";" + pet.getAge();
        this.pets.add(petInfo);
        return true;
    }

    @Override
    public ArrayList getAll() {
        return this.pets;
    }
}
