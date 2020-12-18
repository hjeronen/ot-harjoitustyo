
package tamagotchi.logic;

import java.util.Random;

/**
 * Game logic for the mini game.
 *
 * @author Heli
 */
public class MiniGame {
    int number;
    int answer;
    Random generator;
    int round;
    int score;
    boolean answerGiven;
    
    public MiniGame() {
        this.generator = new Random();
        this.number = 0;
        this.answer = 0;
        this.round = 0;
        this.score = 0;
        this.answerGiven = false;
    }
    
    /**
     * Generates a random number for baseline.
     * If generated number is zero, a new one will be generated until
     * it is other than zero. Generated number will be placed on the
     * variable this.number and returned.
     * 
     * @return this.number  the number generated
     */
    public int generateNumber() {
        this.number = this.generator.nextInt(10);
        while (this.number == 0) {
            this.number = this.generator.nextInt(10);
        }
        return this.number;
    }
    
    /**
     * Generates a random number for answer.
     * If generated number is the same as the baseline number,
     * a new one will be generated until it is something different.
     * Generated number will be placed in the variable this.answer and
     * returned.
     * 
     * @return this.answer  the number generated
     */
    public int generateAnswer() {
        this.answer = this.generator.nextInt(11);
        while (this.answer == this.number) {
            this.answer = this.generator.nextInt(11);
        }
        return this.answer;
    }
    
    /**
     * Checks if user guessed correctly higher or lower.
     * Method compares if it is true or false that the answer is higher 
     * than the baseline number, and returns the result of the comparison.
     * 
     * @param userGuess true for higher, false for lower
     * @return boolean  true for correct guess, false for wrong
     */
    public boolean isHigher(boolean userGuess) {
        return (userGuess == (this.answer > this.number));
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(int number) {
        this.answer = number;
    }
    
    public int getRound() {
        return this.round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void setAnswerGiven(boolean value) {
        this.answerGiven = value;
    }
    
    public boolean getAnswerGiven() {
        return this.answerGiven;
    }
    
    /**
     * Plays a new round.
     * Increases the number of rounds by one and generates a number for the
     * baseline and the answer.
     */
    public void play() {
        this.round += 1;
        this.answerGiven = false;
        generateNumber();
        generateAnswer();
    }
    
    /**
     * Handles user's guess.
     * Checks if an answer has already been given on this round, and if not,
     * uses isHigher() method to determine if user's guess was correct. If
     * method returns true, score is increased by one.
     * 
     * @param guess user's guess for higher or lower
     * 
     * @see MiniGame#isHigher(boolean)
     * 
     * @return boolean  true if guess is right, false if wrong or if an answer 
     * has already been given this round.
     */
    public boolean handleGuess(boolean guess) {
        if (!this.answerGiven) {
            this.answerGiven = true;
            if (isHigher(guess)) {
                this.score++;
            }
            return isHigher(guess);
        }
        return false;
    }
    
    /**
     * Resets the Game.
     * Sets the values of this.round and this.score to 0.
     */
    public void resetGame() {
        this.round = 0;
        this.score = 0;
        this.answerGiven = false;
    }
}
