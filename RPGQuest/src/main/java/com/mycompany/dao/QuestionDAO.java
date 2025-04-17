/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Question;
import java.util.List;

/**
 *
 * @author Oscar
 */
public interface QuestionDAO {
    List<Question> getAllQuestions();
    void loadQuestionsFromJson(String filePath);
}
