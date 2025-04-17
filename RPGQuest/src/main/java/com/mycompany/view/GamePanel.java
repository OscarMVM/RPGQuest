/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.listener.GamePanelListener;
import com.mycompany.util.FontUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Oscar
 */
public class GamePanel extends JPanel {

    private JLabel questionLabel;
    private JLabel playerInfoLabel;
    private JLabel timerLabel; // Label para mostrar el tiempo restante
    private JLabel questionNumberLabel; // Label para mostrar el numero de la pregunta
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JButton dButton;
    private JButton backButton;
    private JButton abilityButton;
    private JPanel answerPanel;
    private JTextArea consoleTextArea;
    private Image backgroundImage;

    public GamePanel() {
        initComponents();
    }

    private void initComponents() {
        // Cargar la imagen de fondo
        ImageIcon icon = new ImageIcon("./src/main/resources/images/juego.png");
        backgroundImage = icon.getImage();

        Font font14 = FontUtil.chargeFont("/fonts/Minecraft.ttf", 14f);
        Font font18 = FontUtil.chargeFont("/fonts/Minecraft.ttf", 18f);

        // Configuración general
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(0xE27669));
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // -------------------------- Panel superior --------------------------
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);

        // Etiquetas de información con fondo semitransparente
        playerInfoLabel = new JLabel();
        playerInfoLabel.setFont(font18);
        playerInfoLabel.setOpaque(true);
        playerInfoLabel.setBackground(Color.BLACK);
        playerInfoLabel.setForeground(Color.WHITE);
        playerInfoLabel.setBorder(new EmptyBorder(5, 10, 5, 10));

        timerLabel = new JLabel();
        timerLabel.setFont(font18);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.BLACK);
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setBorder(new EmptyBorder(5, 10, 5, 10));

        questionNumberLabel = new JLabel();
        questionNumberLabel.setFont(font18);
        questionNumberLabel.setOpaque(true);
        questionNumberLabel.setBackground(Color.BLACK);
        questionNumberLabel.setForeground(Color.WHITE);
        questionNumberLabel.setBorder(new EmptyBorder(5, 10, 5, 10));

        topPanel.add(playerInfoLabel);
        topPanel.add(timerLabel);
        topPanel.add(questionNumberLabel);
        this.add(topPanel, BorderLayout.NORTH);

        // -------------------------- Panel central --------------------------
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        // Pregunta (centrada)
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        spacer.setPreferredSize(new Dimension(0, 25));
        questionLabel = new JLabel();
        questionLabel.setFont(font14);
        questionLabel.setOpaque(true);
        questionLabel.setBackground(new Color(0, 0, 0, 128));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
        questionLabel.setAlignmentX(CENTER_ALIGNMENT);
        contentPanel.add(spacer, BorderLayout.NORTH);
        contentPanel.add(questionLabel, BorderLayout.CENTER);

        // Espaciador vertical
        contentPanel.add(Box.createVerticalStrut(10));

        // Panel para las opciones, en grilla 2x2
        answerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        answerPanel.setOpaque(false); // Hacer el panel transparente
        // Opciones reducidas (60x30)
        Dimension optSize = new Dimension(60, 30);
        aButton = new RoundedButton("Opción A");
        bButton = new RoundedButton("Opción B");
        cButton = new RoundedButton("Opción C");
        dButton = new RoundedButton("Opción D");
        for (JButton btn : new JButton[]{aButton, bButton, cButton, dButton}) {
            btn.setFont(font18);
            btn.setPreferredSize(optSize);
            btn.setMinimumSize(optSize);
            btn.setMaximumSize(optSize);
        }
        answerPanel.add(aButton);
        answerPanel.add(bButton);
        answerPanel.add(cButton);
        answerPanel.add(dButton);
        contentPanel.add(answerPanel);

        // Panel de consola (barra lateral derecha)
        consoleTextArea = new JTextArea(15, 20); // 15 filas, 20 columnas
        consoleTextArea.setBorder(new EmptyBorder(5, 5, 5, 5));
        consoleTextArea.setEditable(false);
        consoleTextArea.setFont(font14);
        consoleTextArea.setPreferredSize(new Dimension(400, 100)); // Tamaño preferido
        JScrollPane consoleScrollPane = new JScrollPane(consoleTextArea);
        consoleScrollPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        consoleScrollPane.setOpaque(false);
        consoleScrollPane.setPreferredSize(new Dimension(400, 100)); // Tamaño preferido
        consoleScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        contentPanel.add(consoleScrollPane);

        this.add(contentPanel, BorderLayout.CENTER);

        // Panel Inferior: Botones de Navegación y Habilidad
        JPanel bottomPanel = new JPanel(); // FlowLayout por defecto
        bottomPanel.setOpaque(false); // Hacer el panel transparente
        abilityButton = new RoundedButton("Usar Habilidad");
        backButton = new RoundedButton("Volver al Menú");
        abilityButton.setFont(font14);
        backButton.setFont(font14);
        bottomPanel.add(abilityButton);
        bottomPanel.add(backButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }

    public void addListener(GamePanelListener listener) {
        // Botones de opciones
        aButton.addActionListener(e -> listener.onAnswerSelected(0));
        bButton.addActionListener(e -> listener.onAnswerSelected(1));
        cButton.addActionListener(e -> listener.onAnswerSelected(2));
        dButton.addActionListener(e -> listener.onAnswerSelected(3));

        // Botón de volver al menú
        backButton.addActionListener(e -> listener.onBackButtonClicked());

        // Botón para usar habilidad
        abilityButton.addActionListener(e -> listener.onAbilityButtonClicked());
    }

    public void showMessage(String message) {
        Font font14 = FontUtil.chargeFont("/fonts/Minecraft.ttf", 14f);
        JTextArea textArea = new JTextArea(message);
        // Establece la fuente del JTextArea
        textArea.setFont(font14);
        textArea.setEditable(false); // Para que el usuario no pueda editar el texto
        textArea.setLineWrap(true); // Para que el texto largo se ajuste a la línea
        textArea.setWrapStyleWord(true); // Para que el ajuste de línea sea por palabras
        
        
        CustomDialog dialogo = new CustomDialog(new JFrame(), message);
        dialogo.setBackgroundColor(new Color(0xcad16d)); // Ejemplo de cambiar el color de fondo
        dialogo.setTextColor(new Color(0x3b3a48));
        dialogo.setFont(font14);
        dialogo.mostrar();// Muestra el diálogo
    }

    // Getters y setters para actualizar la interfaz desde el controlador
    public void setQuestionText(String question) {
        questionLabel.setText(question);
    }

    public void setAnswerText(int option, String text) {
        switch (option) {
            case 0 ->
                aButton.setText(text);
            case 1 ->
                bButton.setText(text);
            case 2 ->
                cButton.setText(text);
            case 3 ->
                dButton.setText(text);
            default ->
                throw new IllegalArgumentException("Opción no válida: " + option);
        }
    }

    public void enableAnswerButton(int index, boolean enable) {
        switch (index) {
            case 0:
                aButton.setEnabled(enable);
                break;
            case 1:
                bButton.setEnabled(enable);
                break;
            case 2:
                cButton.setEnabled(enable);
                break;
            case 3:
                dButton.setEnabled(enable);
                break;
            default:
                throw new IllegalArgumentException("Opción no válida: " + index);
        }
    }

    public void disableAllAnswerButtons() {
        for (int i = 0; i < 4; i++) {
            enableAnswerButton(i, false);
        }
    }

    public void enableAllAnswerButtons() {
        for (int i = 0; i < 4; i++) {
            enableAnswerButton(i, true);
        }
    }

    public void setPlayerInfoText(String info) {
        playerInfoLabel.setText(info);
    }

    public JButton getAbilityButton() {
        return abilityButton;
    }

    // Método para actualizar la visualización del timer
    public void setTimerText(String text) {
        timerLabel.setText("");
        timerLabel.setText(text);
    }

    public void setQuestionNumberText(int current, int total) {
        questionNumberLabel.setText("Pregunta " + current + " de " + total);
    }

    // Método para registrar mensajes en la consola
    public void logToConsole(String message) {
        consoleTextArea.append(message + "\n");
        consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
    }

    public void clear() {
        // Limpiar los textos de las etiquetas
        questionLabel.setText("");
        playerInfoLabel.setText("");
        timerLabel.setText("");
        questionNumberLabel.setText("");
        consoleTextArea.setText("");

        // Limpiar y deshabilitar los botones de respuesta
        aButton.setText("");
        bButton.setText("");
        cButton.setText("");
        dButton.setText("");

        aButton.setEnabled(false);
        bButton.setEnabled(false);
        cButton.setEnabled(false);
        dButton.setEnabled(false);

        // Deshabilitar el botón de habilidad
        abilityButton.setEnabled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage((Image) backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
