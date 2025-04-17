/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.factory;

import com.mycompany.model.Archer;
import com.mycompany.model.Mage;
import com.mycompany.model.Player;
import com.mycompany.model.Warrior;

/**
 *
 * @author Oscar
 */
public class PlayerFactory {

    public static Player createPlayer(String playerType) {
        switch (playerType.toLowerCase()) {
            case "warrior":
                return new Warrior("Gerrero");
            case "mage":
                return new Mage("Mago");
            case "archer":
                return new Archer("Arquero");
            default:
                return null;
        }
    }
}
