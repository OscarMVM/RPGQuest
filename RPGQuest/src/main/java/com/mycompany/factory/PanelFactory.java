/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.factory;

import com.mycompany.controller.GameController;
import com.mycompany.controller.MainController;
import com.mycompany.mediator.GameMediator;
import com.mycompany.view.ClassMenuPanel;
import com.mycompany.view.GamePanel;
import com.mycompany.view.MainMenuPanel;
import com.mycompany.view.WinPanel;

/**
 *
 * @author Oscar
 */
public class PanelFactory {

    private final MainMenuPanel mainMenuPanel;
    private final GamePanel gamePanel;
    private final ClassMenuPanel classMenuPanel;
    private final WinPanel winPanel;

    public PanelFactory(GameMediator mediator) {
        mainMenuPanel = new MainMenuPanel();
        classMenuPanel = new ClassMenuPanel();
        gamePanel = new GamePanel();
        winPanel = new WinPanel();

        new GameController(gamePanel, classMenuPanel, winPanel, mediator);
        new MainController(mainMenuPanel, mediator);
    }

    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ClassMenuPanel getClassMenuPanel() {
        return classMenuPanel;
    }

    public WinPanel getWinPanel() {
        return winPanel;
    }

}
