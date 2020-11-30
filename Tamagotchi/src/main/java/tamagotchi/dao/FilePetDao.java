/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import tamagotchi.domain.Pet;

/**
 *
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

    @Override
    public void createSave(Pet pet) {
        this.pet = pet;
        try {
            save();
        } catch (Exception ex) {
            Logger.getLogger(FilePetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Pet getPet() {
        try {
            load();
            return this.pet;
        } catch (Exception ex) {
            Logger.getLogger(FilePetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.pet;
    }
    
    public void load() throws Exception {
        try {
            Scanner reader = new Scanner(new File(this.saveFile));
            String[] parts = reader.nextLine().split(";");
            this.pet.setName(parts[0]);
            this.pet.setBirthday(parts[1]);
            this.pet.setLastLogin(Long.parseLong(parts[2]));
            this.pet.setEnergy(Double.parseDouble(parts[3]));
            this.pet.setHappiness(Double.parseDouble(parts[4]));
            this.pet.setHealth(Double.parseDouble(parts[5]));
            this.pet.setHygiene(Double.parseDouble(parts[6]));
            this.saveExists = true;
        } catch(Exception NoSuchElementException){
            this.saveExists = false;
        }
    }
    
    @Override
    public boolean saveExists() {
        return this.saveExists;
    }
    
    public void save() throws Exception {
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
                    + this.pet.getHygiene());
            writer.close();
            this.saveExists = true;
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
