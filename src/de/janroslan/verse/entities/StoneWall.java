package de.janroslan.verse.entities;

import de.janroslan.versefx.draw.DrawImage;
import javafx.scene.image.Image;

/**
 * Eine Steinwand. Größe: 40x40 Pixel
 * Wir im Spiel als Block füpr die Türen genutzt
 * @author jackjan
 */
public class StoneWall extends DrawImage {
    
    public StoneWall(int startX, int startY) {
        super("wall", startX, startY, new Image("/de/janroslan/verse/resources/stoneWall.png"), 0.57, 2, 0);
    }
    
}
