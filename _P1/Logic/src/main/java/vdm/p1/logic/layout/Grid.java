package vdm.p1.logic.layout;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameObject;

public class Grid extends GameObject {
    private final FlowDirection direction;
    private final GridElement[] elements;

    public Grid(int values, FlowDirection direction) {
        super();
        this.direction = direction;
        this.elements = new GridElement[values];
        for (int i = 0; i < values; ++i) this.elements[i] = new GridElement();
    }

    public FlowDirection getDirection() {
        return direction;
    }

    /**
     * Gets the {@link GridElement}s that compose this {@link Grid} instance.
     * @return The {@link GridElement}s managed by this {@link Grid} instance.
     */
    public GridElement[] getElements() {
        return elements;
    }

    /**
     * Sets an element at an index.
     * @param index The index to set the {@link GameObject} at.
     * @param gameObject The instance to set at the index.
     */
    public void setElement(int index, GameObject gameObject) {
        if (index < 0) throw new RuntimeException("index cannot be negative");
        if (index >= elements.length) throw new RuntimeException("index cannot be superior to elements.length");

        elements[index].addChild(gameObject);
    }

    @Override
    public void update(double t) {
        for (GridElement element : elements) {
            element.update(t);
        }

        super.update(t);
    }

    @Override
    public void render(IGraphics graphics) {
        for (GridElement element : elements) {
            element.render(graphics);
        }

        super.render(graphics);
    }

    @Override
    public boolean handleInput(TouchEvent event) {
        for (GridElement element : elements) {
            if (element.handleInput(event)) {
                return true;
            }
        }

        return super.handleInput(event);
    }

    @Override
    public void handleParentScreenChange() {
        inheritParentPosition();

        switch (direction) {
            case HORIZONTAL:
                handleParentScreenChangeHorizontal();
                break;
            case VERTICAL:
                handleParentScreenChangeVertical();
                break;
        }

        super.handleParentScreenChange();
    }

    private void handleParentScreenChangeHorizontal() {
        setWidth(getParent().getWidth());
        setHeight(getParent().getHeight());

        double elementWidth = getWidth() / (double) elements.length;
        int elementWidthInt = (int) elementWidth;
        for (int i = 0; i < elements.length; ++i) {
            GridElement element = elements[i];

            element.setHeight(getHeight());
            element.setWidth(elementWidthInt);
            element.setPosition(getPosition().getX() + (int) (elementWidth * i), getPosition().getY());
            element.handleParentScreenChange();
        }
    }

    private void handleParentScreenChangeVertical() {
        setWidth(getParent().getWidth());
        setHeight(getParent().getHeight());

        double elementHeight = getHeight() / (double) elements.length;
        int elementHeightInt = (int) elementHeight;
        for (int i = 0; i < elements.length; ++i) {
            GridElement element = elements[i];

            element.setWidth(getWidth());
            element.setHeight(elementHeightInt);
            element.setPosition(getPosition().getX(), getPosition().getY() + (int) (elementHeight * i));
            element.handleParentScreenChange();
        }
    }
}
