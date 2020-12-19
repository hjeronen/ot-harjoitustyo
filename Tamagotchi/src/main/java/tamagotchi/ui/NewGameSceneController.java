package tamagotchi.ui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

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
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private Tooltip nameRestrictions;
    
    
    public void setApplication(GUI ui) {
        this.userinterface = ui;
    }
    
    
    /**
     * Start the game.
     * Pressing button 'Start Game' switches the game view to MainGameScene.
     * Before that places text from TextField this.inputPetName to this.petName and checks if 
     * the given name is valid.
     * @throws Exception 
     */
    @FXML
    public void startGame() throws Exception {
        this.petName = this.inputPetName.getText();
        if (checkName()) {
            this.userinterface.getPetCare().createNewPetSave();
            this.userinterface.getPetCare().getPet().setName(this.petName);
            this.userinterface.getPetCare().saveGame();
            this.userinterface.setMainGameScene();
        }
    }
    
    /**
     * Shows correct error message in the errorLabel.
     * @param number    determines which message is shown
     */
    public void showErrorLabel(int number) {
        if (number == 1) {
            this.errorLabel.setText("Name cannot contain ';'!");
        } else if (number == 2) {
            this.errorLabel.setText("Name has to be 1-10 characters long.");
        }
    }
    
    /**
     * Checks if input name is valid.
     * Also calls showErrorLabel() to put in correct error message.
     * @return boolean  true if name is valid, false if it's invalid
     */
    public boolean checkName() {
        this.petName = this.petName.trim();
        if (this.petName.contains(";")) {
            showErrorLabel(1);
            return false;
        } else if (this.petName == null || this.petName.length() > 10 || this.petName.length() == 0) {
            showErrorLabel(2);
            return false;
        } else {
            return true;
        }
    }
    
    @FXML
    public void showTooltip() {
        this.nameRestrictions.setText("Name must be 1-10 characters long and must not contain ';'.");
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
