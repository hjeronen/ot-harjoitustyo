/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi;

import tamagotchi.ui.TextUi;
import tamagotchi.domain.PetCare;
/**
 *
 * @author Heli
 */
public class Main {
    public static void main(String[] args) {


        PetCare petCare = new PetCare();
        TextUi textUi = new TextUi(petCare);
   
        
        textUi.startNewGame();
        textUi.run();
    }
}
