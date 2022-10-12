package es.ucm.vdm.aengine;

import android.view.MotionEvent;
import android.view.View;

import es.ucm.vdm.engine.Input;

public class AndroidInput extends Input implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
