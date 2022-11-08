package vdm.p1.logic;

import java.util.List;

import vdm.p1.engine.Color;
import vdm.p1.engine.IEngine;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.layout.Body;
import vdm.p1.logic.layout.Container;
import vdm.p1.logic.layout.FlowDirection;
import vdm.p1.logic.layout.Grid;
import vdm.p1.logic.layout.Padding;
import vdm.p1.logic.objects.Table;

public class Menu extends Scene {

    IEngine engine;

    public Menu(IEngine engine, int width, int height) {
        super(width, height);
        this.engine = engine;

        GameObject grid = new Table(5);
        GameObject padding = new Padding(0.04, 0.1).addChild(grid);
        GameObject container = new Container(400, 600).addChild(padding);
        GameObject body = new Body(engine).addChild(container);

        addGameObject(body);

        // Table t = new Table(5, 5, screen_width / 5, screen_height / 4, screen_width / 2, screen_width / 2);
        // addGameObject(t);

        // CheckSolutionButton c = new CheckSolutionButton(t, screen_width / 3, screen_height / 10, screen_width / 3, screen_height / 12, "Check solution", new Color(0xCC, 0xCC, 0xFF));
        // addGameObject(c);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(Color.WHITE);
        for (GameObject go : mGameObjects_)
            go.render(graphics);
    }

    @Override
    public void update(double t) {
        for (GameObject go : mGameObjects_)
            go.update(t);
    }

    @Override
    public void handleInput(Input input) {
        List<TouchEvent> events = input.getTouchEvents();
        // System.out.println("Scene " + events.size());
        for (TouchEvent t : events) {
            for (GameObject g : getGameObjects()) {
                g.handleInput(t);
            }
        }
    }

}
