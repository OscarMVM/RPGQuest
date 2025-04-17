/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.listener.ClassPanelListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Oscar
 */
public class ClassMenuPanel extends JPanel {

    private JButton warriorButton;
    private JButton mageButton;
    private JButton archerButton;
    private Image backgroundImage;

    public ClassMenuPanel() {
        initComponents();
    }

    private void initComponents() {
        // Cargar la imagen de fondo
        ImageIcon icon = new ImageIcon("./src/main/resources/images/clase.png");
        backgroundImage = icon.getImage();

        // Establecer el layout
        setLayout(null);
        // Crear y configurar los botones
        ImageIcon warriorIcon = new ImageIcon(getClass().getResource("/images/guerrero.png"));
        warriorButton = new JButton("Guerrero");
        warriorButton.setIcon(warriorIcon);
        warriorButton.setText("");
        warriorButton.setBorderPainted(false);
        warriorButton.setContentAreaFilled(false);
        warriorButton.setFocusPainted(false);
        warriorButton.setOpaque(false);
        warriorButton.setPreferredSize(new Dimension(warriorIcon.getIconWidth(), warriorIcon.getIconHeight()));

        ImageIcon mageIcon = new ImageIcon(getClass().getResource("/images/mago.png"));
        mageButton = new JButton("Mago");
        mageButton.setIcon(mageIcon);
        mageButton.setText("");
        mageButton.setBorderPainted(false);
        mageButton.setContentAreaFilled(false);
        mageButton.setFocusPainted(false);
        mageButton.setOpaque(false);
        mageButton.setPreferredSize(new Dimension(mageIcon.getIconWidth(), mageIcon.getIconHeight()));

        ImageIcon archerIcon = new ImageIcon(getClass().getResource("/images/arquero.png"));
        archerButton = new JButton("Arquero");
        archerButton.setIcon(archerIcon);
        archerButton.setText("");
        archerButton.setBorderPainted(false);
        archerButton.setContentAreaFilled(false);
        archerButton.setFocusPainted(false);
        archerButton.setOpaque(false);
        archerButton.setPreferredSize(new Dimension(archerIcon.getIconWidth(), archerIcon.getIconHeight()));

        // Establecer las posiciones y tamaños de los botones
        int buttonWidth = 200;
        int buttonHeight = 400;
        int spacing = 50; // Espacio entre botones

        int totalWidth = (buttonWidth * 3) + (spacing * 2);
        int startX = (1280 - totalWidth) / 2;
        int yPosition = (720 - buttonHeight) / 2;

        warriorButton.setBounds(startX, yPosition, buttonWidth, buttonHeight);
        mageButton.setBounds(startX + buttonWidth + spacing, yPosition, buttonWidth, buttonHeight);
        archerButton.setBounds(startX + (buttonWidth + spacing) * 2, yPosition, buttonWidth, buttonHeight);

        // Añadir los botones al panel
        add(warriorButton);
        add(mageButton);
        add(archerButton);
    }

    public void addListener(ClassPanelListener listener) {
        warriorButton.addActionListener(e -> listener.onPlayerClassSelected("warrior"));
        mageButton.addActionListener(e -> listener.onPlayerClassSelected("mage"));
        archerButton.addActionListener(e -> listener.onPlayerClassSelected("archer"));
        warriorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                listener.onMouseEntered();
            }
        });
        mageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                listener.onMouseEntered();
            }
        });
        archerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                listener.onMouseEntered();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
