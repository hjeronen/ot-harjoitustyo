
package tamagotchi.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import tamagotchi.domain.Pet;

/**
 * Responsible for creating a gamesave.
 * @author Heli
 */
public class FilePetDao implements PetDao {
    
    private String saveFile;
    private Pet pet;
    private boolean saveExists;
    
    public FilePetDao(String file) throws Exception {
        this.pet = new Pet();
        this.saveFile = file;
        this.saveExists = false;
        load();
        
    }
    
    /**
     * Saves the current pet status.
     * 
     * @param pet   Pet who's information will be saved.
     */
    @Override
    public void createSave(Pet pet) {
        this.pet = pet;
        try {
            save();
        } catch (Exception ex) {
            Logger.getLogger(FilePetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns the saved pet.
     * If unable to load a saved pet, returns a new pet.
     * @return this.pet The default pet created in the constructor.
     */
    @Override
    public Pet getPet() {
        try {
            load();
            return this.pet;
        } catch (Exception ex) {
            Logger.getLogger(FilePetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.pet;
    }
    
    /**
     * Loads a saved game.
     * Reads saved information from save file and gives it to the created new pet.
     * @throws Exception    If a save file is not found (no previous saved game), 
     * changes the value of this.saveExists to false.
     */
    public void load() throws Exception {
        try {
            Scanner reader = new Scanner(new File(this.saveFile));
            String[] parts = reader.nextLine().split(";");
            this.pet.setName(parts[0]);
            this.pet.setBirthday(parts[1]);
            this.pet.setLastLogin(Long.parseLong(parts[2]));
            this.pet.setEnergy(Double.parseDouble(parts[3]));
            this.pet.setHappiness(Double.parseDouble(parts[4]));
            this.pet.setHealth(Double.parseDouble(parts[5]));
            this.pet.setHygiene(Double.parseDouble(parts[6]));
            this.saveExists = true;
        } catch (Exception noSuchElementException) {
            this.saveExists = false;
        }
    }
    /**
     * Returns the value of this.saveExists.
     * Informs if there is a gamesave or not.
     * @return this.saveExists true/false
     */
    @Override
    public boolean saveExists() {
        return this.saveExists;
    }
    
     /**
      * Saves this.pet.
      * Attempts to create a save file and writes in it the pet's name, birthday, 
      * time of creating the save as last login, energy-value, happiness-value, 
      * health-value and hygiene-value. Also changes the value of 
      * this.saveExists to true, informing that there is a save for current game.
      * @throws Exception 
      */
    public void save() throws Exception {
        try {
            FileWriter writer = new FileWriter(new File(this.saveFile));
            Date time = new Date();
            this.pet.setLastLogin(TimeUnit.MILLISECONDS.toSeconds(time.getTime()));
            writer.write(this.pet.getName() + ";" 
                    + this.pet.getBirthday().toString() + ";" 
                    + this.pet.getLastLogin() + ";" 
                    + this.pet.getEnergy() + ";" 
                    + this.pet.getHappiness() + ";" 
                    + this.pet.getHealth() + ";" 
                    + this.pet.getHygiene());
            writer.close();
            this.saveExists = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
