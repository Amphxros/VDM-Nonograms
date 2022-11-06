package vdm.p1.androidengine;

import android.content.Context;
import android.content.res.AssetManager;
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


    public AndroidEngine(Context context){

        this.mMngr_= context.getAssets();
        androidGraphics_= new AndroidGraphics(context,mMngr_);
        androidInput_= new AndroidInput();
        androidAudio_= new AndroidAudio(context);

        setGraphics(androidGraphics_);
        setAudio(androidAudio_);
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
        while(this.getView().getWidth() == 0);
        // Espera activa. Sería más elegante al menos dormir un poco.


        running=true;
            long lastFrameTime = System.nanoTime();
            long informePrevio = lastFrameTime; // Informes de FPS
            int frames = 0;

            while(running) {
                long currentTime = System.nanoTime();
                long nanoElapsedTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;

                this.render();
                this.handleInput();

            }
    }
    private void render(){


        this.androidGraphics_.clear(0xFFAA00FF);
        mLogic_.render();
        this.androidGraphics_.present();
    }

    private void update(double delta){
        mLogic_.update(delta);
    }

    private void handleInput(){

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

    public SurfaceView getView(){
        return androidGraphics_.getView();
    }
}
