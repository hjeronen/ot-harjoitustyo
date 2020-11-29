/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.domain;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author Heli
 */
public class Pet {
    private String name;
    private Stat energy;
    private Stat happiness;
    private Stat health;
    private Stat hygiene;
    private LocalDate birthday;
    private boolean isSick;
    private boolean needsWash;
    private long lastLogin;
    
    public Pet(String name) {
        this.name = name;
        
        this.energy = new Stat(50);
        this.happiness = new Stat(50);
        this.health = new Stat(50);
        this.hygiene = new Stat(50);
        
        this.birthday = LocalDate.now();
        
        this.isSick = false;
        this.needsWash = false;
        
        Date time = new Date();
        this.lastLogin = TimeUnit.MILLISECONDS.toSeconds(time.getTime());
    }
    
    public Pet() {
        this("Zorblax");
    }
    
    
    public long getLastLogin() {
        return this.lastLogin;
    }
    
    public void setLastLogin(long time) {
        this.lastLogin = time;
    }
    
    public String toString() {
        return "Name: " + this.name + ", Age: " + this.getAge() + " days, Energy: " 
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
        LocalDate today = LocalDate.now();
        
        int days = (int) DAYS.between(this.birthday, today);
        return days;
    }
    
    //Use form yyyy-mm-dd
    public void setBirthday(String yyyymmdd) {
        LocalDate newBirthday = LocalDate.parse(yyyymmdd);
        this.birthday = newBirthday;
    }
    
    public LocalDate getBirthday() {
        return this.birthday;
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
    
    public Stat getHygiene() {
        return this.hygiene;
    }
    
    public void setHygiene(int value) {
        this.hygiene.setValue(value);
    }
    
    public boolean getIsSick() {
        return this.isSick;
    }
    
    public void setIsSick(boolean value) {
        this.isSick = value;
    }
    
    public boolean getNeedsWash() {
        return this.needsWash;
    }
    
    public void setNeedsWash(boolean value) {
        this.needsWash = value;
    }
}
