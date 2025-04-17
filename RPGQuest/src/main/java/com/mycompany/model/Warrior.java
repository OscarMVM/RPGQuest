/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Oscar
 */
public class Warrior extends Player {

    private boolean canUseDefensiveStance = true;
    private int correctAnswersForStun = 0;
    private boolean canUseProvoke = true;
    private boolean canUseDoublePoints = true; // Para Maestro de Armas
    private boolean secondWindAvailable = true; // Para Segundo Viento

    public Warrior(String name) {
        // Se pasa la clase "Guerrero" al constructor de Player.
        super(name, "Guerrero");
        super.setMaxHealth(150);
        super.setHealth(150);
    }

    @Override
    public void correctAnswer() {
        correctAnswersForStun++;
        canUseDefensiveStance = true; // Se recarga con cada respuesta correcta
        canUseProvoke = true; // Se recarga con cada respuesta correcta
        canUseDoublePoints = true;
        secondWindAvailable = true;
        increaseScore(10);
    }

    public void wrongAnswer() {
        decreaseHealth(15); // Ejemplo: El guerrero pierde 10 de salud
    }

    @Override
    public String useAbility(Question currentQuestion) {
        if (canUseDefensiveStance) {
            canUseDefensiveStance = false;
            activateDefensiveStance();
            return "El Guerrero adoptó una Postura Defensiva.";
        } else if (correctAnswersForStun >= 3) {
            correctAnswersForStun = 0;
            return "El Guerrero usó Golpe Aturdidor.";
        } else if (canUseDoublePoints) {
            canUseDoublePoints = false;
            return "El Guerrero se preparó con Maestro de Armas. La siguiente respuesta correcta dará doble puntuación.";
        } else if (secondWindAvailable) {
            secondWindAvailable = false;
            increaseHealth(20);
            return "El Guerrero usó Segundo Viento para recuperar fuerzas.";
        }
        return "El Guerrero no puede usar ninguna habilidad en este momento.";
    }
}
