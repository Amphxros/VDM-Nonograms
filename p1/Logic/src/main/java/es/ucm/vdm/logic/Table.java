package es.ucm.vdm.logic;

import java.util.ArrayList;

import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.TouchEvent;

public class Table extends GameObject {
    Cell[][] mCells_;
    ArrayList<Integer>[] hints;
    int mFils_=-1;
    int mCols_=-1;

    public Table(int x, int y, int n,int width_per_tile, int height_per_tile) {
        this(x,y,n, n, width_per_tile, height_per_tile);
    }

    public Table(int x, int y, int fils, int cols, int width_per_tile, int height_per_tile) {
        super(x,y,fils +4, cols +4);
        this.mFils_=fils;
        this.mCols_=cols;
        mCells_= new Cell[mFils_][mCols_];
        for(int i=0;i<mFils_;i++){
            for(int j=0;j<mCols_;j++){
                mCells_[i][j]= new Cell(State.Empty,(int)position_.x + (width_per_tile*i),(int)position_.y +(height_per_tile*j),width_per_tile,height_per_tile);
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
