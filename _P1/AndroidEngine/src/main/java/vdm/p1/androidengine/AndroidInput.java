package vdm.p1.androidengine;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import vdm.p1.engine.EventType;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;

public final class AndroidInput extends Input implements View.OnTouchListener {
    private final Handler handler;
    private final Runnable runnable;
    private int x = -1, y = -1;

    public AndroidInput() {
        handler = new Handler(Looper.myLooper());
        runnable = () -> {
            TouchEvent t = new TouchEvent(x, y, EventType.SECONDARY);
            addEvent(t);

            x = -1;
            y = -1;
        };
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x = (int) event.getX();
            y = (int) event.getY();
            handler.postDelayed(runnable, ViewConfiguration.getLongPressTimeout());
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            // Held for longer than 1000 milliseconds, long-press has processed:
            if (x == -1) return false;

            view.performClick();
            handler.removeCallbacks(runnable);

            TouchEvent t = new TouchEvent((int) event.getX(), (int) event.getY(), EventType.PRIMARY);
            addEvent(t);
            return true;
        }

        return false;
    }
}