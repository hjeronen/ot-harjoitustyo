
package tamagotchi.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

/**
 * GameOverScene FXML Controller class.
 *
 * @author Heli
 */
public class GameOverSceneController implements Initializable {

    private GUI userinterface;
    private GameRenderer renderer;
    
    /**
     * Anchor pane holding other ui-elements.
     */
    @FXML
    public AnchorPane gameAnchor;
    
    /**
     * Canvas for drawing 'GameOver' -picture.
     */
    @FXML
    public Canvas gameCanvas;
    
    public void setApplication(GUI ui) {
        this.userinterface = ui;
    }
    
    /**
     * Start new game.
     * Calls the GUI to set up new PetCare and to switch 
     * to NewGameScene.
     * 
     * @throws Exception 
     */
    @FXML
    private void handleButtonActionRestart() throws Exception {
        this.userinterface.setNewGameScene();
    }
    
    /**
     * Set up renderer.
     * Creates a GameRenderer and uses it to draw a picture on the canvas.
     */
    public void setUpRenderer() {
        this.renderer = new GameRenderer(this.gameCanvas);
        this.renderer.renderGhost();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
