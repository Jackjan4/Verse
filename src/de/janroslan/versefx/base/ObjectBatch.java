package de.janroslan.versefx.base;

import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.physics.CollisionType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javafx.scene.Node;

/**
 * Der ObjektBath (deutsch. ObjektSammler) sammelt die Objekte eines Levels und registriert ihre Kollionsziele für das Kollisionssystem
 * Der Sammler übergibt diese Informationen dem LevelLoader, welcher das Level initialisiert und mit seinem Kollisionssystem die Kollisionen überprüft
 *
 * @author Jackjan
 */
public class ObjectBatch implements ObjectCollector {

    private final ArrayList<Node> batch;
    
    private final HashMap<String, HashMap<Collidable,String[]>> collisionDict;

    // This about the Manager (Level) is carried to inject it into gameobjecs
    private ObjectManager man;

    
    public ObjectBatch(ObjectManager man) {
        batch = new ArrayList<>();
        this.man = man;
        collisionDict = new HashMap<>();
    }

    
    /**
     * Fügt einem Objekt die Informationen seines derzeitigen Levels hinzu
     * @param o 
     */
    public void injectInfo(BasicNode o) {
        o.setManager(man);
    }

    
    // Fügt Objekte dem Sammler hinzu
    @Override
    public void add(BasicNode... n) {
        for (BasicNode node : n) {
            injectInfo(node);
            batch.add(node.getNode());
        }
    }

    
    /**
     * Registriert eine Kollision für das Kollisionssystem
     * @param o - Das Objekt, welches registriert werden soll
     * @param type - Kollisionstyp
     * @param targets - Einzigartige Namen der Kollisoionsziele
     */
     
    @Override
    public void registerCollider(Collidable o, CollisionType type, String... targets) {
        // Add new tag-set if not existent
        if (collisionDict.get(o.getTag()) == null) {
            collisionDict.put(o.getTag(), new HashMap<>());
        }

        o.setColType(type);
        collisionDict.get(o.getTag()).put(o, targets);
    }

    public HashMap<String, HashMap<Collidable, String[]>> getCollisionDict() {
        return collisionDict;
    }


    public ArrayList<Node> getContent() {
        return batch;
    }

    /**
     * Fügt dem Sammler eine Liste an Objekten hinzu
     * @param n 
     */
    @Override
    public void add(ArrayList<BasicNode> n) {
        for (BasicNode node : n) {
            injectInfo(node);
            batch.add(node.getNode());
        }
    }

}
