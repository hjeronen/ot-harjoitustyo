/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.logic;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import tamagotchi.domain.Pet;

/** StatManager calculates the decay of pet's stats
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
    
    public void setDecayRate() {
        this.decayRate = this.decayRate / this.pet.getDevelopmentStage();
    }
    
    //Energy drops by 1/108 per second
    public void updateEnergy(double modifier) {
        this.pet.getEnergy().decrease(modifier * this.decayRate);
    }
    
    // Happiness drops faster than energy
    public void updateHappiness(double modifier) {
        this.pet.getHappiness().decrease(modifier * (this.decayRate * 2));
    }
    
    // Health only drops under certain conditions
    public void updateHealth(double modifier) {
        if (this.pet.getIsSick()) {
            this.pet.getHealth().decrease(modifier * (this.decayRate * 4));
        }
        // When energy drops too low, health starts to drop fast
        if (this.pet.getEnergy().getValue() == 0.0) {
            this.pet.getHealth().decrease(modifier * (this.decayRate * 2));
        }
        // When hygiene drops below half, health starts to drop fast
        if (this.pet.getHygiene().getValue() <= 50.0) {
            this.pet.getHealth().decrease(modifier * (this.decayRate * 2));
        }
    }
    
    // Hygiene drops half the rate of energy, unless pet needs cleaning
    public void updateHygiene(double modifier) {
        if (this.pet.getNeedsWash()) {
            this.pet.getHygiene().decrease(modifier * (this.decayRate * 4));
        } else {
            this.pet.getHygiene().decrease(modifier * (this.decayRate / 2));
        }
    }
    
    //Updates stats during game
    public void updateStats(double time) {
        updateEnergy(time);
        updateHappiness(time);
        updateHygiene(time);
        updateHealth(time);
    }
    
    // Updates stats between logins
    public void calculatePetStats() {
        Date today = new Date();
        long timeNow = TimeUnit.MILLISECONDS.toSeconds(today.getTime());
        long differenceInSeconds = timeNow - pet.getLastLogin();
        double seconds = (double) differenceInSeconds;
        this.pet.setDevelopmentStage();
        setDecayRate();
        updateStats(seconds);
    }
    
}
