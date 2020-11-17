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
public class Pet {
    private String name;
    private int age;
    private Stat energy;
    private Stat happiness;
    private Stat health;
    
    public Pet(String name) {
        this.name = name;
        this.age = 0;
        this.energy = new Stat(50);
        this.happiness = new Stat(50);
        this.health = new Stat(50);
    }
    
    public Pet() {
        this("Zorblax");
    }

    
    public String toString() {
        return "Name: " + this.name + ", Age: " + this.age + " days, Energy: " 
                + this.energy + ", Happiness: " + this.happiness 
                + ", Health: " + this.health;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public void setAge(int days) {
        this.age = days;
    }
    
    public Stat getEnergy() {
        return this.energy;
    }
    
    public void setEnergy(int value) {
        this.energy.setValue(value);
    }
    
    
    public Stat getHappiness() {
        return this.happiness;
    }
    
    public void setHappiness(int value) {
        this.happiness.setValue(value);
    }
    
    public Stat getHealth() {
        return this.health;
    }
    
    public void setHealth(int value) {
        this.health.setValue(value);
    }
}
