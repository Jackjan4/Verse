package de.janroslan.verse.level;

import de.janroslan.versefx.base.Level;
import de.janroslan.versefx.base.BasicNode;
import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.physics.CollisionType;
import de.janroslan.versefx.Game;
import de.janroslan.versefx.base.ObjectCollector;
import de.janroslan.verse.entities.GrassWall;
import de.janroslan.verse.entities.LevelFinish;
import de.janroslan.verse.entities.Player;
import de.janroslan.verse.entities.StoneWall;
import de.janroslan.verse.entities.Switch;
import de.janroslan.versefx.base.LevelLoader;
import de.janroslan.versefx.draw.DrawImage;
import de.janroslan.versefx.io.InputManager;
import java.util.ArrayList;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Das erste Level des Spiels
 *
 * @author Jan Roslan
 */
public class LevelOne extends Level {
    
    private Player player;
    
    private MediaPlayer mediaPlayer;
    private Media levelTheme;
    
    private LevelFinish finish;

    /**
     * Initialisieren des Levels
     *
     * @param collector - Objektsammler für den LevelLoader
     */
    @Override
    protected void init(ObjectCollector collector) {

        // Initialisieren der Hintergrundmusik
        levelTheme = new Media(getClass().getResource("/de/janroslan/verse/resources/sounds/levelTheme.aiff").toExternalForm());
        mediaPlayer = new MediaPlayer(levelTheme);
        mediaPlayer.setVolume(0.4);
        mediaPlayer.setAutoPlay(true);

        // Initialisiere Hintergrund
        DrawImage bg = new DrawImage("bg", 0, 0, new Image("/de/janroslan/verse/resources/sky.png"), 1.7, 2, 0);
        collector.add(bg);

        // Init Wände
        initWalls(collector);

        // Init Türen
        ArrayList<BasicNode[]> doors = initDoors(collector);

        // Init Schalter
        initObjs(collector, doors);

        // Spieler initialisieren
        player = new Player(100, 210);
        collector.registerCollider(player, CollisionType.AABox2D, "wall");
        collector.add(player);

        // Zeilflagge initialisieren
        finish = new LevelFinish(Game.W_WIDTH - 70, Game.W_HEIGHT - 74);
        collector.registerCollider(finish, CollisionType.AABox2D, "player");
        collector.add(finish);
        
    }

    /**
     * Initialisieren der abgrenzenden Wände
     *
     * @param collector
     */
    public void initWalls(ObjectCollector collector) {
        ArrayList<BasicNode> list = new ArrayList<BasicNode>() {
            {
                // Untere Levelabtrennung
                for (int i = 0; i <= 1280; i += 40) {
                    add(new GrassWall(i, Game.W_HEIGHT - 40));
                }

                // Linke Levelabtrennung
                for (int i = 0; i <= 720; i += 40) {
                    add(new GrassWall(0, i));
                }

                // Obere Levelabtrennung
                for (int i = 0; i <= 1280; i += 40) {
                    add(new GrassWall(i, 0));
                }

                // Rechte Wandabtrennung
                for (int i = 0; i <= 720; i += 40) {
                    add(new GrassWall(Game.W_WIDTH - 40, i));
                }

                // Wandbereich links
                for (int i = 0; i < 2600; i += 40) {
                    add(new GrassWall(i % 320, 280 + 40 * (int) (i / 320)));
                }

                // Wandbereich oben linkjs
                for (int i = 0; i < 640; i += 40) {
                    add(new GrassWall(40 + i % 160, 40 + 40 * (int) (i / 160)));
                }
                add(new GrassWall(200, 160));
                add(new GrassWall(240, 160));
                add(new GrassWall(320, 320));
                add(new GrassWall(400, 220));

                // Wandbereich oben rechts
                add(new GrassWall(520, 40));
                add(new GrassWall(520, 80));
                add(new GrassWall(520, 120));
                add(new GrassWall(520, 160));
                add(new GrassWall(520, 200));
                add(new GrassWall(520, 240));
                add(new GrassWall(520, 40));
                add(new GrassWall(920, 280));
                
                for (int i = 0; i < 720; i += 40) {
                    add(new GrassWall(560 + i % 360, 200 + 40 * (int) (i / 360)));
                }

                // Wandbereich rechts
                for (int i = 0; i < 2980; i += 40) {
                    add(new GrassWall(680 + i % 600, 360 + 40 * (int) (i / 600)));
                }
                add(new GrassWall(600, 360));
                add(new GrassWall(560, 360));
                add(new GrassWall(520, 360));
                add(new GrassWall(600, 400));
                add(new GrassWall(560, 400));
                add(new GrassWall(520, 400));
                add(new GrassWall(600, 520));
                add(new GrassWall(560, 520));
                add(new GrassWall(520, 520));
                
                add(new GrassWall(640, 360));
                add(new GrassWall(640, 400));
                add(new GrassWall(640, 440));
                add(new GrassWall(640, 480));
                add(new GrassWall(640, 520));
                
            }
        };
        
        collector.add(list);
        for (Collidable o : list) {
            collector.registerCollider(o, CollisionType.AABox2D);
        }
    }

