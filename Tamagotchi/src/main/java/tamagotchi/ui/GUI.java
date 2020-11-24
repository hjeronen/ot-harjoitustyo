/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tamagotchi.domain.PetCare;
import javafx.fxml.FXMLLoader;
/**
 *
 * @author Heli
 */
public class GUI extends Application {
    private Stage stage;
    private PetCare petCare;
    private Scene gameScene;
    private Scene startNewGameScene;
    

    @Override
    public void init() throws Exception {
        FXMLLoader newGameLoader = new FXMLLoader(getClass().getResource("/fxml/NewGame.fxml"));
        Parent startScene = newGameLoader.load();
        NewGameController startController = newGameLoader.getController();
        this.petCare = new PetCare(startController.getPetName());
        startController.setApplication(this);
        this.startNewGameScene = new Scene(startScene);
        
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/fxml/MainGameScene.fxml"));
        Parent game = gameLoader.load();
        MainGameSceneController gameController = gameLoader.getController();
        gameController.setUpPetCare(this.petCare);
        this.gameScene = new Scene(game);
        
    }
    
    

    @Override
    public void start(Stage stage) throws Exception {    
        this.stage = stage;
        
        stage.setTitle("Tamagotchi");
        stage.setScene(startNewGameScene);
        stage.show();
        
    }

    
    public void setGameScene() {
        this.stage.setScene(gameScene);
    }
    
    
    

    
    public static void main(String[] args) {
        launch(args);
    }


    
}
