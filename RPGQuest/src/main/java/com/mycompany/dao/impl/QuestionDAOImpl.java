/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mycompany.dao.QuestionDAO;
import com.mycompany.model.Question;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oscar
 */
public class QuestionDAOImpl implements QuestionDAO {

    private final List<Question> questions;
    private final Gson gson;

    public QuestionDAOImpl() {
        questions = new ArrayList<>();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public void loadQuestionsFromJson(String filePath) {
        try (Reader reader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8)) {
            // Se espera que el JSON contenga un arreglo de preguntas.
            Type questionListType = new TypeToken<List<Question>>() {
            }.getType();
            List<Question> loadedQuestions = gson.fromJson(reader, questionListType);
            questions.addAll(loadedQuestions);
        } catch (Exception e) {
        }
    }

}
