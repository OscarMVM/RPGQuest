/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mediator;

import com.mycompany.controller.GameController;
import com.mycompany.factory.PanelFactory;
import com.mycompany.util.AudioPlayer;
import com.mycompany.view.GamePanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Oscar
 */
public class GameMediator {

    private final JFrame mainFrame;
    private final PanelFactory panelFactory;
    private final AudioPlayer audioPlayer;

    public GameMediator(JFrame frame) {
        this.mainFrame = frame;
        this.panelFactory = new PanelFactory(this);
        this.audioPlayer = new AudioPlayer();  // Inicializamos AudioPlayer
    }

    private void changePanel(JPanel newPanel) {
        mainFrame.setContentPane(newPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public PanelFactory getPanelFactory() {
        return panelFactory;
    }

    // Método para navegar al panel de selección de clase
    public void showClassSelectionPanel() {
        changePanel(panelFactory.getClassMenuPanel());
    }

    // Método para navegar al panel del juego
    public void showGamePanel() {
        changePanel(panelFactory.getGamePanel());
    }
    
    public void showWinPanel() {
        changePanel(panelFactory.getWinPanel());
    }
    
    // Método para navegar al panel del menú principal
    public void showMainMenuPanel() {
        audioPlayer.stopBackgroundMusic();
        audioPlayer.playBackgroundMusic("/audio/menu.wav"); // Reproducir música cuando se muestre el menú principal
        changePanel(panelFactory.getMainMenuPanel());
    }
}