    /**
     * Initialisieren der Türen, die von den Schaltern betätigt werden können
     *
     * @param collector
     * @return - Liste an Türen, zusammengefasst in Objektgruppen
     */
    private ArrayList<BasicNode[]> initDoors(ObjectCollector collector) {
        ArrayList<BasicNode[]> doors = new ArrayList<>();

        // Erstellen der Türen
        ArrayList<BasicNode> list = new ArrayList<BasicNode>() {
            {

                // Door 0
                StoneWall w1 = new StoneWall(280, Game.W_HEIGHT - 80);
                StoneWall w2 = new StoneWall(280, Game.W_HEIGHT - 120);
                add(w1);
                add(w2);
                doors.add(new BasicNode[]{w1, w2});

                // Door 1
                StoneWall w3 = new StoneWall(360, 400);
                StoneWall w4 = new StoneWall(400, 400);
                StoneWall w5 = new StoneWall(320, 400);
                StoneWall w6 = new StoneWall(440, 400);
                StoneWall w7 = new StoneWall(480, 400);
                add(w3);
                add(w4);
                add(w5);
                add(w6);
                add(w7);
                doors.add(new BasicNode[]{w3, w4, w5, w6, w7});

                // Door 2
                StoneWall w8 = new StoneWall(320, 520);
                StoneWall w9 = new StoneWall(360, 520);
                StoneWall w10 = new StoneWall(400, 520);
                StoneWall w11 = new StoneWall(440, 520);
                StoneWall w12 = new StoneWall(480, 520);
                add(w8);
                add(w9);
                add(w10);
                add(w11);
                add(w12);
                doors.add(new BasicNode[]{w8, w9, w10, w11, w12});

                // Door 3
                StoneWall w13 = new StoneWall(640, 280);
                StoneWall w14 = new StoneWall(640, 320);
                add(w13);
                add(w14);
                doors.add(new BasicNode[]{w13, w14});

                // Door 4
                StoneWall w15 = new StoneWall(720, 560);
                StoneWall w16 = new StoneWall(720, 600);
                StoneWall w17 = new StoneWall(720, 640);
                add(w15);
                add(w16);
                add(w17);
                doors.add(new BasicNode[]{w15, w16, w17});

                // Door 5
                StoneWall w18 = new StoneWall(880, 560);
                StoneWall w19 = new StoneWall(880, 600);
                StoneWall w20 = new StoneWall(880, 640);
                add(w18);
                add(w19);
                add(w20);
                doors.add(new BasicNode[]{w18, w19, w20});

                // Door 6
                StoneWall w21 = new StoneWall(680, 40);
                StoneWall w22 = new StoneWall(680, 80);
                StoneWall w23 = new StoneWall(680, 120);
                StoneWall w24 = new StoneWall(680, 160);
                add(w21);
                add(w22);
                add(w23);
                add(w24);
                doors.add(new BasicNode[]{w21, w22, w23, w24});

                // Door 7
                StoneWall w26 = new StoneWall(960, 560);
                StoneWall w27 = new StoneWall(960, 600);
                StoneWall w28 = new StoneWall(960, 640);
                add(w26);
                add(w27);
                add(w28);
                doors.add(new BasicNode[]{w26, w27, w28});

                // Door 8
                StoneWall w29 = new StoneWall(160, 200);
                StoneWall w30 = new StoneWall(160, 240);
                add(w29);
                add(w30);
                doors.add(new BasicNode[]{w29, w30});
            }
        };
        
        collector.add(list);

        // Registrieren der Türen im Kollisionssystem
        for (BasicNode n : list) {
            collector.registerCollider(n, CollisionType.AABox2D);
        }

        // Diese Türen sind standarddmäßig geöffnet
        triggerDoor(doors.get(1));
        triggerDoor(doors.get(4));
        triggerDoor(doors.get(5));
        
        return doors;
    }

