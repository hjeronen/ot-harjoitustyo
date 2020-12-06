/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.logic;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import tamagotchi.dao.PetDao;
import tamagotchi.domain.Pet;

/** Primary holder and caretaker for the pet
 *
 * @author Heli
 */

// Game logic

public class PetCare {
    private Pet pet;
    private PetDao petDao;
    private StatManager statManager;
    
    public PetCare(PetDao petDao) {
        this.petDao = petDao;
        this.pet = this.petDao.getPet();
        this.statManager = new StatManager(this.pet);
    }
    
    public void setUpPetDao(PetDao petDao) {
        this.petDao = petDao;
        this.pet = this.petDao.getPet();
        this.statManager.setPet(this.pet);
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
    
    public StatManager getStatManager() {
        return this.statManager;
    }
    
    // Move feed, play, heal and clean to StatManager?
    public void feedPet() {
        this.pet.getEnergy().increase(10.0);
    }
    
    public void play(int score) {
        this.pet.getHappiness().increase(score * 10);
    }
    
    public void healPet() {
        this.pet.getHealth().increase(10.0);
        if (this.pet.getHealth().getValue() == 100.0) {
            this.pet.setIsSick(false);
        }
    }
    
    public void cleanPet() {
        this.pet.setHygiene(100.0);
        this.pet.setNeedsWash(false);
    }
    
    
    public boolean petIsAlive() {
        return (!(this.pet.getEnergy().getValue() == 0.0 && this.pet.getHealth().getValue() == 0.0));
    }

    

}
