
package tamagotchi.ui;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


/**
 * The renderer used for the game.
 * Renders images for game canvas.
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
        this.skull = new Image("/images/skull.jpg");
        this.waste = new Image("/images/alienWaste.jpg");
    }
    
    /**
     * Prepares the canvas.
     * Fills the canvas with color f0f2f5, painting over the previous image.
     */
    public void prepare() {
        context.setFill(Color.web("#f0f2f5", 1.0));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    public GraphicsContext getContext() {
        return this.context;
    }
    
    /**
     * Renders the game view.
     * Repaints the canvas and calculates the correct position and orientation 
     * for sprite. Uses Sprite's own methods to draw picture. Checks if skull 
     * or waste images need to be shown and draws them.
     */
    public void render() {
        prepare();
        
        spriteIdleMovement();
        
        if (this.spriteOrientation.equals("right")) {
            this.sprite.drawSpriteRight(context);
        } else {
            this.sprite.drawSpriteLeft(context);
        }
        
        if (this.showVirus) {
            context.drawImage(skull, this.canvas.getWidth() - 64.0, 0.0);
        }
        
        if (this.needCleaning) {
            context.drawImage(waste, this.canvas.getWidth() * 0.25, this.canvas.getHeight() - 64.0);
        }
    }
    
    /**
     * Calculates the right position for sprite.
     * If sprite is drawn for the first time it is placed on the center of 
     * the canvas. Otherwise if sprite is less than two 'steps' from the center, 
     * it will continue on that direction. If it's far enough from the center, 
     * it will change direction. Sprite steps both to left/right and up/down. If 
     * this.yDirection is 1, it will go up, if it is -1 it will go down. 
     * The yDirection changes after every step.
     */
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
    
    /**
     * Places sprite at the center of the canvas.
     * Sets Sprite's x and y coordinates accordingly.
     */
    public void spritePlaceCenter() {
        this.sprite.setX(centerX);
        this.sprite.setY(centerY);
    }
    
    /**
     * Changes sprite's x-coordinate half of its width to the left.
     */
    public void spriteStepLeft() {
        this.sprite.setX(this.sprite.getX() - (this.sprite.getWidth() / 2));
    }
    
    /**
     * Changes sprite's x-coordinate half of it's width to the right.
     */
    public void spriteStepRight() {
        this.sprite.setX(this.sprite.getX() + (this.sprite.getWidth() / 2));
    }
    
    /**
     * Changes sprite's y-coordinate half of it's height upwards.
     */
    public void spriteStepUp() {
        this.sprite.setY(this.sprite.getY() + (this.sprite.getHeight() / 2));
    }
    
    /**
     * Changes sprite's y coordinate half of it's height downwards.
     */
    public void spriteStepDown() {
        this.sprite.setY(this.sprite.getY() - (this.sprite.getHeight() / 2));
    }
    
    /**
     * Tells if skull needs to be drawn.
     * If Pet is sick, a skull icon will show.
     * 
     * @param value value of Pet's isSick variable.
     */
    public void setShowVirus(boolean value) {
        this.showVirus = value;
    }
    
    /**
     * Tells if waste needs to be drawn.
     * If Pet needs cleaning, a picture of waste will be drawn.
     * 
     * @param value value of Pet's needsWash.
     */
    public void setNeedCleaning(boolean value) {
        this.needCleaning = value;
    }
    
    /**
     * Tells the sprite which image to draw.
     * Pet has three development stages, each having it's own sprite. This tells 
     * Sprite which one to draw.
     * 
     * @param petDevelopmentStage   1 = baby, 2 = youngling, 3 = adult
     * @see Sprite#setSpriteImages(int)
     */
    public void setSpriteImage(int petDevelopmentStage) {
        sprite.setSpriteImages(petDevelopmentStage);
    }
    
    /**
     * Draws sprite.
     * Used in MiniGameSceneController to draw sprite.
     * 
     * @see MiniGameSceneController#setUpRenderer()
     * @see Sprite#drawSpriteLeft(javafx.scene.canvas.GraphicsContext) 
     */
    public void renderSprite() {
        prepare();
        spritePlaceCenter();
        this.sprite.drawSpriteLeft(this.context);
    }
    
    /**
     * Draws angry sprite.
     * Calls Sprite to draw an angry picture.
     * 
     * @see MiniGameSceneController#handleGuessWrong()
     * @see Sprite#drawSpriteAngry(javafx.scene.canvas.GraphicsContext)
     */
    public void renderAngry() {
        prepare();
        this.sprite.drawSpriteAngry(context);
    }
    
    /**
     * Draws happy sprite.
     * Calls Sprite to draw a happy picture.
     * 
     * @see MiniGameSceneController#handleGuessCorrect()
     * @see Sprite#drawSpriteHappy(javafx.scene.canvas.GraphicsContext)
     */
    public void renderHappy() {
        prepare();
        this.sprite.drawSpriteHappy(context);
    }
}
