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
public class Stat {
    private double min;
    private double max;
    private double value;
    
    public Stat(double initialValue) {
        this.min = 0.0;
        this.max = 100.0;
        this.value = initialValue;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public double getValue() {
        return this.value;
    }
    
    //Obsolete
    public double getValueDouble() {
        return (float) this.value / this.max;
    }
    
    public void increase(double amount) {
        if (this.value + amount >= this.max) {
            this.value = this.max;
        } else {
            this.value += amount;
        }     
    }
    
    public void increase() {
        this.increase(1.0);
    }
    
    public void decrease(double amount) {
        if (this.value - amount <= this.min) {
            this.value = this.min;
        } else {
            this.value -= amount; 
        }    
    }
    
    public void decrease() {
        this.decrease(1.0);
    }
    
    public void setValue(double newValue) {
        this.value = newValue;
    }
    
    public String toString() {
        return "" + this.value;
    }
}
