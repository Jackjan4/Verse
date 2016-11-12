package de.janroslan.verse.level;

import de.janroslan.versefx.base.LevelLoader;
import de.janroslan.versefx.base.Level;
import de.janroslan.versefx.draw.DrawImage;
import de.janroslan.versefx.draw.DrawText;
import de.janroslan.versefx.Game;
import de.janroslan.versefx.base.ObjectCollector;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Das Hauptmenü des Spiels, welches das Startlevel der Anwendung darstellt.
 *
 * @author Jackjan
 */
public class MainMenu extends Level {

    private AudioClip buttonHoverSound;
    private Media menuTheme;
    private MediaPlayer mplayer;

    private DrawText btnExit;
    private DrawText btnStart;
    private Random rdm;
    private DrawImage[] clouds;

    /**
     * Level initialisieren
     *
     * @param collector - Objektsammler
     */
    @Override
    protected void init(ObjectCollector collector) {

        rdm = new Random();

        // Init Hintergrundmusik
        menuTheme = new Media(getClass().getResource("/de/janroslan/verse/resources/sounds/menu.aiff").toExternalForm());
        mplayer = new MediaPlayer(menuTheme);
        mplayer.setVolume(0.7);
        mplayer.setAutoPlay(true);

        // Init Maus Hover Sound
        buttonHoverSound = new AudioClip(getClass().getResource("/de/janroslan/verse/resources/sounds/click.wav").toString());

        initPictures(collector);
        initObjs(collector);
        initTexts(collector);
        initButtons(collector);
        

    }

    /**
     * Initialisieren der Bilder im Hautpmenü
     *
     * @param collector
     */
    public void initPictures(ObjectCollector collector) {
        // Hintergrund initialisieren
        DrawImage bg = new DrawImage("bg", 0, 0, new Image("/de/janroslan/verse/resources/sky.png"), 1.7, 2, 0);

        // Logo initialisieren
        DrawImage logo = new DrawImage("logo", 0, 0, new Image("/de/janroslan/verse/resources/logo.png"), 0.2, 1, 0);
        logo.setX(Game.W_WIDTH / 2 - logo.getWidth() / 2);

        // Ferber logo initialisieren
        DrawImage fimg = new DrawImage("", 100, 0, new Image("/de/janroslan/verse/resources/ferber.png"), 0.3, 1, 0);
        fimg.setY(Game.W_HEIGHT - fimg.getHeight() - 40);

        // Dem ObjektSammler übergeben
        collector.add(bg);
        collector.add(logo);

        collector.add(fimg);
    }

    /**
     * Texte initialisieren
     *
     * @param collector
     */
    public void initTexts(ObjectCollector collector) {
        // "Für" Text initialisieren
        DrawText txtFor = new DrawText("", "Für", 20, 0, 32, null, 2);
        txtFor.setY(Game.W_HEIGHT - txtFor.getHeight() - 10);

        // Text "Von Jan-Philipp Roslan" initialisieren
        DrawText txtJan = new DrawText("", "von Jan-Philipp Roslan", 0, 0, 28, null, 2);
        txtJan.setX(Game.W_WIDTH - txtJan.getWidth() - 10);
        txtJan.setY(Game.W_HEIGHT - txtJan.getHeight() - 10);

        // Texte dem ObjektSammler übergeben
        collector.add(txtFor);
        collector.add(txtJan);
    }

    /**
     * Menü-buttons initialisieren
     *
     * @param collector
     */
    public void initButtons(ObjectCollector collector) {
        // Button Start Iinitialisierung
        btnStart = new DrawText("text", "Spiel starten", 30, 30, 30, null, 2);
        btnStart.setX(Game.W_WIDTH / 2 - btnStart.getWidth() / 2);
        btnStart.setY(Game.W_HEIGHT / 2 - btnStart.getHeight() / 2 - btnStart.getHeight() - 20);

        // Button Exit Iinitialisierung
        btnExit = new DrawText("text", "Beenden", 30, 30, 30, null, 1);
        btnExit.setX(Game.W_WIDTH / 2 - btnExit.getWidth() / 2);
        btnExit.setY(Game.W_HEIGHT / 2 - btnExit.getHeight() / 2);

        // Button Start Ereignisse
        btnStart.setOnMouseHover(event -> {
            buttonHoverSound.play();
            btnStart.setUnderline(true);
        });

        btnStart.setOnMouseHoverEnd(event -> {
            btnStart.setUnderline(false);
        });

        btnStart.setOnMouseClick(event -> {
            mplayer.stop();

            LevelLoader.getInstance().loadLevel(new LevelOne(), false, true);
        });

        // Button Exit Ereignisse
        btnExit.setOnMouseHover(event -> {
            buttonHoverSound.play();
            btnExit.setUnderline(true);
        });

        btnExit.setOnMouseHoverEnd(event -> {
            btnExit.setUnderline(false);
        });

        btnExit.setOnMouseClick(event -> {
            System.exit(0);
        });

        collector.add(btnStart);
        collector.add(btnExit);
    }

    /**
     * Objekte (Wolken) initialisieren
     *
     * @param collector
     */
    public void initObjs(ObjectCollector collector) {
        clouds = new DrawImage[5];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new DrawImage("cloud", rdm.nextInt(Game.W_WIDTH), rdm.nextInt(251), new Image("/de/janroslan/verse/resources/cloud.png"), 0.2, 1, 0);
        }
        collector.add(clouds);
    }

    /**
     * Frame-update-Routine
     *
     * @param deltaT
     */
    @Override
    protected void update(float deltaT) {
        for (DrawImage c : clouds) {
            c.moveX(-0.05 * deltaT);
            if (c.getX() + c.getWidth() < 0) {
                c.setX(Game.W_WIDTH + rdm.nextInt(Game.W_WIDTH / 2));
            }
        }
    }

}
