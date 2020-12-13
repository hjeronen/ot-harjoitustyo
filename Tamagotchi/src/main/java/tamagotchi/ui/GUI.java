
package tamagotchi.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tamagotchi.logic.PetCare;
import javafx.fxml.FXMLLoader;
import javafx.animation.AnimationTimer;
import tamagotchi.dao.FilePetDao;
import tamagotchi.dao.SQLPetCemeteryDao;


/**
 * Graphic User Interface.
 * Controls the graphic user interface and other classes used to create it, 
 * connects game logic to them. 
 * @author Heli
 */
public class GUI extends Application {
    private Stage stage;
    private PetCare petCare;
    private boolean isPaused;
    
    private MainGameSceneController gameController;
    
    private GameRenderer renderer;
    
    /**
     * Initializes the GUI.
     * Loads the saved game or creates a new one and gives it to 
     * the game logic class PetCare.
     * 
     * @throws Exception 
     */
    @Override
    public void init() throws Exception {
        FilePetDao petDao = new FilePetDao("saveFile.txt");
        SQLPetCemeteryDao petCemetery = new SQLPetCemeteryDao();
        this.petCare = new PetCare(petDao, petCemetery);
    }
    
    
    /**
     * Start-method.
     * Prepares the stage and sets the correct scene, runs the game loop 
     * (abstract class AnimationTimer).
     * 
     * @param stage
     * @throws NullPointerException
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws NullPointerException, Exception {    
        this.stage = stage;
        
        if (!this.petCare.getPetDao().saveExists()) {
            setNewGameScene();
        } else {
            this.petCare.getStatManager().calculatePetStats();
            if (this.petCare.petIsAlive()) {
                setGameScene(); 
            } else {
                setGameOverScene();
            }
        }
        
        /**
         * The game loop.
         */
        new AnimationTimer() {
            long lastCheck = java.lang.System.currentTimeMillis();
            long occurrenceCheck = java.lang.System.currentTimeMillis();
            
            /**
             * Updates game view.
             * Updates Pet's status and uses renderer to draw Pet's sprite 
             * on the canvas every 1.5 seconds. Every 10 seconds uses PetCare 
             * to check if Pet is sick or needs cleaning.
             * 
             * @param currentNanoTime   application's running time
             */
            @Override
            public void handle(long currentNanoTime) {
                long now = java.lang.System.currentTimeMillis();
                if (!isPaused) {
                    if (now - occurrenceCheck >= 10000) {
                        petCare.checkIfPetGetsSick();
                        petCare.checkIfPetNeedsCleaning();
                        occurrenceCheck = now;
                    }
                    long time = now - lastCheck;
                    if (time >= 1500) {
                        renderer.setShowVirus(petCare.getPet().getIsSick());
                        renderer.setNeedCleaning(petCare.getPet().getNeedsWash());
                        
                        renderer.render();
                        
                        update((double) time/1000);
                    
                        lastCheck = now;
                    }
                }
        }
        }.start();
    }
    
    /**
     * Sets the scene 'NewGameScene'.
     * Loads NewGameScene from a FXML-file, creates a scene and sets it in 
     * the stage.
     * 
     * @throws Exception 
     */
    public void setNewGameScene() throws Exception {
        this.isPaused = true;
        
        FXMLLoader newGameLoader = new FXMLLoader(getClass().getResource("/fxml/NewGame.fxml"));
        Parent startScene = newGameLoader.load();
        NewGameSceneController startController = newGameLoader.getController();
        
        startController.setApplication(this);
        Scene startNewGameScene = new Scene(startScene);
        
        this.stage.setTitle("Tamagotchi");
        this.stage.setScene(startNewGameScene);
        this.stage.show();
    }

    /**
     * Sets the scene 'MainGameScene'.
     * Loads MainGameScene from a FXML-file, creates a scene and sets it in 
     * the stage. Also creates a renderer used to draw the sprite on 
     * the MainGameScene canvas. Sets up the renderer to show correct Pet-sprite 
     * and additional images.
     * @throws IOException 
     */
    public void setGameScene() throws IOException {
        this.isPaused = false;
        
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/fxml/MainGameScene.fxml"));
        Parent game = gameLoader.load();
        this.gameController = gameLoader.getController();
        
        this.gameController.setApplication(this);
        this.gameController.setUpLabel();
        this.gameController.setUpBars();
        
        Scene gameScene = new Scene(game);
        
        this.renderer = new GameRenderer(this.gameController.getCanvas());
        this.petCare.checkIfPetGetsSick();
        this.renderer.setShowVirus(this.petCare.getPet().getIsSick());
        this.petCare.checkIfPetNeedsCleaning();
        this.renderer.setNeedCleaning(this.petCare.getPet().getNeedsWash());
        this.renderer.setSpriteImage(this.petCare.getPet().getDevelopmentStage());
        
        this.stage.setTitle("Tamagotchi");
        this.stage.setScene(gameScene);
        this.stage.show();
    }
    
    /**
     * Sets the scene 'GameOverScene'.
     * Loads GameOverScene from a FXML-file, creates a scene and sets it in 
     * the stage.
     * 
     * @throws IOException 
     */
    public void setGameOverScene() throws IOException {
        this.isPaused = true;
        
        FXMLLoader gameOverLoader = new FXMLLoader(getClass().getResource("/fxml/GameOverScene.fxml"));
        Parent gameOver = gameOverLoader.load();
        GameOverSceneController gameOverController = gameOverLoader.getController();
        
        gameOverController.setApplication(this);
        Scene gameOverScene = new Scene(gameOver);
        
        this.stage.setTitle("Tamagotchi");
        this.stage.setScene(gameOverScene);
        this.stage.show();
    }
    
    /**
     * Sets the scene 'MiniGameScene'.
     * Loads MiniGameScene from a FXML-file, creates a scene and sets it in 
     * the stage.
     * 
     * @throws IOException 
     */
    public void setMiniGameScene() throws IOException {
        this.isPaused = true;
        
        FXMLLoader miniGameLoader = new FXMLLoader(getClass().getResource("/fxml/MiniGameScene.fxml"));
        Parent miniGameScene = miniGameLoader.load();
        MiniGameSceneController minigameController = miniGameLoader.getController();
        Scene miniGame = new Scene(miniGameScene);
        
        minigameController.setApplication(this);
        minigameController.setUpRenderer();
        this.stage.setTitle("Tamagotchi");
        this.stage.setScene(miniGame);
        this.stage.show();
    }
    
    /**
     * Calls PetCare to update Pet's stats during game.
     * Also instructs the MainGameSceneController to update the progress bars 
     * to show the values of Pet's stats properly.
     * 
     * @param time  time elapsed since last update.
     * @see tamagotchi.logic.StatManager#updateStats()
     * @see MainGameSceneController#setUpBars()
     */
    public void update(double time) {
        this.petCare.getStatManager().updateStats(time);
        this.gameController.setUpBars();
    }
    
    public PetCare getPetCare() {
        return this.petCare;
    }
    
    /**
     * Creates a new PetCare for a new game.
     * PetCare creates a new game save for a new game. Saving method may 
     * throw an exception.
     * 
     * @throws Exception 
     * @see tamagotchi.logic.PetCare#createNewPetSave()
     * @see tamagotchi.dao.FilePetDao#createSave(tamagotchi.domain.Pet) 
     */
    public void setUpNewPetCare() throws Exception {
        this.petCare.createNewPetSave();
    }
    
    /**
     * Saves the game when program is closed.
     * When the user stops the program or closes the window, the current 
     * status of the Pet is saved.
     * 
     * @see tamagotchi.dao.FilePetDao#createSave(tamagotchi.domain.Pet) 
     */
    @Override
    public void stop() {
      this.petCare.getPetDao().createSave(this.petCare.getPet());
    } 
    
    /**
     * Main-method.
     * Called from the Main-class to launch the application.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }


    
}
