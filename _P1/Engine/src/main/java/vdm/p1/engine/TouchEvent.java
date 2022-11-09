package vdm.p1.engine;

public class TouchEvent {
    /**
     * The coordinates the touch event happened at.
     */
    private final float x, y;
    private final EventType type; //type of event

    public TouchEvent(float x, float y, EventType event) {
        this.x = x;
        this.y = y;
        this.type = event;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public EventType getType() {
        return type;
    }
}
