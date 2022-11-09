package vdm.p1.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import vdm.p1.engine.Engine;

public final class AndroidEngine extends Engine implements Runnable {

    AndroidGraphics androidGraphics_;
    AndroidInput androidInput_;
    AndroidAudio androidAudio_;
    private AssetManager mMngr_;
    private Thread mThread;
    private boolean running;
    // Screen variables
    private final SurfaceView mView;
    private final SurfaceHolder mHolder;
    private Canvas mCanvas;
    private final Paint mPaint;

    public AndroidEngine(SurfaceView view, Context context) {

        this.mView = view;
        this.mHolder = this.mView.getHolder();
        this.mPaint = new Paint();
        this.mPaint.setColor(0xFFFFFFFF);

        androidGraphics_ = new AndroidGraphics(mView, context, mCanvas, mPaint);
        androidAudio_ = new AndroidAudio(context);
        androidInput_ = new AndroidInput();

        this.mView.setOnTouchListener(androidInput_);

        setGraphics(androidGraphics_);
        setInput(androidInput_);
        setAudio(androidAudio_);

    }

    @Override
    public void run() {

        if (mThread != Thread.currentThread()) {
            // (Defensive Programming)
            // Makes it so runnable can only be called from this class
            throw new RuntimeException("run() should not be called directly");
        }

        // Waits for the view to be initialized (The thread could be faster than the initialization)
        while (this.running && this.mView.getWidth() == 0) ;
        mLogic_.initLogic();
        long lastFrameTime = System.nanoTime();
        long informePrevio = lastFrameTime; // FPS
        int frames = 0;

        // MAIN GAME LOOP
        while (running) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;

            // Frames Per Second
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;
            this.update(elapsedTime);
            if (currentTime - informePrevio > 1000000000l) {
                long fps = frames * 1000000000l / (currentTime - informePrevio);

                frames = 0;
                informePrevio = currentTime;
            }
            ++frames;

            // Full frame rendering
            this.render();

            this.handleInput();
                /*
                // Posibilidad: cedemos algo de tiempo. Es una medida conflictiva...
                try { Thread.sleep(1); } catch(Exception e) {}
    			*/
        }

    }

    private void render() {

        // Waits for an invalid surface
        while (!this.mHolder.getSurface().isValid()) ;

        this.mCanvas = this.mHolder.lockCanvas();
        androidGraphics_.setCanvas(this.mCanvas);
        this.mCanvas.drawColor(0xFFB0A0FF); // ARGB
        mLogic_.render();
        this.mHolder.unlockCanvasAndPost(mCanvas);

    }

    private void update(double delta) {
        mLogic_.update(delta);
    }

    private void handleInput() {
        mLogic_.handleEvents();
    }

    public void resume() {
        if (!this.running) {
            // Only if we weren´t doing anything yet
            // (Defensive programming at its best)
            this.running = true;
            // run() is "running" in a new thread
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
                    // Something went REALLY wrong
                }
            }
        }
    }

    public int getWidth() {
        return this.mView.getWidth();
    }

    public int getHeight() {
        return this.mView.getHeight();
    }
}