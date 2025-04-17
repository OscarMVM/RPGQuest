/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author Oscar
 */
public class Archer extends Player {

    private boolean canUseEagleEye = true;
    private boolean canUseQuickShot = true;
    private int correctAnswersForDistraction = 0;
    private boolean canUsePreciseAim = true;
    private boolean nextQuestionDifferentTopic = false;

    public Archer(String name) {
        super(name, "Arquero");
    }

    public void correctAnswer() {
        System.out.println("Arquero: ¡Disparo certero!");
        // Bono base.
        increaseScore(10);
        // Se aplica la mecánica de tiro crítico.
        if (Math.random() < 0.25) {  // 25% de probabilidad.
            System.out.println("Arquero: ¡Tiro crítico! Bono adicional otorgado.");
            increaseScore(5);
        }
    }

    public void wrongAnswer() {
        decreaseHealth(10);
    }

    @Override
    public String useAbility(Question currentQuestion) {
        if (canUseEagleEye) {
            // Lógica para mostrar una pista sutil
            canUseEagleEye = false;
            return "El Arquero usó Ojo de Halcón. ¡Busca una pista!";
        } else if (canUseQuickShot) {
            // Lógica para reducir el tiempo límite
            canUseQuickShot = false;
            return "El Arquero usó Disparo Rápido. ¡Responde rápido para obtener un bono!";
        } else if (correctAnswersForDistraction >= 3) {
            correctAnswersForDistraction = 0;
            // Lógica para eliminar una opción basada en la historia del jugador
            return "El Arquero usó Flecha de Distracción.";
        } else if (canUsePreciseAim) {
            canUsePreciseAim = false;
            grantSecondChance();
            // Lógica para permitir una segunda oportunidad
            return "El Arquero usó Apuntar con Precisión. ¡Tendrás una segunda oportunidad si fallas!";
        } else if (nextQuestionDifferentTopic) {
            nextQuestionDifferentTopic = false;
            return "El Arquero usó Trampa Mental. La siguiente pregunta será de un tema diferente.";
        }
        return "El Arquero no puede usar ninguna habilidad en este momento.";
    }
}
