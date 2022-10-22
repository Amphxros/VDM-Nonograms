package es.ucm.vdm.pcengine;

import es.ucm.vdm.engine.Engine;
import es.ucm.vdm.engine.IAudio;
import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.IInput;

public class PCEngine extends Engine {

    public PCEngine(IGraphics graphics, IAudio audio, IInput input) {
        super(graphics, audio, input);
    }
    
    @Override
    public void run() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

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