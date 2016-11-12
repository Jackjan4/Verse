package de.janroslan.versefx.base;

/**
 * Markiert Objekte für die update-Routine
 * @author Jackjan
 */
public interface Updateable {
    
    
    /**
     * Wird pro Frame aufgerufen und beschreibt die update-Logik eines Objekte
     * @param deltaT 
     */
    public void tick(float deltaT);
}
