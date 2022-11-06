package vdm.p1.androidengine;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.util.HashMap;

import vdm.p1.engine.IFont;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.IImage;

public class AndroidGraphics implements IGraphics {
    private SurfaceView mView;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Paint mPaint;

    private HashMap<String,AndroidImage> mImages_;
    private  HashMap<String, IFont> mFonts_;

    private AssetManager mMngr_;

    public AndroidGraphics(Context context, AssetManager mngr){
        this.mView=new SurfaceView(context);
        this.mHolder= this.mView.getHolder();
        this.mPaint= new Paint();
        this.mMngr_=mngr;

        this.mImages_= new HashMap<>();
        this.mFonts_= new HashMap<>();

        this.mPaint.setColor(0xFFFFFFFF); // ARGB
    }

    @Override
    public IImage newImage(String name) {
        return null;
    }

    @Override
    public IFont newFont(String name, int size, boolean isBold) {
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
        mCanvas.drawRect(x,y,x + side,y + side,mPaint);
    }

    @Override
    public void fillRectangle(int x, int y, int width, int height) {
        mCanvas.drawRect(x,y,x + width,y + height,mPaint);
    }

    @Override
    public void drawRectangle(int x, int y, int side) {
        mCanvas.drawRect(x,y,x + side,y + side,mPaint);
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height) {
        mCanvas.drawRect(x,y,x + width,y + height,mPaint);
    }

    @Override
    public void drawLine(int initX, int initY, int endX, int endY) {
        mCanvas.drawLine(initX, initY,endX,endY, mPaint);
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

    }

    @Override
    public void restore() {

    }

    @Override
    public int getWidth() {
        return this.mView.getWidth();
    }

    @Override
    public int getHeight() {
        return this.mView.getHeight();
    }
}


/**

//Clase interna encargada de obtener el SurfaceHolder y pintar con el canvas
class MyRenderClass implements Runnable, IGraphics {

    private SurfaceView myView;
    private SurfaceHolder holder;
    private Canvas canvas;

    private Thread renderThread;

    private boolean running;

    private Paint paint;

    private Scene scene;

    public MyRenderClass(SurfaceView myView){
        this.myView = myView;
        this.holder = this.myView.getHolder();
        this.paint = new Paint();
        this.paint.setColor(0xFFFFFFFF);
    }

    public int getWidth(){
        return this.myView.getWidth();
    }

    @Override
    public void run() {
        if (renderThread != Thread.currentThread()) {
            // Evita que cualquiera que no sea esta clase llame a este Runnable en un Thread
            // Programación defensiva
            throw new RuntimeException("run() should not be called directly");
        }

        // Si el Thread se pone en marcha
        // muy rápido, la vista podría todavía no estar inicializada.
        while(this.running && this.myView.getWidth() == 0);
        // Espera activa. Sería más elegante al menos dormir un poco.

        long lastFrameTime = System.nanoTime();

        long informePrevio = lastFrameTime; // Informes de FPS
        int frames = 0;

        // Bucle de juego principal.
        while(running) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;

            // Informe de FPS
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;
            this.update(elapsedTime);
            if (currentTime - informePrevio > 1000000000l) {
                long fps = frames * 1000000000l / (currentTime - informePrevio);
                System.out.println("" + fps + " fps");
                frames = 0;
                informePrevio = currentTime;
            }
            ++frames;

            // Pintamos el frame
            while (!this.holder.getSurface().isValid());
            this.canvas = this.holder.lockCanvas();
            this.render();
            this.holder.unlockCanvasAndPost(canvas);

                /*
                // Posibilidad: cedemos algo de tiempo. Es una medida conflictiva...
                try { Thread.sleep(1); } catch(Exception e) {}

        }
    }

    protected void update(double deltaTime) {
        scene.update(deltaTime);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    protected void renderCircle(float x, float y, float r){
        canvas.drawCircle(x, y, r, this.paint);
    }

    protected void render() {
        // "Borramos" el fondo.
        this.canvas.drawColor(0xFFBBAABB); // ARGB
        scene.render();
    }

    public void resume() {
        if (!this.running) {
            // Solo hacemos algo si no nos estábamos ejecutando ya
            // (programación defensiva)
            this.running = true;
            // Lanzamos la ejecución de nuestro método run() en un nuevo Thread.
            this.renderThread = new Thread(this);
            this.renderThread.start();
        }
    }

    public void pause() {
        if (this.running) {
            this.running = false;
            while (true) {
                try {
                    this.renderThread.join();
                    this.renderThread = null;
                    break;
                } catch (InterruptedException ie) {
                    // Esto no debería ocurrir nunca...
                }
            }
        }
    }*/