package de.janroslan.versefx.base;

import javafx.scene.Group;

/**
 * Singleton LevelLoader, der Level laden kann und diese verwaltet
 *
 * @author Jackjan
 */
public class LevelLoader {
    
    private static LevelLoader obj;
    
    // Geladenes Level
    private Level currentLvl;
    
    // JavaFX-Szenengraph
    private Group group;
    
    // Derzeitiger ObjektSammler des geladenen Levels
    private ObjectBatch currentBatch;
    
    
    private boolean isTicking;
    
    // Automatische update-Routine pro Frame
    private boolean autoUpdate;
    
    
    // Automatisches Kollionssystem pro Frame
    private boolean colCheckPerFrame;

    /**
     * Gibt die LevelLoader Singleton-Instanz zurück
     * @return
     */
    public static LevelLoader getInstance() {
        return obj;
    }

    /**
     * Erstellt einen neuen LevelLoader mit der angegebenen Java-Szenengraph-Wurzel
     *
     * @param root
     * @return
     */
    public static LevelLoader getInstance(Group root) {
        return obj = new LevelLoader(root);
    }
    
    /**
     * Erstellt ein neue LevelLoader-Instance
     * @param root 
     */
    private LevelLoader(Group root) {
        this.group = root;
    }
    
    
    /**
     * TODO: Lädt ein neues Level im XML-Format
     * @param levelRes 
     */
    public void loadLevel(String levelRes) {
        stopTicking();
        
    }
    
    /**
     * Lädt ein neues Level
     * @param lvl 
     */
    public void loadLevel(Level lvl) {
        stopTicking();
        autoUpdate = false;
        currentLvl = lvl;
        currentBatch = lvl.initLevel(group);
        
        startTicking();
    }

    /**
     * Lädt ein neues Level
     * @param lvl
     * @param autoUpdate  - Aktiviert/Deaktiviert das automatische Aufrufen der Update-Methode des Levels
     * @param colCheckPerFrame - Aktiviert/Deaktivert das automatische Kollisionssystem pro Frame
     */
    public void loadLevel(Level lvl, boolean autoUpdate, boolean colCheckPerFrame) {
        stopTicking();
        this.autoUpdate = autoUpdate;
        currentLvl = lvl;
        currentBatch = lvl.initLevel(group);
        this.colCheckPerFrame = colCheckPerFrame;
        
        startTicking();
    }
    
    
    /**
     * Frame-Update-Routine
     * @param deltaT 
     */
    public final void tick(float deltaT) {
        if (isTicking) {
            
            // Standard Kollisionssystem
            if (colCheckPerFrame) {
                currentBatch.getCollisionDict().forEach((k, v) -> {
                    v.forEach((object, targets) -> {
                        for (String s : targets) {
                            
                            currentBatch.getCollisionDict().get(s).forEach((target, b) -> {
                                if (object.isIntersecting(target)) {
                                    object.intersects(target);
                                }
                            });
                        }
                    });
                });
            }
            
            
            
            // Update level itself
            currentLvl.tick(deltaT);
        }
        
    }
    
    /**
     * Startet die update-Routine
     */
    public void startTicking() {
        isTicking = true;
    }
    
    // Stoppt die Update-Routine
    public void stopTicking() {
        isTicking = false;
    }
    
}
