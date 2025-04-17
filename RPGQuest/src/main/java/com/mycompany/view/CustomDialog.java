/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.util.FontUtil;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author Oscar
 */
public class CustomDialog extends JDialog implements ActionListener {

    private RoundedButton okButton;
    private JPanel contentPanel;
    private JLabel messageLabel;
    private JFrame parentFrame; // Almacena el JFrame padre

    public CustomDialog(JFrame parent, String message) {
        super(parent, "Message", true); // Llama al constructor de JDialog y lo hace modal
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Establece el comportamiento al cerrar el diálogo
        this.parentFrame = parent; // Guarda el JFrame padre

        contentPanel = new JPanel(new BorderLayout()); // Usa BorderLayout para organizar los componentes
        Font font14 = FontUtil.chargeFont("/fonts/Minecraft.ttf", 14f);
        messageLabel = new JLabel(message, SwingConstants.CENTER); // Etiqueta para el mensaje, centrada
        Font defaultFont = new Font("Dialog", Font.PLAIN, 12); // Fuente por defecto
        messageLabel.setFont(defaultFont);
        contentPanel.add(messageLabel, BorderLayout.CENTER); // Añade la etiqueta al panel central

        okButton = new RoundedButton("OK"); // Botón para cerrar el diálogo
        okButton.addActionListener(this); // Escucha los eventos del botón
        okButton.setFont(font14);
        JPanel buttonPanel = new JPanel(); // Panel para el botón
        buttonPanel.add(okButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH); // Añade el panel del botón al panel inferior

        // Establece un borde por defecto para el panel de contenido
        contentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        getContentPane().add(contentPanel); // Añade el panel de contenido al diálogo

        pack(); // Ajusta el tamaño del diálogo al contenido
        setLocationRelativeTo(parent); // Centra el diálogo относительно родительского окна
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            dispose(); // Cierra el diálogo al hacer clic en el botón "OK"
        }
    }

    public void setBackgroundColor(Color color) {
        contentPanel.setBackground(color);
        // Asegura que el fondo del botón también se actualice para que coincida
        for (java.awt.Component comp : contentPanel.getComponents()) {
            if (comp instanceof JPanel) {
                comp.setBackground(color);
            }
        }
    }

    public void setTextColor(Color color) {
        messageLabel.setForeground(color);
    }

    public void setFont(Font font) {
        messageLabel.setFont(font);
    }

    public void mostrar() {
        if (parentFrame != null) {
            setLocationRelativeTo(parentFrame); // Centra el diálogo con respecto al padre JFrame
        } else {
            // Si parentFrame es null, intenta encontrar un componente padre y usar ese.
            Component parent = getParentComponent();
            if (parent != null) {
                setLocationRelativeTo(parent);
            } else {
                setLocationRelativeTo(null); // Si no se encuentra un padre, centra en la pantalla.
            }
        }
        pack(); // Ajusta el tamaño del diálogo al contenido
        setVisible(true); // Muestra el diálogo
    }

    private Component getParentComponent() {
        Component c = this;
        while (c != null && !(c instanceof JFrame) && !(c instanceof JDialog)) {
            c = c.getParent();
        }
        return c;
    }
}
