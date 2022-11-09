package vdm.p1.logic.objects;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.Logic;
import vdm.p1.logic.layout.HorizontalAlignment;
import vdm.p1.logic.layout.VerticalAlignment;
import vdm.p1.logic.objects.base.Button;
import vdm.p1.logic.scenes.GameScene;

public final class CreateLevelButton extends Button {
    private final IEngine engine;
    private final int rows;
    private final int columns;

    public CreateLevelButton(IEngine engine, IFont font,int rows, int columns) {
        super();
        this.engine = engine;
        this.rows = rows;
        this.columns = columns;

        addChild(new Text(rows + "x" + columns,font).setHorizontalAlignment(HorizontalAlignment.CENTRE).setVerticalAlignment(VerticalAlignment.MIDDLE));
    }

    @Override
    public boolean onPrimaryAction(TouchEvent event) {
        Logic logic = (Logic) engine.getLogic();
        logic.changeScene(new GameScene(engine, rows, columns));
        return true;
    }

    @Override
    public void render(IGraphics graphics) {
        super.render(graphics);

        graphics.setColor(Color.BLACK);
        graphics.drawRectangle(getPosition().getX(), getPosition().getY(), getWidth(), getHeight());
    }

    @Override
    public void handleParentScreenChange() {
        inheritParentArea();

        super.handleParentScreenChange();
    }
}
