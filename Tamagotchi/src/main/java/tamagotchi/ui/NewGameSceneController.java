package tamagotchi.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Heli
 */
public class NewGameSceneController implements Initializable {
    
    private GUI userinterface;
    private String petName;

    
    @FXML
    private TextField inputPetName;
    
    
    public void setApplication(GUI ui) {
        this.userinterface = ui;
    }
    
    @FXML
    public void setPetName(ActionEvent event) {
        this.petName = inputPetName.getText();
    }
    
    public String getPetName() {
        return this.petName;
    }
    
    //Button 'Start game' runs this method
    public void startGame() throws Exception {
        this.userinterface.getPetCare().getPet().setName(this.petName);
        this.userinterface.setGameScene();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    
}
