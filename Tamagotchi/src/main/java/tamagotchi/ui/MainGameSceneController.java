package tamagotchi.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.scene.canvas.Canvas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import tamagotchi.domain.PetCare;

/**
 * FXML Controller class
 *
 * @author Heli
 */
public class MainGameSceneController implements Initializable {
    
    private PetCare petCare;
    
    @FXML
    public Canvas gameCanvas;
    
    @FXML
    private ProgressBar energyProgressBar;
    @FXML
    private ProgressBar happinessProgressBar;
    @FXML
    private ProgressBar healthProgressBar;
    
    
    
    @FXML
    private void handleButtonActionFeed() {
        this.petCare.feedPet();
        energyProgressBar.setProgress(this.petCare.getPet().getEnergy().getValueDouble());
    }
    
    @FXML
    private void handleButtonActionPlay() {
        System.out.println("Not supported yet.");
    }
    
    @FXML
    private void handleButtonActionHeal() {
        this.petCare.healPet();
        healthProgressBar.setProgress(this.petCare.getPet().getHealth().getValueDouble());
    }
    
    public void givePetName(String petName) {
        this.petCare.getPet().setName(petName);
    }
    
    public void setUpPetCare(PetCare petCare) {
        this.petCare = petCare;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.petCare = new PetCare(); //Use interface and change to Fake?
        energyProgressBar.setProgress(this.petCare.getPet().getEnergy().getValueDouble());
        happinessProgressBar.setProgress(this.petCare.getPet().getHappiness().getValueDouble());
        healthProgressBar.setProgress(this.petCare.getPet().getHealth().getValueDouble());
    }
       
    
}
