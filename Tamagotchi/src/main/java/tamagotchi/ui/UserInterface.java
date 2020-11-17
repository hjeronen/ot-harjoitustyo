/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.ui;

/**
 *
 * @author Heli
 */
public interface UserInterface {

    boolean handleInput(int i);
    void update();
    void run();
    void startNewGame();
}
