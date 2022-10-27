package es.ucm.vdm.logic;

import java.util.ArrayList;

import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.TouchEvent;

public class Table extends GameObject {
    Cell[][] mCells_;
    ArrayList<Integer>[] hints;
    int mFils_=-1;
    int mCols_=-1;

    public Table(int x, int y, int n) {
        this(x,y,n, n);
    }

    public Table(int x, int y, int fils, int cols) {
        super(x,y,fils +4, cols +4);
        this.mFils_=fils;
        this.mCols_=cols;
        mCells_= new Cell[mFils_][mCols_];
        for(int i=0;i<mFils_;i++){
            for(int j=0;j<mCols_;j++){
                mCells_[i][j]= new Cell(State.Empty,(int)position_.x + i + 1,(int)position_.y +j + 1,1,1);
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
