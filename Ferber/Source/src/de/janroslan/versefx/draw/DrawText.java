package de.janroslan.versefx.draw;

import de.janroslan.versefx.base.BasicNode;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Text, der innerhalb eines Levels gezeichnet werden.
 * @author jackjan
 */
public class DrawText extends BasicNode {
    
    private boolean isClicked;

    private EventHandler<? super MouseEvent> handler;
    

    private DrawText(String tag, Text txt, double startX, double starty, double size, String fontFamily, int layer)
    {
        super(tag, txt, 0);
        
        // Setzen des Fonts
        if (fontFamily != null) {
            getText().setFont(Font.font(fontFamily, size));
        } else {
            getText().setFont(Font.font(size));
        }
        
        isClicked = false;

        // Maus Klickevent
        getText().setOnMouseClicked(event -> {
            isClicked = true;
            if (handler != null) {
                handler.handle(event);
            }
        });
        
        // Maus Release-Event
        getText().setOnMouseReleased(event -> {
            isClicked = false;
            if (handler != null) {
                handler.handle(event);
            }
        });
        
        
    }
    
    
    
    public DrawText(String tag, String txt, double startX, double startY, double size, String fontFamily, int layer) {
        this(tag,new Text(startX, startY, txt), startX,  startY, size, fontFamily, layer);
        
    }
    
    
    /**
     * Gibt den angezeigten Text zurück
     * @return 
     */
    public final Text getText()
    {
        return (Text)getNode();
    }

    
    /**
     * Legt ein Event fest, welches ausgeführt wird, wenn der Text mit der Maus angeklickt wird.
     * @param handler 
     */
    public void setOnMouseClick(EventHandler<? super MouseEvent> handler) {
        this.handler = handler;
    }
    
    
    /**
     * Legt ein Event fest, welches ausgeführt wird, wenn die Maus über dem Text schwebt
     * @param handler 
     */
    public void setOnMouseHover(EventHandler<? super MouseEvent> handler) 
    {
        getText().setOnMouseEntered(handler);
    }
    
    
    /**
     * Legt ein Event fest, welches ausgeführt wird, wenn die Maus nicht mehr über dem Text schwebt
     * @param handler 
     */
    public void setOnMouseHoverEnd(EventHandler<? super MouseEvent> handler) 
    {
        getText().setOnMouseExited(handler);
    }
    
    
    /**
     * Legt fest, ob der Text unterstrichen ist
     * @param val 
     */
    public void setUnderline(boolean val)
    {
        getText().setUnderline(val);
    }

    
    /**
     * Frame-update-Routine
     * @param deltaT 
     */
    @Override
    public void update(float deltaT) {
        
    }

}
