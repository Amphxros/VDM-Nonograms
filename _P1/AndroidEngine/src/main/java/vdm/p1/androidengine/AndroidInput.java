package vdm.p1.androidengine;

import android.view.MotionEvent;
import android.view.View;

import vdm.p1.engine.EventType;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;

public class AndroidInput extends Input implements View.OnTouchListener {

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
            TouchEvent t = new TouchEvent(motionEvent.getX(), motionEvent.getY(), EventType.PRIMARY);
            addEvent(t);
            System.out.println("PRIMARY");
            return true;
        }

        if (motionEvent.getActionMasked() == MotionEvent.ACTION_MOVE) {
            TouchEvent t = new TouchEvent(motionEvent.getX(), motionEvent.getY(), EventType.SECONDARY);
            addEvent(t);
            System.out.println("SECONDARY");
            return true;
        }
        return false;
    }
}