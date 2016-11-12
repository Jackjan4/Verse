package de.janroslan.versefx.physics;

/**
 * Stellt Quader dar, welches für das Kollisionssystem von Objekten dient.
 *
 * @author Jackjan
 */
public class BoundingBox {

    private double x;
    private double y;
    private double z;
    private double width;
    private double height;
    private double depth;
    private double rotX;
    private double rotY;
    private double rotZ;
    private boolean active;
    private CollisionType type;

    /**
     * Gibt den Aktivitätsstatus der BoundingBox zurück.
     *
     * @return
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Aktiviert / Deaktiviert diese BoundinBox
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gibt die x-Rotation dieser Box zurück
     *
     * @return
     */
    public double getRotX() {
        return rotX;
    }

    /**
     * Legt die x-Rotation fest
     *
     * @param rotX
     */
    public void setRotX(double rotX) {
        this.rotX = rotX;
    }

    /**
     * Gibt die y-Rotation dieser Box zurück
     *
     * @return
     */
    public double getRotY() {
        return rotY;
    }

    /**
     * Legt die y-Rotation fest
     *
     * @param rotY
     */
    public void setRotY(double rotY) {
        this.rotY = rotY;
    }

    /**
     * Gibt die z-Rotation dieser Box zurück
     *
     * @return
     */
    public double getRotZ() {
        return rotZ;
    }

    /**
     * Legt die z-Rotation fest
     *
     * @param rotZ
     */
    public void setRotZ(double rotZ) {
        this.rotZ = rotZ;
    }

    /**
     * Konstruktor mit Angabe des Kollisionstyps
     * @param x
     * @param y
     * @param z
     * @param width
     * @param height
     * @param depth
     */
    public BoundingBox(double x, double y, double z, double width, double height, double depth, CollisionType type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.type = type;

        active = true;
    }

    /**
     * Konstruktor ohne Kollisonstyp
     * @param x
     * @param y
     * @param z
     * @param width
     * @param height
     * @param depth
     */
    public BoundingBox(double x, double y, double z, double width, double height, double depth) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.depth = depth;

        active = true;
    }

    // Copy constructor - Erzeugt ein Klon von der BoundingBox 'ori'
    public BoundingBox(BoundingBox ori) {
        this.x = ori.x;
        this.y = ori.y;
        this.z = ori.z;
        this.width = ori.width;
        this.height = ori.height;
        this.depth = ori.depth;
        this.type = ori.getType();

        active = true;
    }

    /**
     * Kollision zwischen zwei BoundingBoxen
     *
     * @param col
     * @return
     */
    private boolean intersectsAABoxAABox(BoundingBox box) {

        return (active
                && box.isActive()
                && box.x + box.width >= x
                && box.y + box.height >= y
                && box.z + box.depth >= z
                && box.x <= x + width
                && box.y <= y + height
                && box.z <= z + depth);
    }

    /**
     * Kollision zwischen zwei Spheren
     *
     * @param box
     * @return
     */
    private boolean intersectsSphereSphere(BoundingBox box) {

        double radius = Math.max(width, Math.max(height, depth)) / 2.0;
        double otherRad = Math.max(box.width, Math.max(box.height, box.depth)) / 2.0;

        return false;
    }

    /**
     * Kollision zwischen einer Sphere und einer BoundingBox
     *
     * @param sphere
     * @param box
     * @return
     */
    private boolean intersectsSphereAABox(BoundingBox sphere, BoundingBox box) {
        return false;
    }

    /**
     * Gibt den Kollisionstyp dieser BoundingBox zurück
     *
     * @return
     */
    public CollisionType getType() {
        return type;
    }

    /**
     * Legt den Kollisionstyp dieser BoundingBox fest
     *
     * @param type
     */
    public void setType(CollisionType type) {
        this.type = type;
    }

    /**
     * Prüft ob diese BoundingBox mit 'col' kollidiert. Dabei wird beachtet was
     * für einen Kollisionstyp beide Boxen nutzen. Je nach Kollisionstypen
     * dieser beiden Boxen wird eine andere Rechnung benutzt
     *
     * @param box
     * @return
     */
    public boolean intersects(BoundingBox box) {
        boolean result = false;

        // Keine Kollisionstypen gesetzt
        if (type == null || box.getType() == null) {
            return false;
        }
        switch (type.getVal() + box.getType().getVal()) {

            // Kollision zwischen: Sphere - Sphere
            case 6:
                result = intersectsSphereSphere(box);
                break;

            // Kollison zwischen: Sphere - AABox
            case 8:
                if (type == CollisionType.AABox2D) {
                    result = intersectsSphereAABox(box, this);
                } else {
                    result = intersectsSphereAABox(this, box);
                }
                break;

            // Kollision zwischen: AABox - AABox
            case 10:
                result = intersectsAABoxAABox(box);
                break;
        }
        return result;
    }

    
    
    // Getter und Setter
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setWidth(double val) {
        this.width = val;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDepth() {
        return depth;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
