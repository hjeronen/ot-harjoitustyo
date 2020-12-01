/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tamagotchi.domain.PetCare;
import javafx.fxml.FXMLLoader;
import javafx.animation.AnimationTimer;
import tamagotchi.dao.FilePetDao;


/**
 *
 * @author Heli
 */
public class GUI extends Application {
    private Stage stage;
    private PetCare petCare;
    private Scene gameScene;
    private Scene startNewGameScene;
    private Scene gameOverScene;
    private boolean isPaused;
    
    private MainGameSceneController gameController;
    
    private GameRenderer renderer;
    
    
    @Override
    public void init() throws Exception {
        FilePetDao petDao = new FilePetDao("saveFile.txt");
        this.petCare = new PetCare(petDao);
        
    }
    
    

    @Override
    public void start(Stage stage) throws NullPointerException, Exception {    
        this.stage = stage;
        
        if (!this.petCare.getPetDao().saveExists()) {
            setNewGameScene();
        } else {
            this.petCare.calculatePetStats();
            if (this.petCare.petIsAlive()) {
                setGameScene(); 
            } else {
                setGameOverScene();
            }
        }
        
        new AnimationTimer() {
            long lastCheck = java.lang.System.currentTimeMillis();
            long occurrenceCheck = java.lang.System.currentTimeMillis();
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
    
    
    public void setNewGameScene() throws Exception {
        this.isPaused = true;
        
        FXMLLoader newGameLoader = new FXMLLoader(getClass().getResource("/fxml/NewGame.fxml"));
        Parent startScene = newGameLoader.load();
        NewGameController startController = newGameLoader.getController();
        
        startController.setApplication(this);
        this.startNewGameScene = new Scene(startScene);
        
        this.stage.setTitle("Tamagotchi");
        this.stage.setScene(startNewGameScene);
        this.stage.show();
    }

    
    public void setGameScene() throws IOException {
        this.isPaused = false;
        
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/fxml/MainGameScene.fxml"));
        Parent game = gameLoader.load();
        this.gameController = gameLoader.getController();
        
        this.gameController.setApplication(this);
        this.gameController.setUpLabel();
        this.gameController.setUpBars();
        
        this.gameScene = new Scene(game);
        
        this.renderer = new GameRenderer(this.gameController.getCanvas());
        petCare.checkIfPetGetsSick();
        renderer.setShowVirus(petCare.getPet().getIsSick());
        petCare.checkIfPetNeedsCleaning();
        renderer.setNeedCleaning(petCare.getPet().getNeedsWash());
        
        this.stage.setTitle("Tamagotchi");
        this.stage.setScene(gameScene);
        this.stage.show();
    }
    
    public void setGameOverScene() throws IOException {
        this.isPaused = true;
        
        FXMLLoader gameOverLoader = new FXMLLoader(getClass().getResource("/fxml/GameOverScene.fxml"));
        Parent restartScene = gameOverLoader.load();
        GameOverSceneController gameOverController = gameOverLoader.getController();
        
        gameOverController.setApplication(this);
        this.gameOverScene = new Scene(restartScene);
        
        this.stage.setTitle("Tamagotchi");
        this.stage.setScene(this.gameOverScene);
        this.stage.show();
    }
    
    
    public void update(double time) {
        this.petCare.updateStats(time);
        this.gameController.setUpBars();
    }
    
    public PetCare getPetCare() {
        return this.petCare;
    }
    
    public void setUpNewPetCare() throws Exception {
        this.petCare.createNewPetSave();
    }
    
    
    @Override
    public void stop() {
      this.petCare.getPetDao().createSave(this.petCare.getPet());
    } 
    
    public static void main(String[] args) {
        launch(args);
    }


    
}
