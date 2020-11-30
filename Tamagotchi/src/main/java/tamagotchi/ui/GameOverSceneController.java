/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Heli
 */
public class GameOverSceneController implements Initializable {

    private GUI userinterface;
    
    @FXML
    public AnchorPane gameAnchor;
    
    @FXML
    public Canvas gameCanvas;
    
    public void setApplication(GUI ui) {
        this.userinterface = ui;
    }
    
    @FXML
    private void handleButtonActionRestart() throws Exception {
        this.userinterface.setUpNewPetCare();
        this.userinterface.setNewGameScene();
    }
    
    public Canvas getCanvas() {
        return this.gameCanvas;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
