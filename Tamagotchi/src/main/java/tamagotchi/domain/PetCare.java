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
        this.decayRate = 1/864;
    }
    
    public void setUpPetDao(PetDao petDao) {
        this.petDao = petDao;
        this.pet = this.petDao.getPet();
    }
    
    public PetDao getPetDao() {
        return this.petDao;
    }
    
    
    public Pet getPet() {
        return this.pet;
    }
    
    
    public void feedPet() {
        this.pet.getEnergy().increase(10);
    }
    
    public void healPet() {
        this.pet.getHealth().increase(10);
        if (this.pet.getHealth().getValue() == 100) {
            this.pet.setIsSick(false);
        }
    }
    
    public void cleanPet() {
        this.pet.setHygiene(100);
    }
    
    
    public boolean petIsAlive() {
        if (this.pet.getEnergy().getValue() == 0) {
            return false;
        }
        return true;
    }
    
    public void updateEnergy() {
        this.pet.getEnergy().decrease(20);
    }
    
    public void updateHealth() {
        this.pet.getHealth().decrease();
    }
    
    public void updateHygiene() {
        this.pet.getHygiene().decrease();
    }
    
    // Between last login and new login, energy drops approx. by 1 per 864 seconds (because pet should be dead in 24h without care)
    public void calculatePetStats(Date timeNow) {
        long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(timeNow.getTime()) - this.pet.getLastLogin();
        int seconds = (int) differenceInSeconds;
        this.pet.getEnergy().decrease((int) (seconds * this.decayRate));
        // When energy drops too low, health starts to drop
        if (this.pet.getEnergy().getValue() == 0) {
            this.pet.getHealth().decrease((int) (seconds * (this.decayRate / 4)));
            this.pet.getHappiness().decrease((int) (seconds * (this.decayRate / 4)));
        }
        // Hygiene drops half the rate of energy
        this.pet.getHygiene().decrease((int) (seconds * (this.decayRate / 2)));
        // When hygiene drops below half, health starts to drop
        if (this.pet.getHygiene().getValue() < 50) {
            this.pet.getHealth().decrease((int) (seconds * (this.decayRate / 4)));
            this.pet.getHappiness().decrease((int) (seconds * (this.decayRate / 4)));
        }
    }
    
    public void setLastLogin(Date newLogin) {
//        this.lastLogin = TimeUnit.MILLISECONDS.toSeconds(newLogin.getTime());
    }
    
    public void checkIfPetGetsSick() {
        if (!this.pet.getIsSick()) {
            if (this.pet.getHealth().getValue() < 50) {
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
            if (this.pet.getHygiene().getValue() < 50) {
                Random generator = new Random();
                int randomInt = generator.nextInt(100);
                if (randomInt >= this.pet.getHealth().getValue()) {
                    this.pet.setNeedsWash(true);
                }
            }
        }
    }

    

}
