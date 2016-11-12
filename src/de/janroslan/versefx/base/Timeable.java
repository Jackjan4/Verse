package de.janroslan.versefx.base;

/**
 * Gemeinsames Interface für den Zugriff auf Objekte, die sich rückwärts in der Zeit bewegen können.
 * @author Jackjan
 */
public interface Timeable {
    
    
    /**
     * Gibt zurück ob noch ein Zeitstempel existiert
     * @return 
     */
    public boolean hasTimeStamp();
    
    
    /**
     * Gibt eine Zeitstempel zurück und löscht in von der Liste der verfügbaren Zeitstempel
     * @return 
     */
    public TimeStamp getTimeStamp();
    
    
    /**
     * Speichert einen Zeitstempel
     * @param stamp 
     */
    public void saveTimeStamp(TimeStamp stamp);
    
    
    /**
     * Bewegt das Objekt in der Zeit zurück um die gegeben Anzahl an Millisekunden
     * @param millis 
     */
    public void reverseTime(int millis);
}
