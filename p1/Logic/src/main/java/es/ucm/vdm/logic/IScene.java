package es.ucm.vdm.logic;

import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.IInput;

public interface IScene {

    //updates all the GO in this Scene with the time since the last update
    void update(double deltaTime);

    //render the GO in their positions.
    // Receives an instance of graphics to call their own Graphics handler (Different in android and PC)
    void render(IGraphics g);

    //handle the input
    void handleInput(IInput input);
}
