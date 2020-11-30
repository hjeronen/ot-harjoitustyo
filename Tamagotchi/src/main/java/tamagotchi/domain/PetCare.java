/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import tamagotchi.dao.FilePetDao;
import tamagotchi.dao.PetDao;

/**
 *
 * @author Heli
 */

// Game logic

public class PetCare {
    private Pet pet;
    private PetDao petDao;
    private double decayRate;
    
    public PetCare(PetDao petDao) {
        this.petDao = petDao;
        this.pet = this.petDao.getPet();
        this.decayRate = 0.0009; // 1/108 //1/432; //1/864;
    }
    
    public void setUpPetDao(PetDao petDao) {
        this.petDao = petDao;
        this.pet = this.petDao.getPet();
    }
    
    public PetDao getPetDao() {
        return this.petDao;
    }
    
    public void createNewPetSave() throws Exception {
        this.pet = new Pet();
        this.petDao.createSave(this.pet);
    }
    
    public Pet getPet() {
        return this.pet;
    }
    
    
    public void feedPet() {
        this.pet.getEnergy().increase(10.0);
    }
    
    public void healPet() {
        this.pet.getHealth().increase(10.0);
        if (this.pet.getHealth().getValue() == 100.0) {
            this.pet.setIsSick(false);
        }
    }
    
    public void cleanPet() {
        this.pet.setHygiene(100.0);
    }
    
    // Move to Pet!
    public boolean petIsAlive() {
        if (this.pet.getEnergy().getValue() == 0.0 && this.pet.getHealth().getValue() == 0.0) {
            return false;
        }
        return true;
    }
    
    // Energy should drop approx. by 1 per 864 seconds (because it should go to 0 in 24h without care)
    // UPDATE! Pet will be dead in 3h without care
    public void updateEnergy(double modifier) {
        this.pet.getEnergy().decrease(modifier * this.decayRate);
        System.out.println(modifier);
        System.out.println(this.decayRate);
        System.out.println("Energy drops by " + (modifier * this.decayRate));
    }
    
    // Happiness drops faster than energy
    public void updateHappiness(double modifier) {
        this.pet.getHappiness().decrease(modifier * (this.decayRate * 2));
    }
    
    // Health only drops under certain conditions
    public void updateHealth(double modifier) {
        if (this.pet.getIsSick()) {
            this.pet.getHealth().decrease( modifier * (this.decayRate * 4));
        }
        // When energy drops too low, health starts to drop fast
        if (this.pet.getEnergy().getValue() == 0.0 ) {
            this.pet.getHealth().decrease(modifier * (this.decayRate * 2));
        }
        // When hygiene drops below half, health starts to drop fast
        if (this.pet.getHygiene().getValue() <= 50.0) {
            this.pet.getHealth().decrease(modifier * (this.decayRate * 2));
        }
        
    }
    
    // Hygiene drops half the rate of energy
    public void updateHygiene(double modifier) {
        this.pet.getHygiene().decrease(modifier * (this.decayRate / 2));
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
        long differenceInSeconds = timeNow - this.pet.getLastLogin();
        double seconds = (double) differenceInSeconds;
        
        updateStats(seconds);      
    }
    
    
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
    
    public void checkIfPetNeedsCleaning() {
        if (!this.pet.getNeedsWash()) {
            if (this.pet.getHygiene().getValue() < 50.0) {
                Random generator = new Random();
                int randomInt = generator.nextInt(100);
                if (randomInt >= this.pet.getHealth().getValue()) {
                    this.pet.setNeedsWash(true);
                }
            }
        }
    }

    

}
