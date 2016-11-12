package de.janroslan.verse.entities;

import de.janroslan.versefx.draw.DrawImage;
import javafx.scene.image.Image;

/**
 * Eine Grasswand. Größe: 40x40 Pixel
 * Wird im Spiel als normale Wand benutzt
 * @author jackjan
 */
public class GrassWall extends DrawImage {
    
    public GrassWall(int startX, int startY) {
        super("wall", startX, startY, new Image("/de/janroslan/verse/resources/grass.png"), 0.57, 2, 0);
    }
}
