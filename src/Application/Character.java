/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.util.Random;
/**
 *
 * @author Edho
 */
public class Character {
    String name;
    int hp, maxhp, hpregen;
    // int mana, manaregen;
    int damage;
    int level;
    int exp;
    int expnext;
    int element;
    int money;
    double acc;
    double dodge;
    boolean alive;
    Random rng;
    
    public Character(String nm, int inhp, int dmg, double accu, double ddg) {
        name = nm;
        maxhp = inhp;
        hp = maxhp;
        damage = dmg;
        acc = accu;
        dodge = ddg;
        alive = true;
        rng = new Random();
    }
    
    public void Attack(Character attacker, Character defender) {
        double hitseed = rng.nextDouble();
        
        if (hitseed < attacker.acc) {
            if (hitseed > defender.dodge) {
                defender.Hit(attacker, defender);
            } else {
                defender.Dodge(attacker, defender);
            }
        } else {
            defender.Miss(attacker, defender);
        }
    }
    
    public void Hit(Character attacker, Character defender) {
        if (defender.hp > attacker.damage) {
            defender.hp -= attacker.damage;
        } else {
            defender.hp = 0;
        }
        System.out.println(attacker.name + " dealt " + attacker.damage + " damage to " + defender.name + ". " + defender.name + "'s HP: " + defender.hp + "/" + defender.maxhp);
        if (defender.hp <= 0) {
            defender.Dead(defender);
        } else if (defender.hpregen != 0) {
            defender.Heal(defender);
        }
    }
    
    public void Miss(Character attacker, Character defender) {
        System.out.println(attacker.name + "'s attack missed. ");
    }
    
    public void Dodge(Character attacker, Character defender) {
        System.out.println(defender.name + " has dodged " + attacker.name + "'s attack.");
    }
    
    public void Heal(Character character) {
        character.hp += character.hpregen;
        System.out.println(character.name + " has healed " + character.hpregen + " HP." + character.name + "'s HP: " + character.hp + "/" + character.maxhp);
    }
    
    public void Dead(Character character) {
        character.alive = false;
        System.out.println(character.name + " has died.");
    }
}
