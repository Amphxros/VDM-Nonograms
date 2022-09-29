package es.ucm.vdm.aengine;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import es.ucm.vdm.engine.Graphics;
public class AndroidGraphics implements Graphics{

    private Canvas canvas;
    private Paint paint;

    public AndroidGraphics(){

    }
    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void drawRectangle(int x, int y, int w, int h) {
        canvas.drawCircle(x, y, w, this.paint);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
