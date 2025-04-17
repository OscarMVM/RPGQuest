/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.listener.MainMenuListener;
import com.mycompany.mediator.GameMediator;
import com.mycompany.util.AudioPlayer;
import com.mycompany.view.MainMenuPanel;

/**
 *
 * @author Oscar
 */
public class MainController implements MainMenuListener {

    private final MainMenuPanel mainMenuPanel;
    private final GameMediator gameMediator;

    public MainController(MainMenuPanel mainMenuPanel, GameMediator gameMediator) {
        this.mainMenuPanel = mainMenuPanel;
        this.gameMediator = gameMediator;
        this.mainMenuPanel.addListener(this);
    }

    @Override
    public void onPlayButtonClicked() {
        gameMediator.showClassSelectionPanel();
    }

    @Override
    public void onExitButtonClicked() {
        System.exit(0);
    }

    @Override
    public void onMouseEntered() {
       AudioPlayer.play("src/main/resources/audio/burbuja.wav");
    }
}
