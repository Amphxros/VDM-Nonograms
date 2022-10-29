package es.ucm.vdm.pcengine;

import es.ucm.vdm.engine.Engine;
import es.ucm.vdm.engine.IAudio;
import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.IInput;
import es.ucm.vdm.engine.Logic;

public class PCEngine extends Engine {

    Window mWindow_;
    int mWidth_;
    int mHeight_;

    public PCEngine(String title, int width, int height){
        super();
        mGraphics_= new PCGraphics();
        mAudio_= new PCAudio();
        mInput_= new PCInput();

        this.mWidth_=width;
        this.mHeight_=height;

        mGraphics_.setResolution(mWidth_, mHeight_);
        mWindow_= new Window(title,mWidth_,mHeight_,this);

    }

    public PCEngine(String title, IGraphics graphics, IAudio audio, IInput input) {
        super(graphics, audio, input);
        this.mWidth_=graphics.getWidth();
        this.mHeight_=graphics.getHeight();
        mWindow_= new Window(title, mWidth_, mHeight_,this);

    }

}