package de.janroslan.versefx;

import de.janroslan.versefx.base.FXGame;
import de.janroslan.versefx.base.LevelLoader;
import de.janroslan.verse.level.MainMenu;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hauptklasse des Spiels
 * @author Jackjan
 */
public class Game implements FXGame
{
    
    private LevelLoader level;
    
    // Fenstergröße
    public static final int W_WIDTH = 1280;
    public static final int W_HEIGHT = 720;
    
    /**
     * Inits the game itself
     * @param stage 
     */
    @Override
    public void init(Stage stage)
    {
        // JavaFX-Szenengraph Wurzel
        Group root = new Group();
        
        // Kameraeinstellungen
        ParallelCamera cam = new ParallelCamera();
        cam.setNearClip(0.1);
        cam.setFarClip(2000.0);
        
        // Setzen der Kamera und Fenstergröße
        Scene scene = new Scene(root,W_WIDTH,W_HEIGHT,true);
        scene.setCamera(cam);

        
        // Initialisieren des LevelLoaders
        this.level  = LevelLoader.getInstance(root);
        
        
        
        // Fenstereinstellungen der Anwendung
        stage.setWidth(W_WIDTH);
        stage.setHeight(W_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Verse - Jan-Philipp Roslan");
        
        stage.show();
    }
    
    
    /**
     * Lädt die Spielinhalt im Hauptmenü
     */
    @Override
    public void loadContent()
    {
        level.loadLevel(new MainMenu(),false,false);
    }
    
    
    /**
     * Frame-update-Routine
     * @param deltaT
     */
    @Override
    public void update(float deltaT)
    {
        level.tick(deltaT);
    }
}
