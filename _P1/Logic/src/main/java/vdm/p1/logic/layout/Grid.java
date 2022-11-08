package vdm.p1.logic.layout;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameObject;

public class Grid extends GameObject {
    private final FlowDirection direction;
    private final GridElement[] elements;
    private double ratio = 1.0;

    public Grid(int values) {
        this(values, FlowDirection.HORIZONTAL);
    }

    public Grid(int values, FlowDirection direction) {
        super(0, 0, 0, 0);
        this.direction = direction;
        this.elements = new GridElement[values];
        for (int i = 0; i < values; ++i) this.elements[i] = new GridElement();
    }

    /**
     * Returns the element aspect ratio.
     * @see Grid#setRatio(double)
     * @return The element aspect ratio.
     */
    public double getRatio() {
        return ratio;
    }

    /**
     * Sets the element aspect ratio. By default, the {@link Grid} is instantiate with a ratio of 1,
     * or 1:1, which renders the elements as squares.
     * @param ratio The aspect ratio for each element.
     * @return The updated {@link Grid} instance.
     */
    public Grid setRatio(double ratio) {
        this.ratio = ratio;
        return this;
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
     * @return The updated {@link Grid} instance.
     */
    public Grid setElement(int index, GameObject gameObject) {
        if (index < 0) throw new RuntimeException("index cannot be negative");
        if (index >= elements.length) throw new RuntimeException("index cannot be superior to elements.length");

        elements[index].addChild(gameObject);
        return this;
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
        setPosition(getParent().getPosition());
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
        setHeight((int) (getWidth() / (double) elements.length * ratio));

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
        // TODO: Finish this
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
