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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import tamagotchi.domain.PetCare;

/**
 * FXML Controller class
 *
 * @author Heli
 */
public class MainGameSceneController implements Initializable {
    
    private PetCare petCare;
    private GUI userinterface;
    
    @FXML
    public Canvas gameCanvas;
    
    @FXML
    public AnchorPane gameAnchor;
    
    @FXML
    private Label petName;
    
    @FXML
    private ProgressBar energyProgressBar;
    @FXML
    private ProgressBar happinessProgressBar;
    @FXML
    private ProgressBar healthProgressBar;
    @FXML
    private ProgressBar hygieneProgressBar;
    
    public void setApplication(GUI ui) {
        this.userinterface = ui;
    }
    
    @FXML
    private void handleButtonActionFeed() {
        this.userinterface.getPetCare().feedPet();
        energyProgressBar.setProgress(this.userinterface.getPetCare().getPet().getEnergy().getValueDouble());
    }
    
    @FXML
    private void handleButtonActionPlay() {
        System.out.println("Not supported yet.");
    }
    
    @FXML
    private void handleButtonActionHeal() {
        this.userinterface.getPetCare().healPet();
        healthProgressBar.setProgress(this.userinterface.getPetCare().getPet().getHealth().getValueDouble());
    }
    
    @FXML
    private void handleButtonActionClean() {
        this.userinterface.getPetCare().cleanPet();
        hygieneProgressBar.setProgress(this.userinterface.getPetCare().getPet().getHygiene().getValueDouble());
    }
    
    @FXML
    private void handleButtonActionTest() {
        System.out.println(this.userinterface.getPetCare().getPet().getName());
    }
    
//    public void givePetName(String petName) {
//        this.userinterface.getPetCare().getPet().setName(petName);
//    }
    
//    public void setUpPetCare() {
//        this.petCare = this.userinterface.getPetCare();
//        this.petName.setText(this.petCare.getPet().getName());
//    }
    
    public void setUpBars() {
        energyProgressBar.setProgress(this.userinterface.getPetCare().getPet().getEnergy().getValueDouble());
        happinessProgressBar.setProgress(this.userinterface.getPetCare().getPet().getHappiness().getValueDouble());
        healthProgressBar.setProgress(this.userinterface.getPetCare().getPet().getHealth().getValueDouble());
        hygieneProgressBar.setProgress(this.userinterface.getPetCare().getPet().getHygiene().getValueDouble());
    }
    
    public void setUpLabel() {
        this.petName.setText(this.userinterface.getPetCare().getPet().getName());
    }
    
    public Canvas getCanvas() {
        return this.gameCanvas;
    }
    
    private void initialiseCanvas() {
        gameCanvas.widthProperty().bind(gameAnchor.widthProperty());
        gameCanvas.heightProperty().bind(gameAnchor.heightProperty());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }
    //TODO: Move this to the GUI!
//    public void update() {
//        setUpBars();
//    }
       
    
}
