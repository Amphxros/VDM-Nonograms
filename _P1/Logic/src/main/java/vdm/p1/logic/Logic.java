package vdm.p1.logic;

import vdm.p1.engine.Engine;
import vdm.p1.engine.ILogic;
import vdm.p1.logic.scenes.Menu;
import vdm.p1.logic.scenes.Scene;

public final class Logic implements ILogic {
    Engine mEngine_;
    IScene mCurrentScene_;

    public Logic(Engine eng){
        this.mEngine_=eng;

    }

    public Engine getEngine(){return mEngine_;}

    @Override
    public void setEngine(Engine eng) {
        this.mEngine_= eng;
    }

    @Override
    public void initLogic() {
        mCurrentScene_= new Menu(getEngine(), mEngine_.getWidth(), mEngine_.getHeight());
    }

    public void ChangeScene(Scene scene){
        mCurrentScene_=scene;
    }

    @Override
    public void update(double t) {
        mCurrentScene_.update(t);
    }

    @Override
    public void render() {
        mCurrentScene_.render(mEngine_.getGraphics());
    }

    @Override
    public void handleEvents() {
        mCurrentScene_.handleInput(mEngine_.getInput());
    }
}