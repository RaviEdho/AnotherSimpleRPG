/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author Edho
 */
public class Test {    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        Random rng;
        
        System.out.print("Hello! Input your name: ");
        String name = in.nextLine();
        Character player = new Character(name, RI(900, 1200), RI(150, 250), RD(0.8, 0.97), RD(0.03, 0.06));
        player.hpregen = 100;
        
        System.out.println("===== Character =====");
        System.out.println(player.name);
        System.out.println("Health: " + player.hp + "/" + player.maxhp);
        System.out.println("Damage: " + player.damage);
        System.out.println("Hitrate/Dodge: " + df.format(player.acc*100) + "%/" + df.format(player.dodge*100) + "%");
        
        for (int i = 0; i < 3; i++) {
            Character enemy = new Character("Enemy", RI(500, 600), RI(50, 60), RD(0.8, 0.97), RD(0.07, 0.1));
            System.out.println("\nA new " + enemy.name + " has appeared!");
            while (enemy.alive) {
                enemy.Attack(enemy, player);
                player.Attack(player, enemy);
            };
            enemy = null;
        }
    }
    
    static int RI(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }
    
    static double RD(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
