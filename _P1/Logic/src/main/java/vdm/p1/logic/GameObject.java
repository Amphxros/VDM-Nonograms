package vdm.p1.logic;

import java.util.Vector;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.VerticalAlignment;

public abstract class GameObject implements IGameObject {
    Vector2D position;
    int width;
    int height;
    HorizontalAlignment horizontalAlignment = HorizontalAlignment.NONE;
    VerticalAlignment verticalAlignment = VerticalAlignment.NONE;
    Color backgroundColor = null;
    GameObject parent = null;
    Vector<GameObject> children = new Vector<>();

    public GameObject() {
        this(0, 0, 0, 0);
    }

    public GameObject(int x, int y, int w, int h) {
        this.position = new Vector2D(x, y);
        this.width = w;
        this.height = h;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * Sets the horizontal alignment, applies when {@link #handleParentScreenChange()} is called,
     * unless {@link HorizontalAlignment#NONE} is passed, which resets to a NOP behaviour.
     * @param horizontalAlignment The alignment to use.
     * @return The updated {@link GameObject} instance.
     */
    public GameObject setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * Sets the vertical alignment, applies when {@link #handleParentScreenChange()} is called,
     * unless {@link VerticalAlignment#NONE} is passed, which resets to a NOP behaviour.
     * @param verticalAlignment The alignment to use.
     * @return The updated {@link GameObject} instance.
     */
    public GameObject setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return this;
    }

    // TODO: Keep or remove? Added for layout debugging purposes.
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    // TODO: Keep or remove? Added for layout debugging purposes.
    public GameObject setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    @Override
    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameObject getParent() {
        return parent;
    }

    public Vector<GameObject> getChildren() {
        return children;
    }

    /**
     * Adds a child to the GameObject.
     *
     * @param gameObject The object to add as a child.
     * @return The updated {@link GameObject} instance.
     * @throws RuntimeException When the given object already has a parent.
     */
    public GameObject addChild(GameObject gameObject) {
        if (gameObject.parent != null) {
            throw new RuntimeException("The given GameObject already has a parent");
        }

        gameObject.parent = this;
        children.add(gameObject);
        return this;
    }

    @Override
    public void render(IGraphics graphics) {
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
            graphics.fillRectangle(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
        }

        for (GameObject child : getChildren()) {
            child.render(graphics);
        }
    }

    @Override
    public void update(double t) {
        for (GameObject child : getChildren()) {
            child.update(t);
        }
    }

    @Override
    public boolean handleInput(TouchEvent event) {
        for (GameObject child : getChildren()) {
            if (child.handleInput(event)) {
                return true;
            }
        }

        return false;
    }

    /**
     * An event called when the parent's size or position change. This is not called automatically,
     * and should be called manually based on whether or not the size has changed.
     */
    public void handleParentScreenChange() {
        handleHorizontalAlignment();
        handleVerticalAlignment();

        for (GameObject child : getChildren()) {
            child.handleParentScreenChange();
        }
    }

    /**
     * Inherits the parent's position and size. Internally calls {@link #inheritParentPosition()}
     * followed by {@link #inheritParentSize()}.<br><br>
     * <p>
     * This is useful for container {@link GameObject}s that share the area and position of the
     * parent, but restrain the sizes of its children.
     */
    public void inheritParentArea() {
        inheritParentPosition();
        inheritParentSize();
    }

    /**
     * Inherits the parent's position. Internally calls {@link #setPosition(Vector2D)}
     * with the value from {@link #getParent()}'s {@link #getPosition()}.
     */
    public void inheritParentPosition() {
        setPosition(getParent().getPosition());
    }

    /**
     * Inherits the parent's size. Internally calls {@link #setWidth(int)} and {@link #setHeight(int)}
     * with the results of {@link #getParent()}'s {@link #getWidth()} and {@link #getHeight()},
     * respectively.
     */
    public void inheritParentSize() {
        setWidth(getParent().getWidth());
        setHeight(getParent().getHeight());
    }

    private void handleHorizontalAlignment() {
        switch (getHorizontalAlignment()) {
            case NONE:
                break;
            case LEFT:
                getPosition().setX(getParent().getPosition().getX());
                break;
            case CENTRE:
                getPosition().setX(getParent().getPosition().getX() + (int) ((getParent().getWidth() - getWidth()) / 2.0));
                break;
            case RIGHT:
                getPosition().setX(getParent().getPosition().getX() + getParent().getWidth() - getWidth());
                break;
        }
    }

    private void handleVerticalAlignment() {
        switch (getVerticalAlignment()) {
            case NONE:
                break;
            case TOP:
                getPosition().setY(getParent().getPosition().getY());
                break;
            case MIDDLE:
                getPosition().setY(getParent().getPosition().getY() + (int) ((getParent().getHeight() - getHeight()) / 2.0));
                break;
            case BOTTOM:
                getPosition().setY(getParent().getPosition().getY() + getParent().getHeight() - getHeight());
                break;
        }
    }
}
