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
    public int getWidth() {
        return engine.getWidth();
    }

    @Override
    public int getHeight() {
        return engine.getHeight();
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(new Color(200, 200, 200));
        graphics.fillRectangle(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());

        super.render(graphics);
    }
}
