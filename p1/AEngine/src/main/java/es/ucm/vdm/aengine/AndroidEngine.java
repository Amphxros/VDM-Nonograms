package es.ucm.vdm.aengine;

import android.content.Context;
import android.content.res.AssetManager;

import es.ucm.vdm.engine.Engine;

public class AndroidEngine extends Engine {

    Window mWindow_;
    AssetManager mMngr_;
    public AndroidEngine(Context context){
        super();

        mWindow_= new Window(context);
        mMngr_=context.getAssets();

        AndroidGraphics graphics_= new AndroidGraphics(mWindow_,mMngr_);
        AndroidInput input_= new AndroidInput();
        AAudio audio_= new AAudio(context);

        setGraphics(graphics_);
        setAudio(audio_);
        setInput(input_);

        setWidth(mWindow_.width());
        setHeight(mWindow_.height());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
    @Override
    public boolean openGame() {
        return false;
    }

    @Override
    public boolean closeGame() {
        return false;
    }
}
