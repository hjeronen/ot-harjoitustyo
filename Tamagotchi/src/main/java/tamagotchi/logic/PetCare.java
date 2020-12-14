
package tamagotchi.logic;

import java.sql.SQLException;
import java.util.Random;
import tamagotchi.dao.PetCemeteryDao;
import tamagotchi.dao.PetDao;
import tamagotchi.domain.Pet;

/**
 * PetCare -class is the primary holder and caretaker for the pet.
 * Contains methods for caring for pet and creating a new save
 * (saving happens in FilePetDao-class).
 */
/** 
 *
 * @author Heli
 */

public class PetCare {
    private Pet pet;
    private PetDao petDao;
    private StatManager statManager;
    private PetCemeteryDao petCemetery;
    
    public PetCare(PetDao petDao, PetCemeteryDao petCemetery) {
        this.petDao = petDao;
        this.pet = this.petDao.getPet();
        this.statManager = new StatManager(this.pet);
        this.petCemetery = petCemetery;
    }
    
    /**
     * Sets PetDao for PetCare and gets the pet from a saved file
     * giving it to PetCare and to StatManager.
     * 
     * @param petDao    Saved pet
     */
    public void setUpPetDao(PetDao petDao) {
        this.petDao = petDao;
        this.pet = this.petDao.getPet();
        this.statManager.setPet(this.pet);
    }
    
    public PetDao getPetDao() {
        return this.petDao;
    }
    
    public PetCemeteryDao getPetCemetery() {
        return this.petCemetery;
    }
    
    /**
     * Creates a new pet and saves it.
     * 
     * @throws Exception 
     * 
     * @see dao.FilePetDao#createSave(Pet pet)
     */
    public void createNewPetSave() throws Exception {
        this.pet = new Pet();
        this.petDao.createSave(this.pet);
    }
    
    public void saveGame() {
        this.pet.calculateAge();
        this.petDao.createSave(this.pet);
    }
    
    public Pet getPet() {
        return this.pet;
    }
    
    public StatManager getStatManager() {
        return this.statManager;
    }
    
    /**
     * Feeds pet, increasing it's energy-level by 10.0.
     */
    public void feedPet() {
        this.pet.getEnergy().increase(10.0);
    }
    
    /**
     * Increases the pet's happiness-level by 10.0 times the score
     * gained from the MiniGame.
     * 
     * @param score points gained in the minigame.
     */
    public void play(int score) {
        this.pet.getHappiness().increase(score * 10);
    }
    
    /**
     * Increases pet's health by 10.0.
     * If pet is healed to max, it cannot be sick.
     */
    public void healPet() {
        this.pet.getHealth().increase(10.0);
        if (this.pet.getHealth().getValue() == 100.0) {
            this.pet.setIsSick(false);
        }
    }
    
    /**
     * Increases pet's hygiene to max.
     * When pet is clean, it does not need a wash.
     */
    public void cleanPet() {
        this.pet.setHygiene(100.0);
        this.pet.setNeedsWash(false);
    }
    
    /**
     * Checks if pet is alive.
     * Pet is dead if both energy and health are at 0.
     * Dead Pet is added to the PetCemetery.
     * 
     * @return  true if pet is alive, false if it is not
     * @throws java.sql.SQLException
     */
    public boolean petIsAlive() throws SQLException {
        boolean petIsAlive = !(this.pet.getEnergy().getValue() == 0.0 && this.pet.getHealth().getValue() == 0.0);
        if (!petIsAlive) {
            this.petCemetery.addPet(this.pet);
        }
        return petIsAlive;
    }
    
    /**
     * Checks if pet gets sick.
     * If it is not already sick, when pet's health is under 50,
     * it has a chance to get sick. The likelihood of getting sick
     * increases as health gets lower.
     */
    public void checkIfPetGetsSick() {
        if (!this.pet.getIsSick()) {
            if (this.pet.getHealth().getValue() < 50.0) {
                Random generator = new Random();
                int randomInt = generator.nextInt(100);
                if (randomInt >= this.pet.getHealth().getValue()) {
                    this.pet.setIsSick(true);
                }
            }
        }
    }
     /**
      * Checks if Pet needs cleaning.
      * If pet needs a wash, waste appears in the game view.
      * Likelihood for this is greater as hygiene gets lower.
      */
    public void checkIfPetNeedsCleaning() {
        if (!this.pet.getNeedsWash()) {
            Random generator = new Random();
            int randomInt = generator.nextInt(100);
            if (randomInt >= this.pet.getHygiene().getValue()) {
                this.pet.setNeedsWash(true);
            }
        }
    }

}
