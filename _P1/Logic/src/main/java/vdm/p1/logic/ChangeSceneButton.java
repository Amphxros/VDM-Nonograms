package vdm.p1.logic;

import vdm.p1.engine.Color;
import vdm.p1.engine.TouchEvent;

public class ChangeSceneButton extends Button{
    Logic mLogic;
    Scene changingScene;
    public ChangeSceneButton(Logic logic,Scene scene,int x, int y, int w, int h, String msg, Color color) {
        super(x, y, w, h, msg, color);
        this.mLogic=logic;
        this.changingScene=scene;
    }

    public boolean handleInput(TouchEvent event){
        if(event.getX()>= position.getX() && event.getX()<= position.getX() + width &&
                event.getY()>= position.getY() && event.getY()<= position.getY() + height)
        {
            mLogic.ChangeScene(changingScene);
            return true;
        }

        return false;
    }
}
