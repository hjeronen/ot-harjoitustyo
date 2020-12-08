/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heli
 */
public class MiniGameTest {
    
    MiniGame game;
    
    public MiniGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.game = new MiniGame();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {}
    
    @Test
    public void generateNumberChangesInitialNumberValue() {
        int number = this.game.getNumber();
        this.game.generateNumber();
        assertTrue(this.game.getNumber() != number);
    }
    
    @Test
    public void generateAnswerChangesInitialAnswerValue() {
        int answer = this.game.getAnswer();
        this.game.generateAnswer();
        assertTrue(this.game.getAnswer() != answer);
    }
    
    @Test
    public void isHigherReturnsFalseIfPlayerGuessHigherAndAnswerIsLower() {
        this.game.setNumber(3);
        this.game.setAnswer(1);
        assertTrue(this.game.isHigher(true) == false);
    }
    
    @Test
    public void isHigherReturnsFalseIfPlayerGuessIsLowerAndAnswerIsHigher() {
        this.game.setNumber(3);
        this.game.setAnswer(5);
        assertTrue(this.game.isHigher(false) == false);
    }
    
    @Test
    public void isHigherReturnsTrueIfPlayerGuessIsHigherAndAnswerIsHigher() {
        this.game.setNumber(3);
        this.game.setAnswer(5);
        assertTrue(this.game.isHigher(true) == true);
    }
    
    @Test
    public void isHigherReturnsTrueIfPlayerGuesIsLowerAndAnswerIsLower() {
        this.game.setNumber(3);
        this.game.setAnswer(1);
        assertTrue(this.game.isHigher(false) == true);
    }
    
    @Test
    public void playIncreasesRoundByOne() {
        int original = this.game.getRound();
        this.game.play();
        assertTrue(this.game.getRound() - original == 1);
    }
    
    @Test
    public void playChangesTheValueOfVariableAnswerGivenToFalse() {
        this.game.setAnswerGiven(true);
        this.game.play();
        assertTrue(this.game.getAnswerGiven() == false);
    }
    
    @Test
    public void playGeneratesNewNumber() {
        int old = this.game.getNumber();
        this.game.play();
        assertTrue(this.game.getNumber() != old);
    }
    
    @Test
    public void playGeneratesNewAnswer() {
        int old = this.game.getAnswer();
        this.game.play();
        assertTrue(this.game.getAnswer() != old);
    }
    
    @Test
    public void handleGuessChangesAnswerGivenToTrueIfItIsFalse() {
        this.game.setAnswerGiven(false);
        this.game.setNumber(3);
        this.game.setAnswer(5);
        this.game.handleGuess(true);
        assertTrue(this.game.getAnswerGiven() == true);
    }
    
    @Test
    public void handleGuessIncreasesScoreIfGuessCorrect() {
        this.game.setNumber(3);
        this.game.setAnswer(5);
        int oldScore = this.game.getScore();
        this.game.handleGuess(true);
        assertTrue(this.game.getScore() - oldScore == 1);
    }
    
    @Test
    public void handleGuessDoesNotIncreaseScoreIfGuessWrong() {
        this.game.setNumber(3);
        this.game.setAnswer(5);
        int oldScore = this.game.getScore();
        this.game.handleGuess(false);
        assertTrue(this.game.getScore() - oldScore == 0);
    }
    
    @Test
    public void handleGuessDoesNotIncreaseScoreIfGuessCorrectButAnswerGivenIsTrue() {
        this.game.setNumber(3);
        this.game.setAnswer(5);
        int oldScore = this.game.getScore();
        this.game.setAnswerGiven(true);
        this.game.handleGuess(true);
        assertTrue(this.game.getScore() - oldScore == 0);
    }
    
    @Test
    public void resetGameChangesScoreToZero() {
        this.game.setScore(3);
        this.game.resetGame();
        assertTrue(this.game.getScore() == 0);
    }
    
    @Test
    public void resetGameChangesRoundToZero() {
        this.game.setRound(3);
        this.game.resetGame();
        assertTrue(this.game.getRound() == 0);
    }
    
    @Test
    public void resetGameChangesAnswerGivenToFalse() {
        this.game.setAnswerGiven(true);
        this.game.resetGame();
        assertTrue(this.game.getAnswerGiven() == false);
    }
}
