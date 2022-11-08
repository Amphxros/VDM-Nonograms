package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.logic.GameObject;

public final class Text extends GameObject {
    private final String text;

    public Text(String text) {
        super();
        this.text = text;

        // TODO: Measure this somehow using Graphics, Font, or whatever
        setWidth(text.length() * 10);
        setHeight(20);
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);

        graphics.setColor(Color.BLACK);
        graphics.drawText(text, getPosition().getX(), getPosition().getY() + getHeight() - 4);
    }

    @Override
    public void handleParentScreenChange() {
        inheritParentPosition();

        super.handleParentScreenChange();
    }
}
