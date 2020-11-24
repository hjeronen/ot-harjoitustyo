/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.domain;

/**
 *
 * @author Heli
 */

// Game logic

public class PetCare {
    private Pet pet;

    
    public PetCare() {
        this.pet = new Pet();
    }
    
    public PetCare(String petName) {
        this.pet = new Pet(petName);
    }
    
    public void renamePet(String newName) {
        this.pet.setName(newName);
    }
    
    public void feedPet() {
        this.pet.getEnergy().increase(10);
    }
    
    public void healPet() {
        this.pet.getHealth().increase(10);
    }
    
    
    public String toString() {
        return pet.toString();
    }
    
    public Pet getPet() {
        return this.pet;
    }
    
    public boolean petIsAlive() {
        if (this.pet.getEnergy().getValue() == 0) {
            return false;
        }
        return true;
    }
    
    // Will update pet status (decrease a stat) continuously once game loop is figured out...
    public void updateEnergy() {
        this.pet.getEnergy().decrease(20);
    }
    
    public void updateHealth() {
        this.pet.getHealth().decrease();
    }
    

    

}
