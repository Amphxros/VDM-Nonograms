package vdm.p1.logic;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IInput;

public class Menu extends Scene{


    public Menu(Logic logic){

    }

    @Override
    public void render(IGraphics graphics) {
        graphics.drawRectangle(0,0, 100,100);
    }

    @Override
    public void update(double t) {

    }


    @Override
    public void handleInput(IInput input) {

    }
}
