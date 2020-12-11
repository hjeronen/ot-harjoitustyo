/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Visual representation of the Pet.
 * Holds all the information of Sprite's position and images.
 * @author Heli
 */
public class Sprite {
    private Image imageLeft;
    private Image imageRight;
    private Image imageHappy;
    private Image imageAngry;
    private double positionX;
    private double positionY;
    private double hight;
    private double width;
    
    public Sprite() {
        this.positionX = 42.0;
        this.positionY = 52.0;
        this.hight = 64.0;
        this.width = 64.0;
        this.imageLeft = new Image("/images/babyLeft.jpg");
        this.imageRight = new Image("/images/babyRight.jpg");
        this.imageHappy = new Image("/images/babyHappy.jpg");
        this.imageAngry = new Image("/images/babyAngry.jpg");
    }
    
    /**
     * Sets the correct image for the sprite.
     * Sprite's image is determined by Pet's development stage, 
     * which is given as a parameter.
     * 
     * @param petDevelopmentStage   1 = baby, 2 = youngling, 3 = adult
     */
    public void setSpriteImages(int petDevelopmentStage) {
        if (petDevelopmentStage == 1) {
            this.imageLeft = new Image("/images/babyLeft.jpg");
            this.imageRight = new Image("/images/babyRight.jpg");
            this.imageHappy = new Image("/images/babyHappy.jpg");
            this.imageAngry = new Image("/images/babyAngry.jpg");
        } else if (petDevelopmentStage == 2) {
            this.imageLeft = new Image("/images/youngLeft.jpg");
            this.imageRight = new Image("/images/youngRight.jpg");
            this.imageHappy = new Image("/images/youngHappy.jpg");
            this.imageAngry = new Image("/images/youngAngry.jpg");
            this.hight = 96.0;
            this.width = 96.0;
        } else {
            this.imageLeft = new Image("/images/adultLeft.jpg");
            this.imageRight = new Image("/images/adultRight.jpg");
            this.imageHappy = new Image("/images/adultHappy.jpg");
            this.imageAngry = new Image("/images/adultAngry.jpg");
            this.hight = 96.0;
            this.width = 96.0;
        }
    }
    
    /**
     * Draws left-facing sprite image to the given context.
     * @param context   drawing context
     */
    public void drawSpriteLeft(GraphicsContext context) {
        context.drawImage(this.imageLeft, this.positionX, this.positionY, this.hight, this.width);
    }
    
    /**
     * Draws right-facing sprite image to the given context.
     * @param context   drawing context
     */
    public void drawSpriteRight(GraphicsContext context) {
        context.drawImage(this.imageRight, this.positionX, this.positionY, this.hight, this.width);
    }
    
    /**
     * Draws happy sprite to the given context.
     * @param context   drawing context
     */
    public void drawSpriteHappy(GraphicsContext context) {
        context.drawImage(this.imageHappy, this.positionX, this.positionY, this.hight, this.width);
    }
    
    /**
     * Draws angry sprite to the given context.
     * @param context   drawing context
     */
    public void drawSpriteAngry(GraphicsContext context) {
        context.drawImage(this.imageAngry, this.positionX, this.positionY, this.hight, this.width);
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
