package vdm.p1.logic;

import java.util.List;

import vdm.p1.engine.Color;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.ILogic;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;

public class Menu extends Scene{

    ILogic mLogic;

    public Menu(ILogic logic, int width, int height){
        super(width,height);
        this.mLogic=logic;

        Table t= new Table(5,5,screen_width/5,screen_height/4,screen_width/2,screen_width/2);
        addGameObject(t);

        CheckSolutionButton c= new CheckSolutionButton(t,screen_width/3,screen_height/10 ,screen_width/3,screen_height/12,"Check solution",new Color(0xCCCCFFFF));
        addGameObject(c);
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(Color.WHITE);
        for(GameObject go: mGameObjects_)
            go.render(graphics);

    }

    @Override
    public void update(double t) {
        for(GameObject go: mGameObjects_)
            go.update(t);
    }

    @Override
    public void handleInput(Input input) {
        List<TouchEvent> events= input.getTouchEvents();
        // System.out.println("Scene " + events.size());
        for(TouchEvent t: events){
            for(GameObject g: getGameObjects()){
                g.handleInput(t);
            }
        }

    }

}
