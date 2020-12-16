
package tamagotchi.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import tamagotchi.dao.PetDao;
import tamagotchi.domain.Pet;

/**
 * Used as a fake game save for testing the PetCare-class.
 * @author Heli
 */
public class FakePetDao implements PetDao {
    private Pet pet;
    
    
    public FakePetDao() {
        this.pet = new Pet();
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
    public boolean saveExists() {
        return false;
    }
}
