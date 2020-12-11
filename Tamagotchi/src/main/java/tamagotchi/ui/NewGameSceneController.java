package tamagotchi.ui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * NewGameScene FXML Controller class.
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
    
    /**
     * Gets the text from Text Field inputPetName and sets it for this.petName.
     * @param event 
     */
    @FXML
    public void setPetName(ActionEvent event) {
        this.petName = inputPetName.getText();
    }
    
    public String getPetName() {
        return this.petName;
    }
    
    /**
     * Start the game.
     * Pressing button 'Start Game' switches the game view to MainGameScene.
     * 
     * @throws Exception 
     */
    public void startGame() throws Exception {
        this.userinterface.getPetCare().getPet().setName(this.petName);
        this.userinterface.setGameScene();
    }

    /**
     * Initializes the NewGameSceneController-class.
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    
}
