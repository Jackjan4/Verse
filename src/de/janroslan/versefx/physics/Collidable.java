package de.janroslan.versefx.physics;

import java.util.ArrayList;

/**
 * Collidable.java wird von Objekten implementiert, die mit anderen Objekten kollidieren können sollen
 * @author Jackjan
 */
public interface Collidable {

    
    // Gibt die BoundingBox eines Objekts zurück
    public BoundingBox getBounds();

    
    // Gibt den Kollisionstyp eines Objekts zurück
    public CollisionType getType();

    
    // Ermittelt ob ein Objekt 'col' dieses Objekt kollidiert
    public default boolean isIntersecting(Collidable col) {
        return getBounds().intersects(col.getBounds());
    }
    
    
    // Ermittelt ob ein Objekt 'col' dieses Objekt kollidiert
    public default boolean isIntersecting(BoundingBox box) {
        return getBounds().intersects(box);
    }
    
    
    // Wird vom LevelLoader aufgerufen, wenn dieses Objekt mit einem Objekt 'col' kollidiert.
    // Kann zur Ereignisbehandlung überschrieben werden
    public void intersects(Collidable col);

    
    // Gibt den einzigartigen Namen des Objekttyps zurück
    public String getTag();

    
    // Legt den Kollisionstyp fest
    public void setColType(CollisionType type);

    
    // Gibt alle Kollisionen zurück, die dieses Objekt mit den definierten Zielen hat oder null wenn es mit keinen Objekten kollidiert
    public ArrayList<Collidable> getCollisions();
    
    // Gibt an, ob das Objekt aktiv ist
    public boolean isActive();
}
