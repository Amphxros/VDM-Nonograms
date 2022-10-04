package es.ucm.vdm.aengine;

import android.graphics.Canvas;
import android.graphics.Paint;

import es.ucm.vdm.engine.Graphics;
import es.ucm.vdm.engine.Rect;

public class AndroidGraphics extends Graphics {

    private Canvas canvas;
    private Paint paint;

    public AndroidGraphics(){

    }

    @Override
    public void setCanvasSize(Rect canvas, Rect dimension) {

    }

    @Override
    public Rect getCanvas() {
        return null;
    }

    @Override
    public boolean createFont(String route, int size, int color, boolean bold) {
        return false;
    }

}
