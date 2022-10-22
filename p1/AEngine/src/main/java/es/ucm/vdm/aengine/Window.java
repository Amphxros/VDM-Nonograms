package es.ucm.vdm.aengine;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Window extends SurfaceView {

    public Window(Context context) {
        super(context);

    }
    public SurfaceHolder getSurfaceHolder(){
        return getHolder();
    }
    public int width(){
        return getWidth();
    }
    public int height(){
        return getHeight();
    }
    public boolean isSurfaceValid(){
        return getHolder().getSurface().isValid();
    }
}
