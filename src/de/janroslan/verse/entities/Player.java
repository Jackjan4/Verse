/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.verse.entities;

import de.janroslan.versefx.draw.AnimImage;
import de.janroslan.versefx.io.InputManager;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;

/**
 * Spielfigur des Spiels
 * @author Jackjan
 */
public class Player extends AnimImage {

    // Spieler steht auf dem Boden?
    private boolean grounded;

    // Sound-Objekt des Zeit-zurückspul-Sounds
    private AudioClip timeSound;

    // false = left - true = right
    private boolean turned;

    private double yForce;

    // Positonen des letzen Frames
    private double lastX;
    private double lastY;

    public Player(int startX, int startY) {
        super("player", startX, startY, new Image("/de/janroslan/verse/resources/player.png"), 2, 1, 0.7, 2, 3000);
        grounded = true;
        turned = true;

        setAnimSpeed(10000);
        startAnim();

    }

    @Override
    public void refresh(float deltaT) {
        
        
        // Springen
        if (grounded && InputManager.getGlobalInput().IsKeyDown(KeyCode.SPACE)) {
            grounded = false;
            yForce = -3;
        }

        
        // Links bewegen
        if (InputManager.getGlobalInput().IsKeyDown(KeyCode.A)) {
            moveColX(-0.5 * deltaT);
            grounded = false;
            
            
            if (turned) {
                rotateY(180.0);
                turned = !turned;
            }
        }

        
        // Rechts bewegen
        if (InputManager.getGlobalInput().IsKeyDown(KeyCode.D)) {
            moveColX(0.5 * deltaT);
            grounded = false;
            

            
            // Umdrehen der Spielfigur bei Richtungswechsel
            if (!turned) {
                rotateY(180.0);
                turned = !turned;
            }
        }
        
         // Imitierte Gravition mit einer beschleunigten Bewegung 
        if (!grounded) {
            yForce += 0.02 * deltaT;
            moveColY(yForce);
        }
       
        // Spieler steht auf Boden, wenn yWerte gleich bleiben
        if (getY() == lastY)
        {
            grounded = true;
            yForce = 0;
        }

        // Zeit zurückspuelen bei Druck auf die E-Taste
        if (InputManager.getGlobalInput().IsKeyDown(KeyCode.E)) {
            reverseTime(3000);
            grounded = false;
        }
        
        // SPeichern der letzten Position
        lastX = getX();
        lastY = getY();
    }
}
