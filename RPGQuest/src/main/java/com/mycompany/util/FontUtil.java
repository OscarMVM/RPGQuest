package com.mycompany.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Oscar
 */
public class FontUtil {

    public static Font chargeFont(String path, float tamaño) {
        try (InputStream is = FontUtil.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("No se encontró el archivo de fuente en la ruta: " + path);
            }
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, is);
            fuente = fuente.deriveFont(tamaño);

            // Registrar la fuente para que esté disponible globalmente
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuente);

            return fuente;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Retornar una fuente por defecto en caso de error
            return new Font("Arial", Font.PLAIN, (int) tamaño);
        }
    }
}
