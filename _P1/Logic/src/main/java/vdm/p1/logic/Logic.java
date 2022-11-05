package vdm.p1.logic;

import vdm.p1.engine.Engine;
import vdm.p1.engine.ILogic;

public class Logic implements ILogic {
    Engine mEngine_;
    IScene mCurrentScene_;

    @Override
    public void setEngine(Engine eng) {
        this.mEngine_= eng;
    }

    @Override
    public void initLogic() {

    }

    @Override
    public void update(double t) {

    }

    @Override
    public void render() {

    }

    @Override
    public void handleEvents() {

    }
}