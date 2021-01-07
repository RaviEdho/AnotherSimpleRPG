/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import static java.lang.Math.sqrt;

/**
 *
 * @author Edho
 */
public class NewClass {
    public static void main(String args[]) {
        int[] xp = new int[100];
        for (int i = 1; i <= 100; i++) {
            int exp = (int) (sqrt(i*4) * 10);
            if (i == 1) {
                xp[i-1] = 100;
                System.out.println("Level " + i + ": " + xp[i-1] + " | Per kill: " + exp);
                continue;
            }
            
            xp[i-1] = (int) (xp[i-2] * 1.02 + 50);
            System.out.println("Level " + i + ": " + xp[i-1] + " | Per kill: " + exp);
        }
    }
}
