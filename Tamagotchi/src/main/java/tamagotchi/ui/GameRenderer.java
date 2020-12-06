/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.ui;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


/**
 *
 * @author Heli
 */
public class GameRenderer {
    private Canvas canvas;
    private GraphicsContext context;
    private Sprite sprite;
    
    private double centerX;
    private double centerY;
    
    private boolean firstRotation;
    
    private String spriteOrientation;
    private int yDirection;
    
    private boolean needCleaning;
    private boolean showVirus;
    
    private Image spriteImageLeft;
    private Image spriteImageRight;
    
    private Image spriteAngry;
    private Image spriteHappy;
    
    private Image skull;
    private Image waste;
    
    
    public GameRenderer(Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.sprite = new Sprite();
        this.centerX = (this.canvas.getWidth() / 2) - (this.sprite.getWidth() / 2);
        this.centerY = (this.canvas.getHeight() / 2) - (this.sprite.getHeight() / 2 + 30);
        this.firstRotation = true;
        this.spriteOrientation = "left";
        this.yDirection = 1;
        this.needCleaning = false;
        this.showVirus = false;
        this.spriteImageLeft = new Image("/images/spriteLeft.jpg");
        this.spriteImageRight = new Image("/images/spriteRight.jpg");
        this.spriteAngry = new Image("/images/angrySprite.jpg");
        this.spriteHappy = new Image("/images/happySprite.jpg");
        this.skull = new Image("/images/skull.jpg");
        this.waste = new Image("/images/alienWaste.jpg");
    }
    
    public void prepare() {
        context.setFill(Color.web("#f0f2f5", 1.0));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    public void render() {
        prepare();
        
        spriteIdleMovement();
        
        if (this.spriteOrientation.equals("right")) {
            context.drawImage(spriteImageRight, sprite.getX(), sprite.getY(), sprite.getHeight(), sprite.getWidth());
        } else {
            context.drawImage(spriteImageLeft, sprite.getX(), sprite.getY(), sprite.getHeight(), sprite.getWidth());
        }
        
        if (this.showVirus) {
            context.drawImage(skull, this.canvas.getWidth() - 64.0, 0.0);
        }
        
        if (this.needCleaning) {
            context.drawImage(waste, this.canvas.getWidth() * 0.25, this.canvas.getHeight() - 64.0);
        }
    }
    
    public void spriteIdleMovement() {
        if (this.firstRotation) {
            spritePlaceCenter();
            this.firstRotation = false;
        } else {
            double distanceFromCenter = Math.abs(this.sprite.getX() - this.centerX);
            if (this.spriteOrientation.equals("left")) {
                if (distanceFromCenter < this.sprite.getWidth() * 1.5) {
                    spriteStepLeft();
                } else {
                    this.spriteOrientation = "right";
                    spriteStepRight();
                }
            } else {
                if (distanceFromCenter < this.sprite.getWidth() * 1.5) {
                    spriteStepRight();
                } else {
                    this.spriteOrientation = "left";
                    spriteStepLeft();
                }
            }
            if (this.yDirection < 0) {
                spriteStepDown();
            } else {
                spriteStepUp();
            }
            this.yDirection *= (-1);
        }
    }
    
    public void spritePlaceCenter() {
        this.sprite.setX(centerX);
        this.sprite.setY(centerY);
    }
    
    public void spriteStepLeft() {
        this.sprite.setX(this.sprite.getX() - (this.sprite.getWidth() / 2));
    }
    
    public void spriteStepRight() {
        this.sprite.setX(this.sprite.getX() + (this.sprite.getWidth() / 2));
    }
    
    public void spriteStepUp() {
        this.sprite.setY(this.sprite.getY() + (this.sprite.getHeight() / 2));
    }
    
    public void spriteStepDown() {
        this.sprite.setY(this.sprite.getY() - (this.sprite.getHeight() / 2));
    }
    
    public void setShowVirus(boolean value) {
        this.showVirus = value;
    }
    
    public void setNeedCleaning(boolean value) {
        this.needCleaning = value;
    }
    
    public void setSpriteImage(int petDevelopmentStage) {
        if (petDevelopmentStage == 1) {
            this.spriteImageLeft = new Image("/images/spriteLeft.jpg");
            this.spriteImageRight = new Image("/images/spriteRight.jpg");
        } else if (petDevelopmentStage == 2) {
            this.spriteImageLeft = new Image("/images/spriteLeft.jpg");
            this.spriteImageRight = new Image("/images/spriteRight.jpg");
        } else {
            this.spriteImageLeft = new Image("/images/spriteLeft.jpg");
            this.spriteImageRight = new Image("/images/spriteRight.jpg");
        }
    }
    
    public void renderSprite() {
        prepare();
        context.drawImage(this.spriteImageLeft, this.centerX, this.centerY);
    }
    public void renderAngry() {
        prepare();
        context.drawImage(this.spriteAngry, this.centerX, this.centerY);
    }
    
    public void renderHappy() {
        prepare();
        context.drawImage(this.spriteHappy, this.centerX, this.centerY);
    }
}
