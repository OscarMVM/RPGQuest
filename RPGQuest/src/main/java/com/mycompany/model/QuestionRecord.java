/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.mycompany.dao.QuestionDAO;
import com.mycompany.dao.impl.QuestionDAOImpl;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Oscar
 */
public class QuestionRecord {

    private final QuestionDAOImpl questionDAOImpl;
    private List<Question> questions;
    private final Random random;

    public QuestionRecord() {
        questionDAOImpl = new QuestionDAOImpl();
        random = new Random();
        loadQuestions();
    }

    private void loadQuestions() {
        questionDAOImpl.loadQuestionsFromJson("./src/main/resources/jsons/questions.json");
        questions = questionDAOImpl.getAllQuestions();
    }

    public void reset() {
        loadQuestions();
    }

    @Override
    public String toString() {
        String data = "";
        int i = 1;
        for (Question question : questions) {
            data += i + " - " + question.toString() + "\n";
            i++;
        }
        return data;
    }
    
    
    public Question getRandomQuestion() {
        if (questions == null || questions.isEmpty()) {
            return null;
        }
        int index = random.nextInt(questions.size());
        return questions.remove(index);
    }
}
