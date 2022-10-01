package es.ucm.vdm.aengine;

import android.graphics.Canvas;
import android.graphics.Paint;

import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.Rect;

public class AndroidGraphics implements IGraphics {

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
}
