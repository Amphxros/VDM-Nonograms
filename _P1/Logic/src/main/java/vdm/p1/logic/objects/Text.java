package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.Dimension;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.logic.GameObject;

public final class Text extends GameObject {
    private final String text;
    private final IFont font;

    public Text(String text, IFont font) {
        super();
        this.text = text;
        this.font = font;

        Dimension<Integer> dimension = font.getGraphics().getTextDimensions(font, text);
        setWidth(dimension.getWidth());
        setHeight(dimension.getHeight());
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);

        graphics.setColor(Color.BLACK);
        graphics.setFont(font);
        graphics.drawText(text, getPosition().getX(), getPosition().getY() + getHeight() - 4);
    }

    @Override
    public void handleParentScreenChange() {
        inheritParentPosition();

        super.handleParentScreenChange();
    }
}
