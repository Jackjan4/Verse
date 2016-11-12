package de.janroslan.versefx.io;

import javafx.scene.input.KeyCode;

/**
 * Abstrahiert den Zugriff auf Eingaben für andere Klasse
 * Implementation: InputManager
 * @author Jackjan
 */
public interface Input {
    
    
    /**
     * Gibt zurück, ob die angegebene Taste gedrückt ist.
     * @param key - KeyCode der jeweiligen Taste
     * @return 
     */
    public boolean IsKeyDown(KeyCode key);
    
    
    /**
     * Gibt zurück, ob die angegebene Taste losgelassen ist.
     * @param key - KeyCode der jeweiligen Taste
     * @return 
     */
    public boolean IsKeyUp(KeyCode key);
}
