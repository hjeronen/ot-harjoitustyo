/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamagotchi.ui;


import java.util.Scanner;
import tamagotchi.logic.PetCare;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author Heli
 */
//public class TextUi {
//    private Scanner s;
//    private PetCare petCare;
//    private long lastCheck;
//
//    
//    public TextUi(PetCare petCare) {
//        this.s = new Scanner(System.in);
//        this.petCare = petCare;
//        this.lastCheck = java.lang.System.currentTimeMillis();
//
//    }
//    
//    public PetCare getPetCare() {
//        return this.petCare;
//    }
//    
//    public boolean handleInput(int input) {
//        if (input == 1) {
//            this.petCare.feedPet();
//            System.out.println("Your pet is eating!");
//            return true;
//        } else if (input == 2) {
//            System.out.println(this.petCare.getPet());
//            return true;
//        } else if (input == 3) {
//            this.petCare.healPet();
//            System.out.println("Your pet looks healthier!");
//            return true;
//        } else {
//            return false;
//        }
//    }
//    
//    public void update() {
//        long now = java.lang.System.currentTimeMillis();
//        if (now - this.lastCheck >= 5000) {
//            this.petCare.updateEnergy();
//            this.lastCheck = now;
//        }
//    }
//    
//    public void startNewGame() {
//        System.out.println("Do you wish to name your pet (or keep default name)? y/n ");
//        String answer = s.nextLine().toLowerCase();
//        if (answer.equals("y")) {
//            System.out.println("Give your pet a name: ");
//            String name = s.nextLine();
//            this.petCare.renamePet(name);
//            System.out.println("Your pet's name is now " + name);
//        }
//    }
//    
//    // Primitive game loop
//    public void run() {
//        System.out.println("Do something! 1 = feed pet, 2 = check your pet, 3 = Give your pet medicine, 4 = quit");
//        while (true) {            
//            update();
//            System.out.println("Give command: ");
//            
//            if (!handleInput(Integer.valueOf(s.nextLine()))) {
//                break;
//            }
//            if (!this.petCare.petIsAlive()) {
//                System.out.println("Your pet is dead!");
//                break;
//            }
//
//        }
//    }
//    
//
//
//}




