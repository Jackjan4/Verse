package de.janroslan.versefx.physics;

/**
 * Stellt den Kollisionstyp für eine BoundingBox dar.
 * Anhand des Typs wird die Kollision auf passende Weise berechnet.
 * @author Jackjan
 */
public enum CollisionType {
    
    Sphere(3),
    AABox2D(5),
    AABox3D(7),
    OOB(9);
    
    
    private final int val;
    
    
    CollisionType(int numVal) {
        this.val = numVal;
    }

    public int getVal() {
        return val;
    }
    
}
