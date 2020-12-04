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
        if (this.userinterface.getMiniGame().isHigher(true)) {
            this.guessResult.setText("Correct!");
            this.userinterface.getMiniGame().increaseScore();
        } else {
            this.guessResult.setText("Wrong!");
        }
        setUpTextFieldAnswer(this.userinterface.getMiniGame().getAnswer());
    }
    
    @FXML
    private void handleButtonActionGuessLower() throws Exception {
        if (this.userinterface.getMiniGame().isHigher(false)) {
            this.guessResult.setText("Correct!");
            this.userinterface.getMiniGame().increaseScore();
        } else {
            this.guessResult.setText("Wrong!");
        }
        setUpTextFieldAnswer(this.userinterface.getMiniGame().getAnswer());
    }
    
    @FXML
    private void handleButtonActionNext() throws Exception {
        if (this.userinterface.getMiniGame().getRound() == 5) {
            this.score.setText("Game Over! Your score: " + this.userinterface.getMiniGame().getScore() + "/5.");
            this.userinterface.getPetCare().play(this.userinterface.getMiniGame().getScore());
            this.userinterface.getMiniGame().setRound(0);
            this.userinterface.getMiniGame().setScore(0);
        } else {
            this.userinterface.setMiniGameScene();
        }
        
    }
    
    @FXML
    private void handleButtonActionBack() throws Exception {
        this.userinterface.getMiniGame().setRound(0);
        this.userinterface.getMiniGame().setScore(0);
        this.userinterface.setGameScene();
    }
    
    public Canvas getCanvas() {
        return this.gameCanvas;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
