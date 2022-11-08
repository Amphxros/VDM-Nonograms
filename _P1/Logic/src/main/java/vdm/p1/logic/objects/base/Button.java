package vdm.p1.logic.objects.base;

import vdm.p1.engine.TouchEvent;
import vdm.p1.logic.GameObject;

public abstract class Button extends GameObject {
    public boolean handleInput(TouchEvent event) {
        int x = (int) event.getX();
        int px = getPosition().getX();
        if (x < px || x > px + getWidth()) return false;

        int y = (int) event.getY();
        int py = getPosition().getY();
        if (y < py || y > py + getHeight()) return false;

        onClick();
        return true;
    }

    public abstract void onClick();
}
