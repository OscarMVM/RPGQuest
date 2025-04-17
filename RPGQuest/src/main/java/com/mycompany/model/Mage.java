/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Oscar
 */
public class Mage extends Player {

    private boolean canUseDecipher = true;
    private int correctAnswersForTeleport = 0;
    private boolean canUseShield = true;
    private int correctAnswersForConcentration = 0;

    public Mage(String name) {
        super(name, "Mago");
        super.setMaxHealth(80);
        super.setHealth(80);
    }

    @Override
    public void correctAnswer() {
        correctAnswersForTeleport++;
        correctAnswersForConcentration++;
        canUseDecipher = true;
        canUseShield = true;
        increaseScore(10);
    }

    public void wrongAnswer() {
        decreaseHealth(20); // Ejemplo: El mago pierde 15 de salud
    }

    @Override
    public String useAbility(Question currentQuestion) {
        if (canUseDecipher) {
            currentQuestion.removeIncorrectOptions(2); // Implementa este método en Question
            canUseDecipher = false;
            return "El Mago usó Descifrar Enigmas, ¡dos opciones incorrectas han desaparecido!";
        } else if (correctAnswersForTeleport >= 3) {
            // Lógica para saltar la pregunta se manejaría en GameController
            correctAnswersForTeleport = 0;
            return "El Mago usó Teletransporte Mental y saltó la pregunta.";
        } else if (canUseShield) {
            // Lógica para activar el escudo se manejaría en GameController
            canUseShield = false;
            return "El Mago activó Escudo de Intelecto.";
        } else if (correctAnswersForConcentration >= 2) {
            // Lógica para aumentar el tiempo se manejaría en GameController
            correctAnswersForConcentration = 0;
            return "El Mago usó Conocimiento Concentrado, ¡tiempo extra para la siguiente pregunta!";
        }
        return "El Mago no puede usar ninguna habilidad en este momento.";
    }
}
