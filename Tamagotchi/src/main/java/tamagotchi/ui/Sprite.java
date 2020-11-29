/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.ui;

import javafx.scene.image.Image;

/**
 *
 * @author Heli
 */
public class Sprite {
    public Image image;
    private double positionX;
    private double positionY;
    private double hight;
    private double width;
    
    public Sprite() {
        //this.image = new Image("/images/PracticeSprite.jpg");
        this.positionX = 42.0;
        this.positionY = 52.0;
        this.hight = 64.0;
        this.width = 64.0;
    }
    
    public void setImage(String filename) {
        this.image = new Image(filename);
    }
    
    public void setImage(Image i) {
        this.image = i;
    }
    
    public Image getImage() {
        System.out.println("This also happens.");
        return new Image("/images/PracticeSprite.jpg");
        
    }
    
    public double getX() {
        return this.positionX;
    }
    
    public double getY() {
        return this.positionY;
    }
    
    public double getHeight() {
        return this.hight;
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public void setX(double value) {
        this.positionX = value;
    }
    
    public void setY(double value) {
        this.positionY = value;
    }
}
