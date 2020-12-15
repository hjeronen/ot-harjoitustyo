
package tamagotchi.domain;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Pet-class represents the pet, the main focus of the game.
 */
/**
 *
 * @author Heli
 */
public class Pet {
    private String name;
    private String defaultName;
    private Stat energy;
    private Stat happiness;
    private Stat health;
    private Stat hygiene;
    private LocalDate birthday;
    private boolean isSick;
    private boolean needsWash;
    private long lastLogin;
    private int developmentStage;
    private int age;
    
    public Pet() {
        this.defaultName = "Zorblax";
        this.name = this.defaultName;
        
        this.energy = new Stat(50.0);
        this.happiness = new Stat(50.0);
        this.health = new Stat(50.0);
        this.hygiene = new Stat(50.0);
        
        this.birthday = LocalDate.now();
        
        this.isSick = false;
        this.needsWash = false;
        
        Date time = new Date();
        this.lastLogin = TimeUnit.MILLISECONDS.toSeconds(time.getTime());
        
        this.developmentStage = 1;
    }
    
    
    
    public long getLastLogin() {
        return this.lastLogin;
    }
    
    public void setLastLogin(long time) {
        this.lastLogin = time;
    }
    
    @Override
    public String toString() {
        return "Name: " + this.name 
                + "\nBirthday: " + this.getBirthday() 
                + "\nAge: " + this.getAge() + " days"
                + "\nEnergy: " + this.energy 
                + "\nHappiness: " + this.happiness 
                + "\nHealth: " + this.health 
                + "\nHygiene: " + this.hygiene;
    }
    
    public void setName(String name) {
        if (name == null || name.equals("") || name.contains(";")) {
            this.name = this.defaultName;
        } else {
            this.name = name;
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setAge(int days) {
        this.age = days;
    }
    
    public int getAge() {
        return this.age;
    }
    
    /**
     * Calculates Pet's age.
     * Checks the amount of days between Pet's birthday and the current time.
     */
    public void calculateAge() {
        LocalDate today = LocalDate.now();
        
        int days = (int) DAYS.between(this.birthday, today);
        this.age = days;
    }
    
    
    /**
    * Sets a new birthday for the pet.
    * New date is given as a string using form yyyy-mm-dd
    * 
    * @param yyyymmdd new date for birthday
    * 
    */
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
    
    public Stat getHappiness() {
        return this.happiness;
    }
    
    public Stat getHealth() {
        return this.health;
    }
    
    public Stat getHygiene() {
        return this.hygiene;
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
    
    /**
     * Determines the development stage of the pet.
     * 1 = baby, 2 = youngling, 3 = adult.
     * Stage is determined by pet's age.
     * Age is expressed in days.
     */
    public void setDevelopmentStage() {
        if (this.getAge() <= 3) {
            this.developmentStage = 1;
        } else if (this.getAge() <= 7) {
            this.developmentStage = 2;
        } else {
            this.developmentStage = 3;
        }
    }
    
    public int getDevelopmentStage() {
        return this.developmentStage;
    }
}
