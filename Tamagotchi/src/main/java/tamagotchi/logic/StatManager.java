
package tamagotchi.logic;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import tamagotchi.domain.Pet;
/**
 * StatManager calculates the decay of pet's stats
 */
/** 
 *
 * @author Heli
 */
public class StatManager {
    private Pet pet;
    private double decayRate;
    
    
    public StatManager(Pet pet) {
        this.pet = pet;
        this.decayRate = 0.0009; // Base rate: 1/108
    }
    
    public void setPet(Pet newPet) {
        this.pet = newPet;
    }
    
    public Pet getPet() {
        return this.pet;
    }
    
    /**
     * Sets the decay rate based on the development stage of the pet.
     * At base rate, the value of a stat drops 1/108 per second.
     * Base rate is affected by pet's development stage (1, 2 or 3).
     * As pet gets older, the decay rate becomes smaller.
     */
    public void setDecayRate() {
        this.decayRate = this.decayRate / this.pet.getDevelopmentStage();
    }
    
    /**
     * Updates energy-stat.
     * Energy drops at base rate times the time in seconds that has elapsed since last update.
     * @param modifier  the time elapsed since last update
     */
    public void updateEnergy(double modifier) {
        this.pet.getEnergy().decrease(modifier * this.decayRate);
    }
    
    /**
     * Updates happiness-stat.
     * Happiness drops twice the base rate times the time in seconds that has elapsed since last update.
     * @param modifier  the time elapsed since last update
     */
    public void updateHappiness(double modifier) {
        this.pet.getHappiness().decrease(modifier * (this.decayRate * 2));
    }
    
    /**
     * Updates health-stat.
     * Health only drops fast when pet is sick, when energy is at 0, and/or when
     * hygiene drops below 50. 
     * 
     * @param modifier  the time elapsed since last update
     */
    public void updateHealth(double modifier) {
        if (this.pet.getIsSick()) {
            this.pet.getHealth().decrease(modifier * (this.decayRate * 4));
        }
        if (this.pet.getEnergy().getValue() == 0.0) {
            this.pet.getHealth().decrease(modifier * (this.decayRate * 2));
        }
        if (this.pet.getHygiene().getValue() <= 50.0) {
            this.pet.getHealth().decrease(modifier * (this.decayRate * 2));
        }
    }
    
    /**
     * Updates hygiene-stat.
     * Hygiene drops slowly, unless pet needs a wash.
     * 
     * @param modifier  the time elapsed since last update
     */
    public void updateHygiene(double modifier) {
        if (this.pet.getNeedsWash()) {
            this.pet.getHygiene().decrease(modifier * (this.decayRate * 4));
        } else {
            this.pet.getHygiene().decrease(modifier * (this.decayRate / 2));
        }
    }
    
    /**
     * Updates all stats.
     * This method is called when the gameloop is running.
     * 
     * @param time  the time elapsed since last update
     */
    public void updateStats(double time) {
        updateEnergy(time);
        updateHappiness(time);
        updateHygiene(time);
        updateHealth(time);
    }
    
    /**
     * Calculates the decay of stats between logins.
     * This method is called when game is started. First it sets development stage
     * for the pet, sets the right decay rate, and then updates all stats.
     */
    public void calculatePetStats() {
        Date today = new Date();
        long timeNow = TimeUnit.MILLISECONDS.toSeconds(today.getTime());
        long differenceInSeconds = timeNow - this.pet.getLastLogin();
        double seconds = (double) differenceInSeconds;
        this.pet.setDevelopmentStage();
        setDecayRate();
        updateStats(seconds);
    }
    
}
