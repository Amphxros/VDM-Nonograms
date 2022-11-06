package vdm.p1.logic;

import java.util.ArrayList;
import java.util.List;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IInput;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;

public class Menu extends Scene{

    Logic mLogic;

    public Menu(Logic logic){
        super();
        this.mLogic=logic;

        Table t= new Table(3,3,250,700,600,600);
        addGameObject(t);

        CheckSolutionButton c= new CheckSolutionButton(t,250,300, 600,150,"Check solution",0xFFCCCCFF);
        addGameObject(c);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(0xFFFFFFFF);
        for(GameObject go: mGameObjects_)
            go.render(graphics);

    }

    @Override
    public void update(double t) {

    }

    @Override
    public void handleInput(Input input) {
        List<TouchEvent> events= input.getTouchEvents();

        for(TouchEvent t: events){
            for(GameObject g: getGameObjects()){
                g.handleInput(t);
            }
        }

    }

}
