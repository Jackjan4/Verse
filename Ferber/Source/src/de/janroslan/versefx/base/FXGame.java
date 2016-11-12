package de.janroslan.versefx.base;

import javafx.stage.Stage;

/**
 * Interface für das Spiel. Die Hauptklasse eines Spiels innerhalb der Engine muss dieses Interface implementieren
 * @author Jackjan
 */
public interface FXGame {
    
    /**
     * Lädt den Inhalt den das Siel zur Laufzeit braucht (Bilder, Texturen, Musik)
     */
    public  void loadContent();
    
    
    /**
     * Update-Routine
     * @param deltaT 
     */
    public  void update(float deltaT);
    
    
    /**
     * Initialisiert das Spiel
     * @param stage 
     */
    public void init(Stage stage);
}
