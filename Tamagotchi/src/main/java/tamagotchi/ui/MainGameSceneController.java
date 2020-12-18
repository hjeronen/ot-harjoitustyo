package tamagotchi.ui;



import java.io.IOException;
import javafx.scene.canvas.Canvas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

/**
 * MainGameScene FXML Controller class.
 *
 * @author Heli
 */
public class MainGameSceneController implements Initializable {
    
    private GUI userinterface;
    
    /**
     * Canvas for drawing sprite image.
     */
    @FXML
    public Canvas gameCanvas;
    
    /**
     * Anchor Pane holding all other ui-elements.
     */
    @FXML
    public AnchorPane gameAnchor;
    
    @FXML
    private Label petName;
    
    @FXML
    private Tooltip petInfo;
    
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
    
    /**
     * Pressing Feed-button increases Pet's energy-stat.
     */
    @FXML
    private void handleButtonActionFeed() {
        this.userinterface.getPetCare().feedPet();
        energyProgressBar.setProgress(this.userinterface.getPetCare().getPet().getEnergy().getValue() / 100);
    }
    
    /**
     * Pressing Play-button starts the minigame and changes game view 
     * to MiniGameSene.
     * 
     * @throws IOException 
     */
    @FXML
    private void handleButtonActionPlay() throws IOException {
        this.userinterface.setMiniGameScene();
    }
    
    /**
     * Pressing Heal-button increases Pet's health-stat.
     */
    @FXML
    private void handleButtonActionHeal() {
        this.userinterface.getPetCare().healPet();
        healthProgressBar.setProgress(this.userinterface.getPetCare().getPet().getHealth().getValue() / 100);
    }
    
    /**
     * Pressing Clean-button sets Pet's hygiene-stat to max and clears waste 
     * from game view.
     */
    @FXML
    private void handleButtonActionClean() {
        this.userinterface.getPetCare().cleanPet();
        hygieneProgressBar.setProgress(this.userinterface.getPetCare().getPet().getHygiene().getValue() / 100);
    }
    
    @FXML
    private void handleButtonViewCemetery() throws IOException {
        this.userinterface.setPetCemeteryScene();
    }
    
    /**
     * Updates the progress bars to show current progress.
     * Gets the value of Pet's energy, happiness, health and hygiene stats and 
     * sets the value for the right progress bar for user to see.
     */
    public void setUpBars() {
        energyProgressBar.setProgress(this.userinterface.getPetCare().getPet().getEnergy().getValue() / 100);
        happinessProgressBar.setProgress(this.userinterface.getPetCare().getPet().getHappiness().getValue() / 100);
        healthProgressBar.setProgress(this.userinterface.getPetCare().getPet().getHealth().getValue() / 100);
        hygieneProgressBar.setProgress(this.userinterface.getPetCare().getPet().getHygiene().getValue() / 100);
    }
    
    /**
     * Sets Pet's name in the label.
     */
    public void setUpLabel() {
        this.petName.setText(this.userinterface.getPetCare().getPet().getName());
    }
    
    @FXML
    public void showTooltip() {
        this.petInfo.setText(this.userinterface.getPetCare().getPet().toString());
    }
    
    public Canvas getCanvas() {
        return this.gameCanvas;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }
       
    
}
