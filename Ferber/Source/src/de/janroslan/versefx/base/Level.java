package de.janroslan.versefx.base;

import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.physics.BoundingBox;
import de.janroslan.versefx.physics.NodePositionChange;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import javafx.scene.Group;

/**
 * Abstrakte Level-Definition. Ein Level im Spiel muss von dieser Klasse erben
 *
 * @author Jackjan
 */
public abstract class Level implements Updateable, ObjectManager {

    private Group root;
    private ObjectBatch batch;

    // Sammlung von Objekten die im nächsten Frame gelöscht werden sollen -> Mülleimer
    private HashSet<BasicNode> garbage;
    
    // Schlange von Objekten, bei deren neue Position eine Kollision geprüft werden muss
    private Queue<NodePositionChange> colQueue;

    /**
     * Initialisiert das Level und erstellt einen Objektsammler, welcher im Level Objekt zuum Zeichnen aufnimmt
     * @param root
     * @return 
     */
    public ObjectBatch initLevel(Group root) {
        this.root = root;
        garbage = new HashSet<>();
        colQueue = new LinkedList<>();
        
        //ObjektSammer
        batch = new ObjectBatch(this);
        
        // Level initialisieren
        init(batch);

        // Löschen der derzeitigen Objekte im JavaFX Szenengraph
        root.getChildren().clear();
        
        // Hinzufügen der neuen Objekte im Szenengraph
        root.getChildren().addAll(batch.getContent());

        return batch;
    }

    
    
    

    protected abstract void init(ObjectCollector collector);

    @Override
    public void destroyObject(BasicNode o) {
        root.getChildren().remove(o.getNode());
        batch.getContent().remove(o.getNode());
        garbage.add(o);

    }

    
    /**
     * Gibt dialle Kollisions mit dem Objekt 'o' zurück
     * @param o
     * @return 
     */
    @Override
    public ArrayList<Collidable> getCollisions(Collidable o) {
        ArrayList<Collidable> result = new ArrayList<>();

        // Keine Kollisionsziele für 'o' vorhanden 
        if (!batch.getCollisionDict().containsKey(o.getTag()) || !batch.getCollisionDict().get(o.getTag()).containsKey(o)) {
            return result;
        }

        
        for (String target : batch.getCollisionDict().get(o.getTag()).get(o)) {

            // Kollisionsziel in HashMap nicht vorhanden
            if (!batch.getCollisionDict().containsKey(target)) {
                continue;
            }

            for (Entry<Collidable, String[]> entry : batch.getCollisionDict().get(target).entrySet()) {
                if (o.isIntersecting(entry.getKey())) {
                    result.add(entry.getKey());
                }
            }
        }

        return result;
    }

    
    /**
     * Dynamisches Hinzufügen eines Objektes zum JavaFX-Szenengraph
     * @param o 
     */
    @Override
    public void addObject(BasicNode o) {
        batch.add(o);
        root.getChildren().add(o.getNode());
    }

    /**
     * Hinzufügen eines Objektes in die Kollisionqueue
     * @param o
     * @param x
     * @param y
     * @param z 
     */
    @Override
    public void addToColQueue(BasicNode o, double x, double y, double z) {
        colQueue.offer(new NodePositionChange(o,x,y,z));
    }

    
    /**
     * Frame-update-Routine
     * @param deltaT 
     */
    @Override
    public final void tick(float deltaT) {

        // Löschen von Objekten im Mülleimer
        for (BasicNode o : garbage) {
            batch.getCollisionDict().get(o.getTag()).remove((Collidable) o);
        }
        garbage.clear();

        // Verarbeiten der Kollisionsschlange
        processColQueue();

        // Update des Levels in der Implementation dieser abstrakten Klasse
        update(deltaT);
    }

    
    /**
     * Durchlaufen der Kollisionsschlange 
     */
    private void processColQueue() {
        
        colqueue:
        while(!colQueue.isEmpty()) {
            NodePositionChange current = colQueue.poll();
            
            // Ermitteln der Kollisionszeile für das akutelle Objekt
            HashMap<Collidable, String[]> targetMap = batch.getCollisionDict().get(current.getNode().getTag());
            String[] targets = targetMap.get(current.getNode());

            // Durchlaufen jedes Targets um Kollision mit dem Objekt prüfen
            for (String s : targets) {
                for (Entry<Collidable, String[]> batchEntry : batch.getCollisionDict().get(s).entrySet()) {
                    
                    // Erstellung und Prüfung der neuen Position
                    BoundingBox newPos = new BoundingBox(current.getNode().getBounds());
                    newPos.setX(newPos.getX() + current.getX());
                    newPos.setY(newPos.getY() + current.getY());
                    newPos.setZ(newPos.getZ() + current.getZ());
                    
                    if (batchEntry.getKey().isIntersecting(newPos)) {
                        // Weiter mit dem nächsten Objekt, wenn Kollision vorliegt
                        continue colqueue;
                    }

                }
            }

            // Anwenden der neuen Position wenn keine KOllision vorliegt
            current.getNode().moveX(current.getX());
            current.getNode().moveY(current.getY());
            current.getNode().moveZ(current.getZ());
        }
        colQueue.clear();
    }

    /**
     * Frame-update-Routine für die Implementation dieser Klasse
     * @param deltaT 
     */
    protected abstract void update(float deltaT);
    
    
    
    protected HashSet<BasicNode> getGarbage() {
        return garbage;
    }
}
