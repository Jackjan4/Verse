package de.janroslan.versefx.draw;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Ein animierbares Bild, welches auf der Klasse DrawImage aufbaut.
 * @author jackjan
 */
public class AnimImage extends DrawImage {

    // Gewünschte Zeit für einen Animationszyklus
    private int animTime;

    // Zeit seit dem letzten Bildwechsel der Animation
    private double elapsed;

    // Animationsstatus
    private AnimationState animState;

    int pointer;

    // Anzahl der Zeilen und Spalten der Einzelbilder innerhalb eines Bildes
    private int columns;
    private int rows;

    // Maße eines Einzelbildes innerhalb der Textur
    private int subX;
    private int subY;

    /**
     * Konstruktor für Bilder, die in ihrem Seitenverhätlnis beibehalten werden sollen
     *
     * @param tag
     * @param startX
     * @param startY
     * @param img
     * @param columns
     * @param rows
     * @param ratio
     * @param time
     * @param layer
     */
    public AnimImage(String tag, int startX, int startY, Image img, int columns, int rows, double ratio, int layer, int time) {
        this(tag, startX, startY, img, columns, rows, (int) (img.getWidth() * ratio), (int) (img.getHeight() * ratio), layer, time);
    }

    /**
     * Konstruktor für quadratische Bilder
     *
     * @param tag
     * @param startX
     * @param startY
     * @param img
     * @param subs
     * @param length
     * @param layer
     * @param time
     */
    public AnimImage(String tag, int startX, int startY, Image img, int subs, int length, int layer, int time) {
        this(tag, startX, startY, img, subs, subs, length, length, layer, time);
    }

    /**
     * Primärkonstruktor
     *
     * @param tag
     * @param startX
     * @param startY
     * @param img
     * @param columns
     * @param rows
     * @param width
     * @param height
     * @param layer
     * @param time
     */
    public AnimImage(String tag, int startX, int startY, Image img, int columns, int rows, int width, int height, int layer, int time) {
        super(tag, startX, startY, new ImageView(img), width, height, layer, time);

        pointer = 0;
        this.columns = columns;
        this.rows = rows;

        this.subX = (int) img.getWidth() / columns;
        this.subY = (int) img.getHeight() / rows;

        animTime = 1000;
        
        getBounds().setWidth(subX * getScaleX());
        getBounds().setHeight(subY * getScaleY());

        getImageView().setViewport(new Rectangle2D(0, 0, subX, subY));
        
        setX(startX);
        setY(startY);
    }

    /**
     * Setzt die Animationsgeschwindigkeit für einen kompletten Zyklus auf die gegebene Zeit in Millisekunden.
     * Standard ist 1 Sekunde.
     *
     * @param time
     */
    public void setAnimSpeed(int time) {
        animTime = time;
    }

    
    /**
     * Startet die Animation
     */
    public void startAnim() {
        animState = AnimationState.RUNNING;

    }

    
    /**
     * Pausiert die Animation
     */
    public void pauseAnim() {
        animState = AnimationState.PAUSED;
    }

    
    /**
     * Stoppt die Animation
     */
    public void stopAnim() {
        animState = AnimationState.STOPPED;
    }

    /**
     * Zeigt das animierte Bild in dem gegebenen Frame und pausiert die Animation.
     *
     * @param frame
     */
    public void goToFrame(int frame) {
        animState = AnimationState.PAUSED;
        pointer = frame;

        int posX = pointer % columns;
        int posY = pointer / columns;

        getImageView().setViewport(new Rectangle2D(posX * subX, posY * subY, subX, subY));

    }

    
    /**
     * Frame-update.Routine
     * @param deltaT 
     */
    @Override
    protected final void update(float deltaT) {
        if (animState == AnimationState.RUNNING && columns > 1) {

            elapsed += deltaT;
            
            // Erlaubte Zeit für einen Frame der Animation
            double timePerFrame = animTime / (columns * rows);

            // Frame-pointer
            pointer = (int) (elapsed / timePerFrame);
            int posX = pointer % columns;
            int posY = pointer / columns;

            // Ermittlung des nächsten Frames
            if (pointer > columns * rows - 1) {
                pointer = 0;
                elapsed = 0;
            }
            
            
            // Setzen des Viewports auf den korrekten Ausschnitt
            getImageView().setViewport(new Rectangle2D(posX * subX, posY * subY, subX, subY));

        }

        refresh(deltaT);
    }

    /**
     * LWird pro Frame aufgerufen und ermöglicht Kindklassen das Hinzufügen einer eigenen Frame-update-routine ohne die Animationslogik zu überschreiben
     * @param deltaT 
     */
    protected void refresh(float deltaT) {

    }

}
