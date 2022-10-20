package es.ucm.vdm.aengine;

import es.ucm.vdm.engine.Engine;
import es.ucm.vdm.engine.IAudio;
import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.IInput;

public class AndroidEngine extends Engine {
    public AndroidEngine(IGraphics graphics, IAudio audio, IInput input) {
        super(graphics, audio, input);
    }
}
