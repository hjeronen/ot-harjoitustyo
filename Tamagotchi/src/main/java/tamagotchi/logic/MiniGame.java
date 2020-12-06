/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.logic;

import java.util.Random;

/**
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
    
    public int generateNumber() {
        this.number = this.generator.nextInt(10);
        while (this.number == 0) {
            this.number = this.generator.nextInt(10);
        }
        return this.number;
    }
    
    public int generateAnswer() {
        this.answer = this.generator.nextInt(11);
        while (this.answer == this.number) {
            this.answer = this.generator.nextInt(11);
        }
        return this.answer;
    }
    
    public boolean isHigher(boolean userGuess) {
        return (userGuess == (this.answer > this.number));
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public int getAnswer() {
        return this.answer;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public int getRound() {
        return this.round;
    }
    
    public void increaseScore() {
        this.score += 1;
    }
    
    public void setScore(int number) {
        this.score = number;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void play() {
        this.round += 1;
        generateNumber();
        generateAnswer();
    }
    
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
    
    public void setNewRound() {
        this.answerGiven = false;
    }
    
    public void resetGame() {
        this.round = 0;
        this.score = 0;
        this.answerGiven = false;
    }
}
