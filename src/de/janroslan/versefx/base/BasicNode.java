/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.janroslan.versefx.base;

import de.janroslan.versefx.physics.Collidable;
import de.janroslan.versefx.physics.CollisionType;
import de.janroslan.versefx.physics.BoundingBox;
import de.timetoerror.jputils.collections.OverflowStack;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;

/**
 *
 * @author jackjan
 */
public abstract class BasicNode implements Timeable, Updateable, Collidable {

    // Zeitspeicher
    private OverflowStack<TimeStamp> timeBuffer;

    private boolean reverse;

    // The time in millis which should be reversed
    private int reverseTime;
    private long triggerTime;

    private Node node;

    private ObjectManager man;

    private long last;

    // An Axis Aligned BoundingBox
    protected BoundingBox box;

    // Einzigartiger Name des Objekts
    protected String tag;

    public BasicNode(String tag, Node node, int time) {
        this.tag = tag;
        if (time > 0) {
            timeBuffer = new OverflowStack<>(time);
        }

        this.node = node;
        box = new BoundingBox(getX(), getY(), getZ(), getWidth(), getHeight(), getDepth());

        setActive(true);
    }

    protected void setNode(Node node) {
        this.node = node;
    }

    public final Node getNode() {
        return node;
    }

    public void setManager(ObjectManager man) {
        this.man = man;
    }

    protected ObjectManager getManager() {
        return man;
    }

    public final double getWidth() {
        return getNode().getBoundsInLocal().getWidth() * getNode().getScaleX();
    }

    public final double getHeight() {
        return getNode().getBoundsInLocal().getHeight() * getNode().getScaleY();
    }

    public final double getDepth() {
        return getNode().getBoundsInLocal().getDepth() * getNode().getScaleZ();
    }

    public final void setX(double val) {
        getNode().setTranslateX(val - (getNode().getBoundsInLocal().getWidth() - getWidth()) / 2.0);
        box.setX(val);
    }

    public final void setY(double val) {
        getNode().setTranslateY(val - (getNode().getBoundsInLocal().getHeight() - getHeight()) / 2.0);
        box.setY(val);
    }

    public final void setZ(double val) {
        getNode().setTranslateZ(val - (getNode().getBoundsInLocal().getDepth() - getDepth()) / 2.0);
        box.setZ(val);
    }

    public final double getX() {
        return getNode().getTranslateX() + (getNode().getBoundsInLocal().getWidth() - getWidth()) / 2.0;
    }

    public final double getY() {
        return getNode().getTranslateY() + (getNode().getBoundsInLocal().getHeight() - getHeight()) / 2.0;
    }

    public final double getZ() {
        return getNode().getTranslateZ() + (getNode().getBoundsInLocal().getDepth() - getDepth()) / 2.0;
    }

    public final double getRotation() {
        return getNode().getRotate();
    }

    public final void destroy() {
        getManager().destroyObject(this);
    }

    @Override
    public final void tick(float deltaT) {

        if (!reverse) {
            if (timeBuffer != null) {
                TimeStamp stamp = new TimeStamp(getX(), getY(), getZ(), getRotation());
                if (!stamp.equals(timeBuffer.peek())) {
                    timeBuffer.add(stamp);
                }
            }
            update(deltaT);
        } else if (!timeBuffer.isEmpty()) {
            TimeStamp stamp = timeBuffer.pop();

            if (stamp.getTime() < triggerTime - reverseTime) {
                reverse = false;
                return;
            }
            setX(stamp.getX());
            setY(stamp.getY());
            setZ(stamp.getZ());

        } else {
            reverse = false;
        }
    }

    protected abstract void update(float deltaT);

    @Override
    public final BoundingBox getBounds() {
        return box;
    }

    public void setActive(boolean val) {
        getNode().setVisible(val);
        getNode().setDisable(!val);
        box.setActive(val);
    }

    @Override
    public final boolean isActive() {
        return !getNode().isDisable();
    }

    @Override
    public CollisionType getType() {
        return box.getType();
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setColType(CollisionType type) {
        box.setType(type);
    }

    @Override
    public ArrayList<Collidable> getCollisions() {
        return man.getCollisions(this);
    }

    public void rotateX(double val) {
        getNode().setRotationAxis(Rotate.X_AXIS);
        getNode().setRotate(getNode().getRotate() + val);
        box.setRotX(getNode().getRotate() + val);
    }

    public void rotateY(double val) {
        getNode().setRotationAxis(Rotate.Y_AXIS);
        getNode().setRotate(getNode().getRotate() + val);
        box.setRotY(getNode().getRotate() + val);
    }

    public void rotateZ(double val) {
        getNode().setRotationAxis(Rotate.Z_AXIS);
        getNode().setRotate(getNode().getRotate() + val);
        box.setRotZ(getNode().getRotate() + val);
    }

    public final void moveX(double val) {
        getNode().setTranslateX(getNode().getTranslateX() + val);
        box.setX(getX());
    }

    public final void moveColX(double val) {
        man.addToColQueue(this, val,0,0);
    }

    public final void moveColY(double val) {
        man.addToColQueue(this, 0,val,0);
    }

    public final void moveY(double val) {
        getNode().setTranslateY(getNode().getTranslateY() + val);
        box.setY(getY());
    }

    public final void moveZ(double val) {
        getNode().setTranslateZ(getNode().getTranslateZ() + val);
        box.setZ(getZ());
    }

    @Override
    public boolean hasTimeStamp() {
        return !timeBuffer.isEmpty();
    }

    @Override
    public TimeStamp getTimeStamp() {
        return timeBuffer.pop();
    }

    @Override
    public void saveTimeStamp(TimeStamp stamp) {
        timeBuffer.add(stamp);
    }

    @Override
    public void reverseTime(int time) {
        reverse = true;
        reverseTime = time;
        triggerTime = System.currentTimeMillis();
    }

    @Override
    public void intersects(Collidable col) {

    }

    public final void scaleX(double val) {
        getNode().setScaleX(val);
        box.setWidth(getWidth());
        box.setX(getX());
    }

    public final void scaleY(double val) {
        getNode().setScaleY(val);
        box.setHeight(getHeight());
        box.setY(getY());
    }

    public final void scaleZ(double val) {
        getNode().setScaleZ(val);
        box.setDepth(getDepth());
        box.setZ(getZ());
    }

    public final double getScaleX() {
        return getNode().getScaleX();
    }

    public final double getScaleY() {
        return getNode().getScaleY();
    }

    public final double getScaleZ() {
        return getNode().getScaleZ();
    }

}
