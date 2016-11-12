package de.janroslan.versefx.base;

import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.physics.CollisionType;
import java.util.ArrayList;

/**
 * Abstraktes Interface, welches der ObjektSammler implementiert.
 * Level können mit diesem Interface arbeiten um mit dem Sammler zu kommunizieren
 * @author jackjan
 */
public interface ObjectCollector {
    
    /**
     * Fügt dem Sammler ein Feld an Objekten hinzu
     * @param n 
     */
    public void add(BasicNode... n);
    
    
    /**
     * Registriert ein Objekt beim Kollisionsystem
     * @param o
     * @param type
     * @param targets 
     */
    public void registerCollider(Collidable o, CollisionType type, String... targets);
    

    /**
     * Fügt dem Sammler eine Liste an Objekten hinzu
     * @param list 
     */
    public void add(ArrayList<BasicNode> list);
}
