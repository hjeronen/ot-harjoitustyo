/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.dao;

import tamagotchi.domain.Pet;

/**
 *
 * @author Heli
 */
public interface PetDao {
    void createSave(Pet pet);
    Pet getPet();
    boolean saveExists();


    
}
