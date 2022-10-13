package es.ucm.vdm.logic;

import java.util.ArrayList;

import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.TouchEvent;

public class Table extends GameObject {
    Cell[][] mCells_;
    ArrayList<Integer>[] hints;

    public Table(int n) {
        this(n, n);
    }

    public Table(int n, int m) {

    }
    public void render(IGraphics graphics){

    }
    public void handleInput(TouchEvent event){

    }
}
