package de.janroslan.versefx.draw;

import de.janroslan.versefx.base.BasicNode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Ein 2D Bild, welches mithilfe des JavaFX-ImageView gezeichnet wird
 *
 * @author jackjan
 */
public class DrawImage extends BasicNode {

    /**
     * Primärkonstruktor
     *
     * @param tag
     * @param startX
     * @param startY
     * @param img
     * @param width
     * @param height
     * @param layer
     * @param time
     */
    public DrawImage(String tag, int startX, int startY, ImageView img, int width, int height, int layer, int time) {
        super(tag, img, time);
        
        scaleX(width / getWidth());
        scaleY(height / getHeight());
        
        setX(startX);
        setY(startY);
        setZ(layer);
    }

    public DrawImage(String tag, int startX, int startY, Image img, double ratio, int layer, int time) {
        this(tag, startX, startY, new ImageView(img), (int) (img.getWidth() * ratio), (int) (img.getHeight() * ratio), layer, time);

    }

    /**
     * Constructor for quadratic images
     *
     * @param tag
     * @param startX
     * @param startY
     * @param img
     * @param length
     * @param layer
     */
    public DrawImage(String tag, int startX, int startY, Image img, int length, int layer, int time) {
        this(tag, startX, startY, new ImageView(img), length, length, layer, time);

    }

    
    /**
     * Gibt das angezeigt Bild zurück
     * @return 
     */
    public final ImageView getImageView() {
        return (ImageView) getNode();
    }

    
    /**
     * Frame-update-Routine
     * @param deltaT 
     */
    @Override
    protected void update(float deltaT) {
        
    }

}
