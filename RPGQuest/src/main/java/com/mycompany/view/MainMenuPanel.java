/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.listener.MainMenuListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Oscar
 */
public class MainMenuPanel extends JPanel {

    private final JButton playButton;
    private final JButton exitButton;
    private final Image backgroundImage;

    public MainMenuPanel() {
        // Cargar la imagen de fondo
        ImageIcon icon = new ImageIcon("./src/main/resources/images/menu.png");
        backgroundImage = icon.getImage();

        // Establecer el layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Centrar los componentes
        gbc.insets = new Insets(10, 0, 10, 0); // Espaciado entre botones

        // Añadir espacio vertical de 100 píxeles en la parte superior
        gbc.gridy = 0;
        add(Box.createVerticalStrut(100), gbc);

        // Crear botón "Jugar" con imagen
        ImageIcon playIcon = new ImageIcon(getClass().getResource("/images/btnJugar.png"));
        playButton = new JButton();
        playButton.setIcon(playIcon);
        playButton.setText("");
        playButton.setBorderPainted(false);
        playButton.setContentAreaFilled(false);
        playButton.setFocusPainted(false);
        playButton.setOpaque(false);
        playButton.setPreferredSize(new Dimension(playIcon.getIconWidth(), playIcon.getIconHeight()));

        // Añadir botón "Jugar" al panel
        gbc.gridy = 1;
        add(playButton, gbc);

        // Añadir espacio vertical de 30 píxeles entre los botones
        gbc.gridy = 2;
        add(Box.createVerticalStrut(30), gbc);

        // Crear botón "Salir" con imagen
        ImageIcon exitIcon = new ImageIcon(getClass().getResource("/images/btnSalir.png"));
        exitButton = new JButton();
        exitButton.setIcon(exitIcon);
        exitButton.setText("");
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(false);
        exitButton.setPreferredSize(new Dimension(exitIcon.getIconWidth(), exitIcon.getIconHeight()));
        exitButton.addActionListener(e -> System.exit(0));

        // Añadir botones al panel con las restricciones del layout
        gbc.gridy = 1;
        add(playButton, gbc);
        gbc.gridy = 2;
        add(exitButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage((Image) backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void addListener(MainMenuListener listener) {
        playButton.addActionListener(e -> listener.onPlayButtonClicked());
        exitButton.addActionListener(e -> listener.onExitButtonClicked());
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                listener.onMouseEntered();
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                listener.onMouseEntered();
            }
        });
    }
}
