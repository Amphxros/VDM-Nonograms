package es.ucm.vdm.logic;

import java.util.ArrayList;

import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.TouchEvent;

public class Table extends GameObject {
    Cell[][] mCells_;
    ArrayList<Integer>[] hints;
    int mFils_=-1;
    int mCols_=-1;

    public Table(int n) {
        this(n, n);
    }

    public Table(int fils, int cols) {
        this.mFils_=fils;
        this.mCols_=cols;
        mCells_= new Cell[mFils_][mCols_];
        for(int i=0;i<mFils_;i++){
            for(int j=0;j<mCols_;j++){
                mCells_[i][j]= new Cell(State.Empty,i,j,1,1);
            }
        }
    }

    public Table(String route){

    }
    public void render(IGraphics graphics){
        for(int i=0;i<mFils_;i++){
            for(int j=0;j<mCols_;j++){
                mCells_[i][j].render(graphics);
            }
        }

    }
    public void handleInput(TouchEvent event){

    }
}
