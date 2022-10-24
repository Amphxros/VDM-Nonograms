package es.ucm.vdm.aengine;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import es.ucm.vdm.engine.IFont;
import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.IImage;

public class AndroidGraphics implements IGraphics {
    SurfaceView mView_;
    AssetManager mMngr_;
    Paint mPaint_;
    Canvas mCanvas_;

    public AndroidGraphics(SurfaceView view, AssetManager mngr){
        super();
        this.mView_=view;
        this.mMngr_=mngr;
        this.mPaint_=new Paint();
        mPaint_.setColor(0xFFFFFFFF);
    }

    @Override
    public IImage newImage(String name) {

        return null;
    }

    @Override
    public IFont newFont(String name, int size, boolean isBold)
    {
        return null;
    }

    @Override
    public void drawImage(IImage image, int x, int y) {

    }

    @Override
    public void drawImage(IImage image, int x, int y, int width, int height) {

    }

    @Override
    public void drawText(String text, int x, int y) {

    }

    @Override
    public void fillRectangle(int x, int y, int side) {

    }

    @Override
    public void fillRectangle(int x, int y, int width, int height) {

    }

    @Override
    public void drawRectangle(int x, int y, int side) {

    }

    @Override
    public void drawRectangle(int x, int y, int width, int height) {

    }

    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {

    }

    @Override
    public void setResolution(int width, int height) {

    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public void setFont(IFont font) {

    }

    @Override
    public void clear(int color) {

    }

    @Override
    public void translate(int x, int y) {

    }

    @Override
    public void scale(double x, double y) {

    }

    @Override
    public void save() {
        mCanvas_.save();
    }

    @Override
    public void restore() {
        mCanvas_.restore();
    }

    @Override
    public int getWidth() {

        return mView_.getWidth();
    }

    @Override
    public int getHeight() {
        return mView_.getHeight();
    }
}
