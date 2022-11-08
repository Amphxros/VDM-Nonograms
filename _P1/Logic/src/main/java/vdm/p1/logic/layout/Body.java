package vdm.p1.logic.layout;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IGraphics;
import vdm.p1.logic.GameObject;

public final class Body extends GameObject {
    private final IEngine engine;

    public Body(IEngine engine) {
        super(0, 0, 0, 0);
        this.engine = engine;
    }

    @Override
    public void render(IGraphics graphics) {
        // TODO: Visualization only, remove before delivering the project.
        graphics.setColor(new Color(200, 200, 200));
        graphics.fillRectangle(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());

        super.render(graphics);
    }

    @Override
    public void update(double t) {
        // Detect if the screen size has changed, if it did, send a signal to the children elements.
        if (detectSizeChanges()) this.handleParentScreenChange();

        super.update(t);
    }

    private boolean detectSizeChanges() {
        boolean changed = false;
        if (getWidth() != engine.getWidth()) {
            setWidth(engine.getWidth());
            changed = true;
        }

        if (getHeight() != engine.getHeight()) {
            setHeight(engine.getHeight());
            changed = true;
        }

        return changed;
    }
}