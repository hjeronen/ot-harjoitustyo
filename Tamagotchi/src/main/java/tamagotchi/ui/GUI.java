/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tamagotchi.domain.PetCare;
import javafx.fxml.FXMLLoader;
import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
import tamagotchi.dao.FakePetDao;
import tamagotchi.dao.FilePetDao;


import tamagotchi.domain.Pet;
/**
 *
 * @author Heli
 */
public class GUI extends Application {
    private Stage stage;
    private PetCare petCare;
    private Scene gameScene;
    private Scene startNewGameScene;
    private boolean isPaused;
    private String saveFile;
    
    private MainGameSceneController gameController;
    private GameRenderer renderer;
    
    
    @Override
    public void init() throws Exception {
        this.petCare = new PetCare();
    }
    
    

    @Override
    public void start(Stage stage) throws Exception {    
        this.stage = stage;
        
        setNewGameScene();
        
        
        new AnimationTimer() {
            long lastCheck = java.lang.System.currentTimeMillis();
            long occurrenceCheck = java.lang.System.currentTimeMillis();
            @Override
            public void handle(long currentNanoTime) {
                long now = java.lang.System.currentTimeMillis();
                if (!isPaused) {
//                    if (occurrenceCheck >= 10000) {
//                        petCare.checkIfPetGetsSick();
//                        if (petCare.getPet().getIsSick()) {
//                            renderer.setShowVirus(true);
//                        }
//                        petCare.checkIfPetNeedsCleaning();
//                        if (petCare.getPet().getNeedsWash()) {
//                           renderer.setNeedCleaning(true);
//                        }
//                       occurrenceCheck = now;
//                    }
                    
                    if (now - lastCheck >= 1500) {
                        renderer.render();
                        update();
                    
                        lastCheck = now;
                    }
                    
                    
                }
        }
        }.start();
        
    }
    
    public void renamePet(String petName) {
        this.petCare.getPet().setName(petName);
    }
    
//    public void run() throws IOException {
//        
//        
//        long lastCheck = java.lang.System.currentTimeMillis();
//        
//        while (true) {
//            long now = java.lang.System.currentTimeMillis();
//            if (now - lastCheck >= 2000) {
//                this.gameController.update();
//                System.out.println(this.petCare.getPet().toString());
//                //setGameScene();
//                //setUpBars();
//                //renderer.render();
//                lastCheck = now;
//            }
//        }
//    }
    
    public void setNewGameScene() throws Exception {
        this.isPaused = true;
        
        FXMLLoader newGameLoader = new FXMLLoader(getClass().getResource("/fxml/NewGame.fxml"));
        Parent startScene = newGameLoader.load();
        NewGameController startController = newGameLoader.getController();
        
        startController.setApplication(this);
        this.startNewGameScene = new Scene(startScene);
        
        stage.setTitle("Tamagotchi");
        stage.setScene(startNewGameScene);
        stage.show();
    }

    
    public void setGameScene() throws IOException {
        //load petCare here
        
        this.isPaused = false;
        
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/fxml/MainGameScene.fxml"));
        Parent game = gameLoader.load();
        this.gameController = gameLoader.getController();
        //gameController.setUpPetCare();
        
        gameController.setApplication(this);
        gameController.setUpLabel();
        gameController.setUpBars();
        
        this.gameScene = new Scene(game);
        
        renderer = new GameRenderer(gameController.getCanvas());
        
        this.stage.setScene(gameScene);
        stage.show();
    }
    
    public void update() {
        this.petCare.updateEnergy();
        this.gameController.setUpBars();
        System.out.println(this.petCare.getPet().toString());
    }
    
    public PetCare getPetCare() {
        return this.petCare;
    }
    
    @Override
    public void stop() {
      // tee lopetustoimenpiteet täällä
      System.out.println("sovellus sulkeutuu");
    } 
    
    public static void main(String[] args) {
        launch(args);
    }


    
}
