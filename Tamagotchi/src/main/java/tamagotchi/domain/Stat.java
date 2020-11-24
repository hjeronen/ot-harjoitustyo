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
    private int min;
    private int max;
    private int value;
    
    public Stat(int initialValue) {
        this.min = 0;
        this.max = 100;
        this.value = initialValue;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public double getValueDouble() {
        return (float) this.value / this.max;
    }
    
    public void increase(int amount) {
        if (this.value + amount >= this.max) {
            this.value = this.max;
        } else {
            this.value += amount;
        }     
    }
    
    public void increase() {
        this.increase(1);
    }
    
    public void decrease(int amount) {
        if (this.value - amount <= this.min) {
            this.value = this.min;
        } else {
            this.value -= amount; 
        }    
    }
    
    public void decrease() {
        this.decrease(1);
    }
    
    public void setValue(int newValue) {
        this.value = newValue;
    }
    
    public String toString() {
        return "" + this.value;
    }
}
