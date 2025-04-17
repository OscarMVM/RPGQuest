/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.listener.ClassPanelListener;
import com.mycompany.listener.WinPanelListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Oscar
 */
public class WinPanel extends JPanel {

    private final Image backgroundImage;
    private final JButton backButton;

    public WinPanel() {
        // Cargar la imagen de fondo
        ImageIcon icon = new ImageIcon("./src/main/resources/images/win.gif");
        backgroundImage = icon.getImage();

        // Establecer el layout
        setLayout(null);

        ImageIcon archerIcon = new ImageIcon(getClass().getResource("/images/btnRegresar.png"));
        backButton = new JButton("Arquero");
        backButton.setIcon(archerIcon);
        backButton.setText("");
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setOpaque(false);
        backButton.setPreferredSize(new Dimension(archerIcon.getIconWidth(), archerIcon.getIconHeight()));
        backButton.setBounds(850, 300, archerIcon.getIconWidth(), archerIcon.getIconHeight());

        add(backButton);

    }

    public void addListener(WinPanelListener listener) {
        backButton.addActionListener(e -> listener.onBackButtonClicked());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
