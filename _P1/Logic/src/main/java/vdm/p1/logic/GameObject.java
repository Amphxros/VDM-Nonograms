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

    public GameObject(int x, int y, int w, int h) {
        this.position = new Vector2D(x, y);
        this.width = w;
        this.height = h;
    }

    @Override
    public Vector2D getPosition() {
        return position;
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

    public void addChild(GameObject gameObject) {
        if (gameObject.parent != null) {
            throw new RuntimeException("The given GameObject already has a parent");
        }

        gameObject.parent = this;
        children.add(gameObject);
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
}
