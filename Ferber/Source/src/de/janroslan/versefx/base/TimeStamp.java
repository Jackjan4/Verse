package de.janroslan.versefx.base;


/**
 * Zeitstempel für eine Position eines Objektes zu einer gegebenen Zeit
 * @author Jackjan
 */
public class TimeStamp
{

    private double x;
    private double y;
    private double z;
    private long time;
    private double rot;

    public TimeStamp(double x, double y, double z, double rot) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.rot = rot;
        time = System.currentTimeMillis();
    }
    
    

    public TimeStamp() {
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeStamp other = (TimeStamp) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        return Double.doubleToLongBits(this.rot) == Double.doubleToLongBits(other.rot);
    }
    
    
    
    // Getter & Setter

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRot() {
        return rot;
    }

    public void setRot(double rot) {
        this.rot = rot;
    }

    public long getTime() {
        return time;
    }
    
    
    
}
