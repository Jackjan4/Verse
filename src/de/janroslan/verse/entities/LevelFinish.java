package de.janroslan.verse.entities;

import de.janroslan.versefx.draw.AnimImage;
import de.janroslan.versefx.physics.Collidable;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 * Zeilflagge, die der Spieler erreichen muss um das Level abzuschließen
 * @author jackjan
 */
public class LevelFinish extends AnimImage {
    
    private boolean finished;
    
    private AudioClip finishSound;
    
    public LevelFinish(int startX, int startY) {
        super("finish", startX, startY, new Image("/de/janroslan/verse/resources/flag.png"), 2, 1, 0.5, 2, 0);
        finishSound = new AudioClip(getClass().getResource("/de/janroslan/verse/resources/sounds/levelFinish.wav").toString());
        finished = false;
    }
    
    @Override
    public void intersects(Collidable col)
    {
        if (col.getTag().equals("player"))
        {
            finished = true;
            finishSound.play(0.7);
        }
    }

    public boolean isFinished() {
        return finished;
    }
    
    
    
}
