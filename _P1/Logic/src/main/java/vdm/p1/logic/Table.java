package vdm.p1.logic;

import java.util.List;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;

public final class Table extends GameObject{
    Cell [][] mCasillas_;
    int nRows_;
    int mCols_;

    boolean isChecked=false;
    int numErrors_;

    public Table(int nRows, int mCols,int x, int y, int w, int h) {
        super(x, y, w, h);
        this.nRows_=nRows;
        this.mCols_=mCols;
        this.numErrors_=0;
        mCasillas_= new Cell[nRows][mCols];
        for(int i=0;i<nRows;i++){
            for(int j=0;j<mCols;j++){
                mCasillas_[i][j]= new Cell(State.Marked, getPosition().getX() + i*200,getPosition().getY() +j*200,190,190);
            }
        }
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(0xEEFFFFFF);
        graphics.drawRectangle(mPosition_.getX(), mPosition_.getY(), mWidth_, mHeight_);

        for(int i=0;i<nRows_;i++){
            for(int j=0;j<mCols_;j++){
                mCasillas_[i][j].render(graphics);
            }

        }
    }

    public void handleInput(TouchEvent ev){
            for(int i=0;i<nRows_;i++){
                for(int j=0;j<mCols_;j++){
                    mCasillas_[i][j].handleInput(ev);
                }
            }

    }

    public boolean checkSolutions(){
       if(!this.isChecked){
            for(int i=0;i<nRows_;i++) {
                for (int j = 0; j < mCols_; j++) {
                    if (!mCasillas_[i][j].checkSolution()) {
                        numErrors_++;
                    }
                }
            }
       }
        this.isChecked=true;
        return numErrors_==0;
    }

}
