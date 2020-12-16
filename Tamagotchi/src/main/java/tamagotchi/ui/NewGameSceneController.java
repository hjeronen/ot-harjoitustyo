package tamagotchi.ui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    
    @FXML
    private Label errorLabel;
    
    
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
    
    
    /**
     * Start the game.
     * Pressing button 'Start Game' switches the game view to MainGameScene.
     * 
     * @throws Exception 
     */
    public void startGame() throws Exception {
        if (checkName()) {
            this.userinterface.getPetCare().getPet().setName(this.petName);
            this.userinterface.setMainGameScene();
        }
        
    }
    
    /**
     * Shows correct error message in the errorLabel.
     * @param number    determines which message is shown
     */
    public void showErrorLabel(int number) {
        if (number == 1) {
            this.errorLabel.setText("Remember to save your name!");
        } else if (number == 2) {
            this.errorLabel.setText("Name cannot contain ';'!");
        } else if (number == 3) {
            this.errorLabel.setText("Name has to be 1 to 10 characters.");
        }
        
    }
    
    /**
     * Checks if input name is valid.
     * Also calls showErrorLabel() to put in correct error message.
     * @return boolean  true if name is valid, false if it's invalid
     */
    public boolean checkName() {
        if (this.petName == null) {
            showErrorLabel(1);
            return false;
        } else if (this.petName.contains(";")) {
            showErrorLabel(2);
            return false;
        } else if (this.petName.length() > 10 || this.petName.length() <= 0) {
            showErrorLabel(3);
            return false;
        } else {
            return true;
        }
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
