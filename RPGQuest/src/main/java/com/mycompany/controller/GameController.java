/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.factory.PlayerFactory;
import com.mycompany.listener.ClassPanelListener;
import com.mycompany.listener.GamePanelListener;
import com.mycompany.listener.WinPanelListener;
import com.mycompany.mediator.GameMediator;
import com.mycompany.model.Player;
import com.mycompany.model.Question;
import com.mycompany.model.QuestionRecord;
import com.mycompany.util.AudioPlayer;
import com.mycompany.view.ClassMenuPanel;
import com.mycompany.view.GamePanel;
import com.mycompany.view.WinPanel;
import java.util.List;
import javax.swing.Timer;

/**
 *
 * @author Oscar
 */
public class GameController implements GamePanelListener, ClassPanelListener, WinPanelListener {

    private QuestionRecord questionRecord;
    private final GamePanel gamePanel;
    private final WinPanel winPanel;
    private final ClassMenuPanel classMenuPanel;
    private final GameMediator gameMediator;

    private Question currentQuestion;
    private Player player;
    private boolean gameOver;

    // Timer para la cuenta regresiva de cada pregunta
    private Timer timer;
    private final int TIME_LIMIT_SECONDS = 30; // Tiempo límite en segundos
    private int timeRemaining;

    // Contador para la cantidad de preguntas    
    private static final int MAX_QUESTIONS = 15;
    private int answeredCount = 0;

    public GameController(GamePanel gamePanel, ClassMenuPanel classMenuPanel, WinPanel winPanel, GameMediator gameMediator) {
        this.gamePanel = gamePanel;
        this.classMenuPanel = classMenuPanel;
        this.winPanel = winPanel;
        this.gameMediator = gameMediator;
        this.registerListeners();
        questionRecord = new QuestionRecord();
    }

    private void registerListeners() {
        this.gamePanel.addListener(this);
        this.classMenuPanel.addListener(this);
        this.winPanel.addListener(this);
    }

    private void updatePlayerInfo() {
        gamePanel.setPlayerInfoText(String.format(
                "Clase: %s   |   Puntuación: %d   |   Salud: %d/%d",
                player.getCharacterClass(),
                player.getScore(),
                player.getHealth(),
                player.getMaxHealth()));
    }

    private void finishGame(String message) {
        gameOver = true;
        stopTimer();
        gamePanel.showMessage(message);
        gamePanel.disableAllAnswerButtons();
        gamePanel.getAbilityButton().setEnabled(false);
    }

    private void loadNextQuestion() {
        if (gameOver) {
            return;
        }

        if (player.getHealth() <= 0) {
            updatePlayerInfo();
            finishGame("¡Te has quedado sin salud! Fin del juego.");
            return;
        }

        if (answeredCount >= MAX_QUESTIONS) {
            gameMediator.showWinPanel();
            return;
        }
//        System.out.println(questionRecord.toString());

        stopTimer();
        currentQuestion = questionRecord.getRandomQuestion();
        if (currentQuestion == null) {
            finishGame("No hay más preguntas disponibles.");
            return;
        }

        renderQuestion();
        startTimer();

    }

    private void renderQuestion() {
        gamePanel.setQuestionNumberText(answeredCount + 1, MAX_QUESTIONS);
        gamePanel.setQuestionText(currentQuestion.getQuestionText());

        // Deshabilitar y limpiar todos los botones antes de configurar las nuevas opciones
        for (int i = 0; i < 4; i++) {
            gamePanel.setAnswerText(i, "");
            gamePanel.enableAnswerButton(i, false);
        }

        // Configurar las opciones disponibles
        List<String> options = currentQuestion.getOptions();
        for (int i = 0; i < options.size(); i++) {
            gamePanel.setAnswerText(i, options.get(i));
            gamePanel.enableAnswerButton(i, true);
        }

        gamePanel.getAbilityButton().setEnabled(true);
    }

    private void startTimer() {
        timeRemaining = TIME_LIMIT_SECONDS;
        gamePanel.setTimerText("Tiempo: " + timeRemaining + " seg");

        timer = new Timer(1000, e -> {
            if (gameOver) {
                stopTimer();
                return;
            }

            timeRemaining--;
            gamePanel.setTimerText("Tiempo: " + timeRemaining + " seg");

            if (timeRemaining <= 0) {
                stopTimer();
                gamePanel.showMessage("Tiempo agotado. Fallaste la pregunta!");
                handleWrongAnswer();
                loadNextQuestion();
            }
            // Habilitar el botón de habilidad al inicio de cada pregunta
            gamePanel.getAbilityButton().setEnabled(true);
        });
        timer.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void handleWrongAnswer() {
        player.wrongAnswer();
        updatePlayerInfo();
    }

    private void handleCorrectAnswer() {
        player.correctAnswer();
        updatePlayerInfo();
    }

    @Override
    public void onAnswerSelected(int index) {
        if (gameOver || currentQuestion == null) {
            return;
        }
        stopTimer();
        boolean correct = currentQuestion.isCorrect(index);

        // Segundo intento para el arquero si está disponible
        if (!correct && player.canUseSecondChance()) {
            String msg = player.useSecondChanceMessage();
            gamePanel.showMessage(msg);
            player.consumeSecondChance();
            gamePanel.enableAllAnswerButtons();
            return;
        }

        processAnswer(correct);
        loadNextQuestion();
    }

    private void processAnswer(boolean correct) {
        answeredCount++;
        if (correct) {
            gamePanel.showMessage("¡Correcto!");
            handleCorrectAnswer();
        } else {
            gamePanel.showMessage("Respuesta incorrecta.");
            handleWrongAnswer();
        }
        player.applyClassEffects(currentQuestion, correct);
        updatePlayerInfo();

        if (player.getHealth() <= 0) {
            finishGame("¡Te has quedado sin salud! Fin del juego.");
        }
    }

    @Override
    public void onAbilityButtonClicked() {
        if (gameOver || currentQuestion == null) {
            return;
        }

        String message = player.useAbility(currentQuestion);
        gamePanel.showMessage(message);
        gamePanel.logToConsole(message);
        updatePlayerInfo();
        gamePanel.getAbilityButton().setEnabled(false);

        // Actualiza opciones si habilidad las modifica
        if (message.contains("Descifrar Enigmas")) {
            renderQuestion();
        } // Teletransporte mental: salta pregunta
        else if (message.contains("Teletransporte Mental")) {
            loadNextQuestion();
            return;
        } // Conocimiento Concentrado: añade tiempo
        else if (message.contains("Conocimiento Concentrado")) {
            timeRemaining += 15;
            gamePanel.setTimerText("Tiempo: " + timeRemaining + " seg");
        }
        // Escudo de Intelecto y Postura Defensiva se manejan en applyClassEffects post-respuesta

        // Retomar temporizador si no saltó pregunta
        timer.start();
    }

    @Override
    public void onPlayerClassSelected(String playerClass) {
        player = PlayerFactory.createPlayer(playerClass);
        gameMediator.showGamePanel();
        initializeGame();
    }

    @Override
    public void onBackButtonClicked() {
        stopTimer();
        gameMediator.showMainMenuPanel();
    }

    private void initializeGame() {
        gameOver = false;
        answeredCount = 0;
        questionRecord = new QuestionRecord();
        gamePanel.clear();
        updatePlayerInfo();
        loadNextQuestion();
    }

    @Override
    public void onMouseEntered() {
        AudioPlayer.play("src/main/resources/audio/burbuja.wav");
    }
}
