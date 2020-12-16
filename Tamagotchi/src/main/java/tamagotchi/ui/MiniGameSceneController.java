package tamagotchi.ui;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tamagotchi.logic.MiniGame;

/**
 * MiniGameScene FXML Controller class.
 *
 * @author Heli
 */
public class MiniGameSceneController implements Initializable {
    
    private GUI userinterface;
    private GameRenderer renderer;
    private MiniGame minigame;
    
    /**
     * Anchor Pane holding all other ui-elements.
     */
    @FXML
    public AnchorPane gameAnchor;
    
    /**
     * Canvas for drawing sprite image.
     */
    @FXML
    public Canvas gameCanvas;
    
    @FXML
    private TextField number;
    
    @FXML
    private TextField answer;
    
    @FXML
    private TextField guessResult;
    
    @FXML
    private Label score;
    
    
    public void setApplication(GUI ui) {
        this.userinterface = ui;
    }
    
    /**
     * Creates a renderer for minigame to draw sprite images.
     */
    public void setUpRenderer() {
        this.renderer = new GameRenderer(this.gameCanvas);
        this.renderer.setSpriteImage(this.userinterface.getPetCare().getPet().getDevelopmentStage());
        this.renderer.renderSprite();
    }
    
    /**
     * Sets minigame's number in text field.
     * 
     * @param number    the baseline number from minigame
     */
    @FXML
    public void setUpTextFieldNumber(int number) {
        this.number.setText("" + number);
    }
    
    /**
     * Sets minigame's answer in text field.
     * 
     * @param answer    the correct answer from minigame
     */
    @FXML
    public void setUpTextFieldAnswer(int answer) {
        this.answer.setText("" + answer);
    }
    
    /**
     * Sets the result of user's guess in text field.
     * 
     * @param result    "correct"/"wrong"
     */
    @FXML
    public void setUpTextFieldGuessResult(String result) {
        this.guessResult.setText(result);
    }
    
    /**
     * Handles player's guess for higher.
     * Checks with MiniGame if a guess has already been given this round, 
     * then checks if player's guess was correct and shows the answer.
     * 
     * @throws Exception
     * @see tamagotchi.logic.MiniGame#handleGuess(boolean)
     */
    @FXML
    private void handleButtonActionGuessHigher() throws Exception {
        if (!this.minigame.getAnswerGiven()) {
            if (this.minigame.handleGuess(true)) {
                handleGuessCorrect();
            } else {
                handleGuessWrong();
            }
            setUpTextFieldAnswer(this.minigame.getAnswer());
        }
    }
    
    /**
     * Handles player's guess for lower.
     * Checks with MiniGame if a guess has already been given this round, 
     * then checks if player's guess was correct and shows the answer.
     * 
     * @throws Exception
     * @see tamagotchi.logic.MiniGame#handleGuess(boolean)
     */
    @FXML
    private void handleButtonActionGuessLower() throws Exception {
        if (!this.minigame.getAnswerGiven()) {
            if (this.minigame.handleGuess(false)) {
                handleGuessCorrect();
            } else {
                handleGuessWrong();
            }
            setUpTextFieldAnswer(this.minigame.getAnswer());
        }
    }
    
    /**
     * Handle correct guess.
     * When player's guess was correct, sets the text "Correct!" in text field 
     * this.guessResult and uses renderer to draw a happy sprite.
     */
    private void handleGuessCorrect() {
        this.guessResult.setText("Correct!");
        this.renderer.renderHappy();
    }
    
    /**
     * Handle wrong guess.
     * When player's guess was wrong, sets the text "Wrong!" in text field 
     * this.guessResult and uses renderer to draw an angry sprite.
     */
    private void handleGuessWrong() {
        this.guessResult.setText("Wrong!");
        this.renderer.renderAngry();
    }
    
    /**
     * Handle the pressing of button 'Next'.
     * If the minigame has been played for five rounds shows the player their 
     * score, updates Pet's happiness according to the gained points and resets 
     * the minigame. Otherwise plays a new round and resets the text fields.
     * 
     * @throws Exception 
     * @see tamagotchi.logic.MiniGame#play()
     * @see tamagotchi.logic.MiniGame#resetGame()
     */
    @FXML
    private void handleButtonActionNext() throws Exception {
        if (this.minigame.getRound() == 5) {
            this.score.setText("Game Over! Your score: " + this.minigame.getScore() + "/5.");
            this.userinterface.getPetCare().play(this.minigame.getScore());
            this.minigame.resetGame();
            this.renderer.renderSprite();
        } else {
            this.minigame.play();
            this.renderer.renderSprite();
            setUpTextFieldNumber(this.minigame.getNumber());
            this.answer.setText("");
            this.guessResult.setText("");
            this.score.setText("");
        }
    }
    
    /**
     * Switch back to MainGameScene.
     * Pressing button 'Back to Game' resets the minigame and switches 
     * the game view back to MainGameScene.
     * @throws Exception 
     */
    @FXML
    private void handleButtonActionBack() throws Exception {
        this.minigame.resetGame();
        this.userinterface.setMainGameScene();
    }
    
    public Canvas getCanvas() {
        return this.gameCanvas;
    }
    
    /**
     * Initializes the scene controller.
     * Creates a new instance of MiniGame and sets up the first round, setting 
     * the number on text field this.number.
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.minigame = new MiniGame();
        this.minigame.play();
        setUpTextFieldNumber(this.minigame.getNumber());
    }    
    
}
