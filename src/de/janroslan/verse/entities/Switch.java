package de.janroslan.verse.entities;

import de.janroslan.versefx.draw.AnimImage;
import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.io.InputManager;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;

/**
 * Schalter, den die Spielfigur umlegen kann um eine Aktion auszulösen. (Typischerweise das Öffnen einer Tür)
 * @author jackjan
 */
public class Switch extends AnimImage {

    private boolean pushed;
    
    private EventHandler handler;
    
    private boolean lastKeyDown;

    private AudioClip clickSound;

    public Switch(int startX, int startY) {
        super("switch", startX, startY, new Image("/de/janroslan/verse/resources/switch.png"), 3, 1, 0.5, 2, 0);
        pushed = false;
        clickSound = new AudioClip(getClass().getResource("/de/janroslan/verse/resources/sounds/click.wav").toString());
       
    }

    
    @Override
    public void intersects(Collidable col) {
        if ("player".equals(col.getTag()) && InputManager.getGlobalInput().IsKeyUp(KeyCode.F) && lastKeyDown) {
            
            pushed = !pushed;
            if (pushed) {
                goToFrame(2);
            } else {
                goToFrame(0);
            }

            clickSound.play();
            if (handler != null) {
                handler.handle(null);
            }
        }

        if (InputManager.getGlobalInput().IsKeyDown(KeyCode.F)) {
            lastKeyDown = true;
        } else {
            lastKeyDown = false;
        }

    }

    /**
     * Festlegen einer Ereignislogik, wenn der Schalter betätigt wird
     * @param handler 
     */
    public void setOnTriggered(EventHandler handler) {
        this.handler = handler;
    }

    public boolean isPushed() {
        return pushed;
    }

}
