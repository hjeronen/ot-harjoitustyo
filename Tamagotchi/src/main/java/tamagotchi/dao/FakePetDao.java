/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import tamagotchi.domain.Pet;

/**
 *
 * @author Heli
 */
public class FakePetDao implements PetDao {
    private Pet pet;
    
    
    public FakePetDao() {
        this.pet = new Pet("Zorblax");
        
    }

    @Override
    public void createSave(Pet pet) {
        this.pet = pet;
    }

    @Override
    public Pet getPet() {
        return this.pet;
    }
    

    @Override
    public boolean saveExists() {
        return false;
    }
}
