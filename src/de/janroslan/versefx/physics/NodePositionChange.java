package de.janroslan.versefx.physics;

import de.janroslan.versefx.base.BasicNode;

/**
 * Hilfklasse, die eine BasicNode und ihre neue Positionsveränderung zusammenfast.
 * Wird in der Kollisions-Queue benutzt
 * @author jackjan
 */
public class NodePositionChange {
    private double x;
    private double y;
    private double z;
    
    private BasicNode node;

    public NodePositionChange(BasicNode node,double x, double y, double z ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.node = node;
    }

    public double getX() {
        return x;
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public BasicNode getNode() {
        return node;
    }

    public void setNode(BasicNode node) {
        this.node = node;
    }

    
    
}
