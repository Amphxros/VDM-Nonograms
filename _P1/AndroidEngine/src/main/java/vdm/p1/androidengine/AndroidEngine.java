package vdm.p1.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.Console;

import vdm.p1.engine.Engine;

public final class AndroidEngine extends Engine implements Runnable {

    private AssetManager mMngr_;

    private Thread mThread;
    private boolean running;

    AndroidGraphics androidGraphics_;
    AndroidInput androidInput_;
    AndroidAudio androidAudio_;


    private SurfaceView mView;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Paint mPaint;

    public AndroidEngine(SurfaceView view,Context context){

        this.mView=view;
        this.mHolder=this.mView.getHolder();
        this.mPaint=new Paint();
        this.mPaint.setColor(0xFFFFFFFF);

        androidGraphics_= new AndroidGraphics(mView,context,mCanvas,mPaint);
        androidAudio_= new AndroidAudio(context);
        androidInput_= new AndroidInput();

        this.mView.setOnTouchListener(androidInput_);

        setGraphics(androidGraphics_);
        setInput(androidInput_);

    }

    @Override
    public void run() {

        if (mThread != Thread.currentThread()) {
            // Evita que cualquiera que no sea esta clase llame a este Runnable en un Thread
            // Programación defensiva
            throw new RuntimeException("run() should not be called directly");
        }

        // Si el Thread se pone en marcha
        // muy rápido, la vista podría todavía no estar inicializada.
        while(this.running && this.mView.getWidth() == 0);
        // Espera activa. Sería más elegante al menos dormir un poco.
        mLogic_.initLogic();
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

                frames = 0;
                informePrevio = currentTime;
            }
            ++frames;

            // Pintamos el frame
            this.render();

            this.handleInput();
                /*
                // Posibilidad: cedemos algo de tiempo. Es una medida conflictiva...
                try { Thread.sleep(1); } catch(Exception e) {}
    			*/
        }

    }
    private void render(){

        while (!this.mHolder.getSurface().isValid());
        this.mCanvas = this.mHolder.lockCanvas();
        androidGraphics_.setCanvas(this.mCanvas);
        this.mCanvas.drawColor(0xFFB0A0FF); // ARGB
        mLogic_.render();
        this.mHolder.unlockCanvasAndPost(mCanvas);


    }


    private void update(double delta){
        mLogic_.update(delta);
    }

    private void handleInput(){
        mLogic_.handleEvents();
    }


    public void resume() {
        if (!this.running) {
            // Solo hacemos algo si no nos estábamos ejecutando ya
            // (programación defensiva)
            this.running = true;
            // Lanzamos la ejecución de nuestro método run() en un nuevo Thread.
            this.mThread = new Thread(this);
            this.mThread.start();
        }
    }

    public void pause() {
        if (this.running) {
            this.running = false;
            while (true) {
                try {
                    this.mThread.join();
                    this.mThread = null;
                    break;
                } catch (InterruptedException ie) {
                    // Esto no debería ocurrir nunca...
                }
            }
        }
    }

    public int getWidth(){
        return this.mView.getWidth();
    }
    public int getHeight(){
        return this.mView.getHeight();
    }
}