    /**
     * Initialisieren der Schalter, des Ziels und Verknüpfung der Schalter mit
     * den Türen
     *
     * @param collector
     */
    private void initObjs(ObjectCollector collector, ArrayList<BasicNode[]> doors) {

        //Schalter 0
        Switch s0 = new Switch(60, 245);
        s0.setOnTriggered(handler -> {
            triggerDoor(doors.get(8));
            triggerDoor(doors.get(1));
        });
        collector.registerCollider(s0, CollisionType.AABox2D, "player");
        collector.add(s0);

        // Schalter 1
        Switch s1 = new Switch(100, Game.W_HEIGHT - 2 * 40 + 5);
        s1.setOnTriggered(handler -> {
            triggerDoor(doors.get(0));
            triggerDoor(doors.get(5));
            
        });
        collector.registerCollider(s1, CollisionType.AABox2D, "player");
        collector.add(s1);

        // Schalter 2
        Switch s2 = new Switch(220, 125);
        s2.setOnTriggered(handler -> {
            triggerDoor(doors.get(4));
            
            triggerDoor(doors.get(3));
        });
        collector.registerCollider(s2, CollisionType.AABox2D, "player");
        collector.add(s2);

        // Schalter 3
        Switch s3 = new Switch(560, 485);
        s3.setOnTriggered(handler -> {
            triggerDoor(doors.get(2));
            triggerDoor(doors.get(5));
        });
        collector.registerCollider(s3, CollisionType.AABox2D, "player");
        collector.add(s3);

        // Schalter 4
        Switch s4 = new Switch(600, 165);
        s4.setOnTriggered(handler -> {
            triggerDoor(doors.get(6));
            triggerDoor(doors.get(7));
        });
        collector.registerCollider(s4, CollisionType.AABox2D, "player");
        collector.add(s4);
        
        Switch s5 = new Switch(720, 165);
        s5.setOnTriggered(handler -> {
            triggerDoor(doors.get(6));
        });
        collector.registerCollider(s5, CollisionType.AABox2D, "player");
        collector.add(s5);

        // Schalter 6
        Switch s6 = new Switch(400, Game.W_HEIGHT - 2 * 40 + 5);
        s6.setOnTriggered(handler -> {
            triggerDoor(doors.get(0));
        });
        collector.registerCollider(s6, CollisionType.AABox2D, "player");
        collector.add(s6);
        
    }

    /**
     * Frame-Update-Routine
     *
     * @param deltaT - Zeit in millisekunden seit dem letzten Update
     */
    @Override
    protected void update(float deltaT) {
        player.tick(deltaT);

        // Spieler lädt Level neu
        if (InputManager.getGlobalInput().IsKeyDown(KeyCode.R)) {
            mediaPlayer.stop();
            LevelLoader.getInstance().loadLevel(new LevelOne());
        }

        // Spieler hat das Level abgeschlossen
        if (finish.isFinished()) {
            mediaPlayer.stop();
            try {
                Thread.sleep(3000);
                LevelLoader.getInstance().loadLevel(new MainMenu());
            } catch (InterruptedException ex) {
                
            }
            
        }
    }

    /**
     * *
     * Schaltet eine Feld von Objekten (Benutzt für die Türen) in ihrer
     * Sichtbarkeit um
     *
     * @param door
     */
    private void triggerDoor(BasicNode[] door) {
        for (BasicNode n : door) {
            n.setActive(!n.isActive());
        }
    }
    
}
