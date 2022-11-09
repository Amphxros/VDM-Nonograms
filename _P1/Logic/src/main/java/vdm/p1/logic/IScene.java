package vdm.p1.logic;

import java.util.ArrayList;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.Input;

public interface IScene {

    ArrayList<GameObject> getGameObjects();

    void render(IGraphics graphics);

    void update(double t);

    void handleInput(Input input);
}
