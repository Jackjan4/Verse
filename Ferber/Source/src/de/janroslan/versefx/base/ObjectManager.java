package de.janroslan.versefx.base;

import de.janroslan.versefx.physics.Collidable;
import java.util.ArrayList;

/**
 * Level.java implementiert dieses Interface um Level-Objekten die Möglichkeit zu geben mit ihrem Level zu kommunizieren
 * @author Jackjan
 */
public interface ObjectManager {
    
    
    /**
     * Zerstört das gegebene Objekt und löscht es aus dem Szenengraph
     * @param o 
     */
    public void destroyObject(BasicNode o);
    
    
    /**
     * Gibt die Objekte zurück, mit denen Objekt o kollidiert
     * @param o
     * @return 
     */
    public ArrayList<Collidable> getCollisions(Collidable o);
    
    
    /**
     * Fügt der Kollisions-Queue ein Objekt hinzu mitsamt seiner gewünschten neue Bewegung
     * @param o
     * @param x
     * @param y
     * @param z 
     */
    public void addToColQueue(BasicNode o, double x, double y, double z);
    
    
    /**
     * Fügt dem Level dynamisch während der Laufzeit ein Objekt hinzu
     * @param o 
     */
    public void addObject(BasicNode o);
}
