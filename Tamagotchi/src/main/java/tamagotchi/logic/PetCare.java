
package tamagotchi.logic;

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
        this.petCemetery.createSave();
    }
    
    /**
     * Prepare PetDao.
     * Sets PetDao for PetCare and gets the Pet from a saved file
     * giving it to the PetCare and to it's StatManager.
     * 
     * @param petDao    Saved Pet
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
     * Creates a new Pet and saves it.
     * 
     * @throws Exception 
     * 
     * @see tamagotchi.dao.FilePetDao#createSave(Pet pet)
     */
    public void createNewPetSave() throws Exception {
        this.pet = new Pet();
        this.petDao.createSave(this.pet);
    }
    
    /**
     * Saves the current Pet.
     * Calculates Pet's age before saving.
     */
    public void saveGame() {
        this.pet.setAge(this.pet.calculateAge());
        this.petDao.createSave(this.pet);
    }
    
    public Pet getPet() {
        return this.pet;
    }
    
    public StatManager getStatManager() {
        return this.statManager;
    }
    
    /**
     * Increases Pet's energy by 10.0.
     */
    public void feedPet() {
        this.pet.getEnergy().increase(10.0);
    }
    
    /**
     * Increases Pet's happiness.
     * Happiness is increased by 10.0 times the score
     * gained from the MiniGame.
     * 
     * @param score points gained in the minigame.
     */
    public void play(int score) {
        this.pet.getHappiness().increase(score * 10);
    }
    
    /**
     * Increases Pet's health by 10.0.
     * If pet is healed to max, it cannot be sick.
     */
    public void healPet() {
        this.pet.getHealth().increase(10.0);
        if (this.pet.getHealth().getValue() == 100.0) {
            this.pet.setIsSick(false);
        }
    }
    
    /**
     * Increases Pet's hygiene to max.
     * Also sets Pet's needsWash to false.
     */
    public void cleanPet() {
        this.pet.getHygiene().setValue(100.0);
        this.pet.setNeedsWash(false);
    }
    
    /**
     * Checks if Pet is alive.
     * Pet is dead if both energy and health are at 0.
     * Dead Pet is added to the PetCemetery.
     * 
     * @return  true if pet is alive, false if it is not
     */
    public boolean petIsAlive() {
        return !(this.pet.getEnergy().getValue() == 0.0 && this.pet.getHealth().getValue() == 0.0);
    }
    
    /**
     * Checks if Pet gets sick.
     * If it is not already sick, when Pet's health is under 50,
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
      * If Pet needs a wash, waste appears in the game view.
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
    
    /**
     * Updates Pet's stats during game.
     * Calls StatManager to update Pet's stats. Also checks if Pet is alive - 
     * if it's not, Pet is added to the PetCemetery.
     * @param time  time that has passed since last update
     */
    public void updatePetStatus(double time) {
        this.pet.setAge(this.pet.calculateAge());
        this.statManager.updateStats(time);
        if (!petIsAlive()) {
            this.petCemetery.addPet(this.pet);
        }
    }
    
    /**
     * Calculates Pet's stats between logins.
     * Pet's age is taken up before StatManager recalculates it, so that 
     * Pet does not age if it has died between logins, but instead old age is 
     * set for the pet before adding it to the PetCemetery.
     */
    public void calculatePetStatus() {
        int petAge = this.pet.getAge();
        this.statManager.calculatePetStats();
        if (!petIsAlive()) {
            this.pet.setAge(petAge);
            this.petCemetery.addPet(this.pet);
        }
    }

}
