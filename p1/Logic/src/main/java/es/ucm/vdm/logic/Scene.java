package es.ucm.vdm.logic;

import es.ucm.vdm.enngine.Graphics;
public interface Scene {

    //updates all the GO in this Scene with the time since the last update
    public void update(double t);

    //render the GO in their positions.
    // Receives an instance of graphics to call their own Graphics handler(Different in android and PC)
    public void render(Graphics g);

    //handle the input
    public void handleInput();

}
