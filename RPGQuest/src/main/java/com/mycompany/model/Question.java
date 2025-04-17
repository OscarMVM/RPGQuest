/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Oscar
 */
public class Question {

    private final String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    @Override
    public String toString() {
        return "Question{" + "questionText=" + questionText + ", options=" + options + ", correctOptionIndex=" + correctOptionIndex + '}';
    }
    
    
    public boolean isCorrect(int index){
        return correctOptionIndex == index;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public List<String> getIncorrectOptions() {
        List<String> incorrectOptions = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            if (i != correctOptionIndex) {
                incorrectOptions.add(options.get(i));
            }
        }
        return incorrectOptions;
    }

    public void removeOption(String option) {
        int index = options.indexOf(option);

        // Verifica si la opción existe en la lista
        if (index == -1) {
            System.out.println("La opción \"" + option + "\" no existe.");
            return;
        }

        // Evita eliminar la opción correcta
        if (index == correctOptionIndex) {
            throw new IllegalArgumentException("No se puede remover la opción correcta.");
        }

        // Remover la opción
        options.remove(index);

        // Ajustar el índice de la opción correcta si es necesario
        if (index < correctOptionIndex) {
            correctOptionIndex--;
        }
    }

    public void shuffleOptions() {
        Collections.shuffle(this.options);
        // Necesitas actualizar el correctOptionIndex después de barajar
        // Encuentra el nuevo índice de la respuesta correcta
        String correctAnswer = this.options.get(this.correctOptionIndex);
        this.correctOptionIndex = this.options.indexOf(correctAnswer);
    }

    public void removeIncorrectOptions(int count) {
        if (count <= 0 || options.size() <= 2) {
            return;
        }
        String correctAnswer = options.get(correctOptionIndex); // Guarda la respuesta correcta
        List<Integer> incorrectIndices = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            if (i != correctOptionIndex) {
                incorrectIndices.add(i);
            }
        }
        Collections.shuffle(incorrectIndices);
        int removedCount = 0;
        List<String> newOptions = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            if (i == correctOptionIndex || removedCount >= count || !incorrectIndices.contains(i)) {
                newOptions.add(options.get(i));
            } else if (incorrectIndices.contains(i)) {
                removedCount++;
            }
        }
        this.options = newOptions;
        this.correctOptionIndex = newOptions.indexOf(correctAnswer); // Busca el índice de la respuesta correcta guardada
    }
}
