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
    int hp, hpmax, hpregen;
    int mana, manamax, manaregen;
    int damage;
    int level;
    int exp;
    int expnext;
    int element;
    String[] elementname = {"Non-elemental", "Fire", "Water", "Earth", "Wind"};
    // int money;
    double acc;
    double dodge;
    boolean alive;
    Random rng;
    
    // INITIALIZE PLAYER CHARACTER
    public Character(String nm, int inhp, int dmg, double accu, double ddg, int el) {
        name = nm;
        hpmax = inhp;
        hp = hpmax;
        damage = dmg;
        acc = accu;
        dodge = ddg;
        alive = true;
        rng = new Random();
        element = el;
        level = 1;
        exp = 0;
        expnext = 100;
    }
    
    // CREATE A NEW ENEMY
    public Character(String nm, int inhp, int dmg, double accu, double ddg, int el, int lv) {
        name = nm;
        hpmax = inhp;
        hp = hpmax;
        damage = dmg;
        acc = accu;
        dodge = ddg;
        alive = true;
        rng = new Random();
        element = el;
        level = lv;
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
        System.out.println(attacker.name + " dealt " + attacker.damage + " damage to " + defender.name + ". " + defender.name + "'s HP: " + defender.hp + "/" + defender.hpmax);
        if (defender.hp <= 0) {
            defender.Dead(attacker, defender);
        }
    }
    
    public void Miss(Character attacker, Character defender) {
        System.out.println(attacker.name + "'s attack missed. ");
    }
    
    public void Dodge(Character attacker, Character defender) {
        System.out.println(defender.name + " has dodged " + attacker.name + "'s attack.");
    }
    
    public void StartTurn(Character target) {
        if (target.hpregen < 0) {
            Wound(target);
        }
    }
    
    public void EndTurn(Character target) {
        if (target.hpregen > 0) {
            Heal(target);
        }
    }
    
    public void Heal(Character target) {
        if (target.hp + target.hpregen > target.hpmax) {
            target.hp = target.hpmax;
        } else {
            target.hp += target.hpregen;
        }
        System.out.println(target.name + " has healed " + target.hpregen + " HP. " + target.name + "'s HP: " + target.hp + "/" + target.hpmax);
    }
    
    public void Wound(Character target) {
        target.hp += target.hpregen;
        System.out.println(target.name + " has suffered " + target.hpregen + " damage. " + target.name + "'s HP: " + target.hp + "/" + target.hpmax);
    }
    
    public void Dead(Character winner, Character loser) {
        loser.alive = false;
        System.out.println(loser.name + " has died.");
    }
    
    public void GainXP(Character target, Character input) {
        target.exp += (int) (input.level * 10 * 1.01);
    }
    
    public void LevelUP(Character target) {
        int temp = target.expnext;
        target.expnext = (int) (temp * 1.02 + 50);
    }
}
