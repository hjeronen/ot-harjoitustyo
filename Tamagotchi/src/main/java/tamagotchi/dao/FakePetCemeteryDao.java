/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import tamagotchi.domain.Pet;

/**
 *
 * @author Heli
 */
public class FakePetCemeteryDao implements PetCemeteryDao {
    private ArrayList<String> pets;
    
    public FakePetCemeteryDao() {
        this.pets = new ArrayList<>();
    }

    @Override
    public boolean createSQL() throws SQLException {
        this.pets = new ArrayList<>();
        return true;
    }

    @Override
    public void addPet(Pet pet) throws SQLException {
        String petInfo = pet.getName() + ";" + pet.getAge();
        this.pets.add(petInfo);
    }

    @Override
    public ArrayList getAll() throws SQLException {
        return this.pets;
    }
}
