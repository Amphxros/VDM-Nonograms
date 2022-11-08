package vdm.p1.logic;

import java.util.Vector;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;

public abstract class GameObject implements IGameObject {
    Vector2D position;
    int width;
    int height;
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
        for (GameObject child : getChildren()) {
            child.handleParentScreenChange();
        }
    }
}
