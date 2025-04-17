/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Oscar
 */
public abstract class Player {

    private String name;
    private String characterClass;
    private int score;
    private int health;
    private int maxHealth;

    // Flags de habilidad activa y efectos de clase
    private boolean shieldActive;
    private boolean defensiveStanceActive;
    private boolean secondChanceAvailable;
    private boolean secondWindActive;

    public Player(String name, String characterClass) {
        this.name = name;
        this.characterClass = characterClass;
        this.score = 0;
        this.maxHealth = 100;
        this.health = maxHealth;
    }

    // --- Getters y setters ---
    public String getCharacterClass() {
        return characterClass;
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    protected void increaseScore(int pts) {
        score += pts;
    }

    protected void increaseHealth(int points) {
        this.health = Math.min(this.maxHealth, this.health + points); // Aumentar salud sin exceder el máximo
    }
    
    protected void decreaseHealth(int pts) {
        health = Math.max(0, health - pts);
        if (health == 0) {
            onDefeat();
        }
    }

    protected void onDefeat() {
        // Lógica de derrota (registro, etc.)
        System.out.println(name + " ha sido derrotado.");
    }

    // --- HABILIDADES: activación desde useAbility(...) en subclases ---
    protected void activateShield() {
        shieldActive = true;
    }

    protected void activateDefensiveStance() {
        defensiveStanceActive = true;
    }

    protected void grantSecondChance() {
        secondChanceAvailable = true;
    }

    protected void grantSecondWind() {
        secondWindActive = true;
    }

    public boolean canUseSecondChance() {
        return secondChanceAvailable;
    }

    public void consumeSecondChance() {
        secondChanceAvailable = false;
    }

    public String useSecondChanceMessage() {
        return "Respuesta incorrecta. ¡Apuntar con Precisión te da otra oportunidad!";
    }

    public void applyClassEffects(Question question, boolean correct) {
        if (this instanceof Mage) {
            // Protección del escudo
            if (!correct && shieldActive) {
                // Cancela daño
                shieldActive = false;
                System.out.println("Escudo de Intelecto protegido el fallo.");
                return;
            }
        }

        else if (this instanceof Warrior) {
            Warrior w = (Warrior) this;
            if (!correct && defensiveStanceActive) {
                // Daño reducido y bonus de postura
                defensiveStanceActive = false;
                increaseHealth(10);
                increaseScore(5);
                System.out.println("Postura Defensiva redujo daño y otorgó 5 puntos.");
                return;
            }
            if (correct && secondWindActive) {
                // Bonus por Segundo Viento
                secondWindActive = false;
                increaseScore(10);
                System.out.println("Segundo Viento otorgó 10 puntos.");
            }
        }

        else if (this instanceof Archer) {
            // El controlador ya maneja el segundo intento; aquí no se quita vida si secondChanceAvailable
            if (!correct && secondChanceAvailable) {
                // Se consume en el controlador
                return;
            }
        }
    }

    // Métodos abstractos que implementan cada subclase:
    public abstract void correctAnswer();

    public abstract void wrongAnswer();

    public abstract String useAbility(Question current);
}
