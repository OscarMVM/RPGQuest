/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.rpgquest;

import com.mycompany.factory.PanelFactory;
import com.mycompany.mediator.GameMediator;
import com.mycompany.view.MainFrame;

/**
 *
 * @author Oscar
 */
public class RPGQuest {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        GameMediator mediator = new GameMediator(mainFrame);
        mediator.showMainMenuPanel();
        mainFrame.setVisible(true);
    }
}
