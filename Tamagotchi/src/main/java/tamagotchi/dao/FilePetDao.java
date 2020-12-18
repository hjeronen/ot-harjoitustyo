
package tamagotchi.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import tamagotchi.domain.Pet;

/**
 * Responsible for creating a game save.
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
     * Saves the Pet's current status.
     * 
     * @param pet   Pet who's information will be saved.
     */
    @Override
    public void createSave(Pet pet) {
        this.pet = pet;
        try {
            save();
        } catch (Exception ex) {
            this.saveExists = false;
        }
    }
    
    /**
     * Returns the saved pet.
     * If unable to load a saved pet, returns a new pet.
     * @return this.pet the default pet created in the constructor
     */
    @Override
    public Pet getPet() {
        try {
            load();
            return this.pet;
        } catch (Exception ex) {
            return this.pet;
        }
        
    }
    
    /**
     * Loads a saved game.
     * Reads saved information from save file and gives it to the created new pet.
     * If a save file is not found (no previous saved game), 
     * changes the value of this.saveExists to false.
     */
    @Override
    public void load() {
        try {
            Scanner reader = new Scanner(new File(this.saveFile));
            String[] parts = reader.nextLine().split(";");
            this.pet.setName(parts[0]);
            this.pet.setBirthday(parts[1]);
            this.pet.setLastLogin(Long.parseLong(parts[2]));
            this.pet.getEnergy().setValue(Double.parseDouble(parts[3]));
            this.pet.getHappiness().setValue(Double.parseDouble(parts[4]));
            this.pet.getHealth().setValue(Double.parseDouble(parts[5]));
            this.pet.getHygiene().setValue(Double.parseDouble(parts[6]));
            if (parts[7].equals("true")) {
                this.pet.setNeedsWash(true);
            } else {
                this.pet.setNeedsWash(false);
            }
            if (parts[8].equals("true")) {
                this.pet.setIsSick(true);
            } else {
                this.pet.setIsSick(false);
            }
            this.saveExists = true;
        } catch (Exception noSuchElementException) {
            this.saveExists = false;
        }
    }
    
    /**
     * Returns the value of this.saveExists.
     * Informs if there is a game save or not.
     * @return this.saveExists true/false
     */
    @Override
    public boolean getSaveExists() {
        return this.saveExists;
    }
    
     /**
      * Saves this.pet.
      * Attempts to create a save file and writes in it the pet's name, birthday, 
      * time of creating the save as last login, energy-value, happiness-value, 
      * health-value, hygiene-value, and boolean values of needsWash and isSick. 
      * Also changes the value of this.saveExists to true, informing that there 
      * is a save for current game. If the save file could not be created, 
      * changes the value of this.saveExists to false.
      */
    @Override
    public void save() {
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
                    + this.pet.getHygiene() + ";"
                    + this.pet.getNeedsWash() + ";"
                    + this.pet.getIsSick());
            writer.close();
            this.saveExists = true;
        } catch (Exception e) {
            this.saveExists = false;
        }
    }
    
}
