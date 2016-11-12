package de.janroslan.versefx.io;

import java.util.HashMap;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

/**
 * Der InputManager ist eine Singletone-Klasse, die Tastatur- und Mauseingaben registriert und zugreifbar für
 * andere Klasse macht.
 * @author Jackjan
 */
public class InputManager implements Input {

    private final HashMap<KeyCode, Boolean> keyTable;
    private final HashMap<MouseButton, Boolean> mouseTable;
    
    private double mouseX;
    private double mouseY;
    
    private static Input input;

    public InputManager() {
        keyTable = new HashMap<>(50);
        mouseTable = new HashMap<>();
    }
    
    
    /**
     * Initialiserung
     */
    public void init()
    {
        this.input = this;
    }
    
    
    /**
     * Gibt die globale Eingabeschnittstelle zurück, mit der Klasse Eingaben des Nutzer abfragen können.
     * @return 
     */
    public static Input getGlobalInput()
    {
        return input;
    }
    
    
    /**
     * Registriert eine gedrückte Taste 'key'
     * @param key - KeyCode der jeweiligen Taste
     */
    public void registerKeyDown(KeyCode key)
    {
        keyTable.put(key, true);
    }
    
    
    /**
     * Registriert eine losgelassene Taste 'key'
     * @param key - KeyCode der jeweiligen Taste
     */
    public void registerKeyUp(KeyCode key)
    {
        keyTable.put(key, false);
    }

    /**
     * Gibt zurück, ob die angegebene Taste gedrückt ist.
     * @param key - KeyCode der jeweiligen Taste
     */
    @Override
    public boolean IsKeyDown(KeyCode key) {
        Boolean b = keyTable.get(key);
        if (b == null) {
            return false;
        }

        return b;
    }
    
    
    /**
     * Gibt zurück, ob die angegebene Taste losgelassen ist.
     * @param key - KeyCode der jeweiligen Taste
     */
    @Override
    public boolean IsKeyUp(KeyCode key) {
        Boolean b = keyTable.get(key);
        if (b == null) {
            return true;
        }

        return !b;
    }
    
    
    /**
     * Registriert einen Tastenklick auf der Maus
     * @param btn 
     */
    public void registerMousePress(MouseButton btn)
    {
        mouseTable.put(btn, true);
    }

    
    /**
     * Registriert das Loslassen einer Taste auf der Maus
     * @param btn 
     */
    public void registerMouseRelease(MouseButton btn)
    {
        mouseTable.put(btn, false);
    }
    
    
    /**
     * Registriert die derzeitige Mausposition
     * @param x
     * @param y 
     */
    public void registerMousePos(double x, double y)
    {
        this.mouseX = x;
        this.mouseY = y;
    }
    
    /**
     * Gibt die derzeitige Mausposition zurück
     * @return  
     */
    public Point2D getMousePos()
    {
        return new Point2D(mouseX, mouseY);
    }
    
}
