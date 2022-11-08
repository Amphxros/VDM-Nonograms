package vdm.p1.logic;

import vdm.p1.engine.Color;
import vdm.p1.engine.TouchEvent;

public class ChangeSceneButton extends Button{
    public ChangeSceneButton(int x, int y, int w, int h, String msg, Color color) {
        super(x, y, w, h, msg, color);
    }

    public boolean handleInput(TouchEvent event){
        if(event.getX()>= position.getX() && event.getX()<= position.getX() + width &&
                event.getY()>= position.getY() && event.getY()<= position.getY() + height)
        {

            return true;
        }

        return false;
    }
}
