package de.janroslan.versefx;

import de.janroslan.versefx.io.InputManager;
import de.janroslan.versefx.base.FXGame;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Einstiegspunkt für die JVM
 *
 * @author Jackjan
 */
public class Main extends Application {

    /**
     * JVM Einstieg (JavaFX)
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        // Initialisieren des Spiels
        FXGame game = new Game();
        game.init(primaryStage);
        game.loadContent();

        // Initialisieren des InputManager für Nutzeringaben
        InputManager input = new InputManager();
        input.init();

        // Drücken einer Tastaturtaste registrieren
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            input.registerKeyDown(event.getCode());
        });

        // Loslassen einer Tastaturtaste registrieren
        primaryStage.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            input.registerKeyUp(event.getCode());
        });

        // Drücken einer Maustaste registrieren
        primaryStage.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            input.registerMousePress(event.getButton());
        });

        // Loslassen einer Maustaste registrieren
        primaryStage.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            input.registerMouseRelease(event.getButton());
        });

        // Registrieren der Mausposition
        primaryStage.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
            input.registerMousePos(event.getX(), event.getY());
        });

        // Definiton der Frame-Update-Routine
        AnimationTimer timer = new AnimationTimer() {
            // Zeitstempel
            private long last = 0;

            @Override
            public void handle(long now) {

                // Aufrufen der Updateroutine des Spiels mit Angabe der Delta-Zeit
                game.update((float) ((now - last) / 1000_000.0));
                last = now;
            }
        };
        timer.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Debug-ausgaben
        System.out.println("Graphic pipeline: " + com.sun.prism.GraphicsPipeline.getPipeline().getClass().getName());
        System.out.println("java.version : " + System.getProperty("java.version"));
        System.out.println("sun.arch.data.model : " + System.getProperty("sun.arch.data.model"));
        System.out.println("os.arch : " + System.getProperty("os.arch"));

        // JavaFX starten
        launch(args);
    }

}
