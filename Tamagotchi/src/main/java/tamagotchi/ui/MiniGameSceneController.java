package tamagotchi.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * FXML Controller class
 *
 * @author Heli
 */
public class MiniGameSceneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private GUI userinterface;
    private GameRenderer renderer;
    private MiniGame minigame;
    
    
    @FXML
    public AnchorPane gameAnchor;
    
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
    
    @FXML
    public void setUpTextFieldNumber(int number) {
        this.number.setText("" + number);
    }
    
    @FXML
    public void setUpTextFieldAnswer(int answer) {
        this.answer.setText("" + answer);
    }
    
    @FXML
    public void setUpTextFieldGuessResult(String result) {
        this.guessResult.setText(result);
    }
    
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
    
    private void handleGuessCorrect() {
        this.guessResult.setText("Correct!");
        this.renderer.renderHappy();
    }
    
    private void handleGuessWrong() {
        this.guessResult.setText("Wrong!");
        this.renderer.renderAngry();
    }
    
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
    
    @FXML
    private void handleButtonActionBack() throws Exception {
        this.minigame.resetGame();
        this.userinterface.setGameScene();
    }
    
    public Canvas getCanvas() {
        return this.gameCanvas;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.renderer = new GameRenderer(this.gameCanvas);
        this.renderer.renderSprite();
        this.minigame = new MiniGame();
        this.minigame.play();
        setUpTextFieldNumber(this.minigame.getNumber());
    }    
    
}
