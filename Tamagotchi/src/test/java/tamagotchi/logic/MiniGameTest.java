
package tamagotchi.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for MiniGame-class.
 * 
 * @author Heli
 */
public class MiniGameTest {
    
    MiniGame game;
    
    
    @Before
    public void setUp() {
        this.game = new MiniGame();
    }
    
    
    @Test
    public void generateNumberChangesNumberValue() {
        this.game.setNumber(0);
        this.game.generateNumber();
        assertTrue(this.game.getNumber() != 0);
    }
    
    @Test
    public void generateAnswerChangesAnswerValue() {
        this.game.setAnswer(11);
        this.game.generateAnswer();
        assertTrue(this.game.getAnswer() != 11);
    }
    
    @Test
    public void isHigherReturnsFalseIfPlayerGuessesHigherAndAnswerIsLower() {
        this.game.setNumber(3);
        this.game.setAnswer(1);
        assertTrue(this.game.isHigher(true) == false);
    }
    
    @Test
    public void isHigherReturnsFalseIfPlayerGuessesLowerAndAnswerIsHigher() {
        this.game.setNumber(3);
        this.game.setAnswer(5);
        assertTrue(this.game.isHigher(false) == false);
    }
    
    @Test
    public void isHigherReturnsTrueIfPlayerGuessesHigherAndAnswerIsHigher() {
        this.game.setNumber(3);
        this.game.setAnswer(5);
        assertTrue(this.game.isHigher(true) == true);
    }
    
    @Test
    public void isHigherReturnsTrueIfPlayerGuessesLowerAndAnswerIsLower() {
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
        this.game.setNumber(0);
        this.game.play();
        assertTrue(this.game.getNumber() != 0);
    }
    
    @Test
    public void playGeneratesNewAnswer() {
        this.game.setAnswer(11);
        this.game.play();
        assertTrue(this.game.getAnswer() != 11);
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
    public void handleGuessIncreasesScoreIfGuessIsCorrect() {
        this.game.setNumber(3);
        this.game.setAnswer(5);
        int oldScore = this.game.getScore();
        this.game.handleGuess(true);
        assertTrue(this.game.getScore() - oldScore == 1);
    }
    
    @Test
    public void handleGuessDoesNotIncreaseScoreIfGuessIsWrong() {
        this.game.setNumber(3);
        this.game.setAnswer(5);
        int oldScore = this.game.getScore();
        this.game.handleGuess(false);
        assertTrue(this.game.getScore() - oldScore == 0);
    }
    
    @Test
    public void handleGuessDoesNotIncreaseScoreIfGuessIsCorrectButAnswerGivenIsTrue() {
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
