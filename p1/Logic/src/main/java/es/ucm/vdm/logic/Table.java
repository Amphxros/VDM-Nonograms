package es.ucm.vdm.logic;

import java.util.ArrayList;

import es.ucm.vdm.engine.Graphics;

public class Table extends GameObject{
    State[][] levelInfo;
    Cell[][] mCells_;
    ArrayList<Integer>[] hints;
    int fils;
    int cols;

    public Table(int n){
        this(n,n);
    }

    public Table(int n, int m) {
        this.fils=n;
        this.cols=m;
        mCells_= new Cell[fils][cols];
    }

    public void render(Graphics graphics){

        for(int i=0;i<fils;i++){
            for(int j=0; j<cols;j++){
                mCells_[i][j].render(graphics);
            }
        }

    }

}
