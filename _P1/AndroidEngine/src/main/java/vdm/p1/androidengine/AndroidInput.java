package vdm.p1.androidengine;

import android.view.MotionEvent;
import android.view.View;

import vdm.p1.engine.EventType;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;

public class AndroidInput extends Input implements View.OnTouchListener{



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(motionEvent.getActionMasked()==MotionEvent.ACTION_DOWN){
            System.out.println(" BOOP");
            TouchEvent t= new TouchEvent(motionEvent.getX(), motionEvent.getY(), EventType.PRESSED_DOWN);
            addEvent(t);
            return true;
        }
        else if(motionEvent.getActionMasked()==MotionEvent.ACTION_UP){
            System.out.println(" BOOP");
            TouchEvent t= new TouchEvent(motionEvent.getX(), motionEvent.getY(), EventType.PRESSED_UP);
            addEvent(t);
            return true;
        }
        else if(motionEvent.getActionMasked()==MotionEvent.ACTION_MOVE){
            System.out.println(" BOOP");
            TouchEvent t= new TouchEvent(motionEvent.getX(), motionEvent.getY(), EventType.MOVED);
            addEvent(t);
            return true;

        }
        return false;
    }
}
