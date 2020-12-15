
package tamagotchi.domain;

import java.text.DecimalFormat;

/**
* Stat-class represents a stat of a pet, for example the amount of energy it has.
*/
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
    
    /**
     * Increases this.value by a given amount
     * but not over this.max value.
     * 
     * @param amount    given amount to increase
     */
    public void increase(double amount) {
        if (this.value + amount >= this.max) {
            this.value = this.max;
        } else {
            this.value += amount;
        }     
    }
    
    /**
     * Increases this.value by 1.0.
     */
    public void increase() {
        this.increase(1.0);
    }
    
    /**
     * Decreases Stat's value.
     * Decreases this.value by a given amount
     * but not under this.min value.
     * 
     * @param amount    given amount to decrease.
     */
    public void decrease(double amount) {
        if (this.value - amount <= this.min) {
            this.value = this.min;
        } else {
            this.value -= amount; 
        }    
    }
    
    /**
     * Decreases Stat's value by 1.0.
     */
    public void decrease() {
        this.decrease(1.0);
    }
    
    /**
     * Sets new value for the Stat.
     * @param newValue  new value for the Stat
     */
    public void setValue(double newValue) {
        this.value = newValue;
    }
    
    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#.00");
        String formatValue = formatter.format(this.value);
        return formatValue;
    }
}
