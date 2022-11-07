package vdm.p1.logic;

import java.util.List;

import vdm.p1.engine.IGraphics;
import vdm.p1.engine.Input;
import vdm.p1.engine.TouchEvent;

public final class Table extends GameObject{
    Cell [][] mCasillas_;

    Hint[] solutions;
    int[] pistas_fila;
    int[] pistas_columna;
    int nRows_;
    int mCols_;

    boolean isChecked=false;
    int numErrors_;
    int fondow=0;
    int fondoh=0;
    public Table(int nRows, int mCols,int x, int y, int w, int h) {
        super(x, y, w, h);
        this.nRows_=nRows;
        this.mCols_=mCols;
        this.numErrors_=0;

        solutions= new Hint[nRows];
        for(int i=0; i<nRows;i++)
            solutions[i]= new Hint(mCols);

        pistas_fila= new int[nRows];
        pistas_columna= new int[mCols];



        mCasillas_= new Cell[nRows][mCols];
        for(int i=0;i<nRows;i++){
            for(int j=0;j<mCols;j++){
                if(solutions[i].getSolutionOnRow()[j]){
                    pistas_fila[j]++;
                    pistas_columna[i]++;
                    mCasillas_[i][j]= new Cell(State.Marked, getPosition().getX() + (i+2) * mHeight_/mCols_,getPosition().getY() +(j+2)*mHeight_/mCols,mHeight_/mCols,mHeight_/mCols);
                }
                else {
                    pistas_fila[j]*=10;
                    pistas_columna[i]*=10;
                    mCasillas_[i][j]= new Cell(State.Empty, getPosition().getX() + (i+2) * mHeight_/mCols_,getPosition().getY() +(j+2)*mHeight_/mCols,mHeight_/mCols,mHeight_/mCols);
                }
            }
        }

        fondow= (nRows_ + 2)*w /mCols;
        fondoh=(mCols_ + 2)*h/mCols;
    }

    @Override
    public void render(IGraphics graphics) {
        graphics.setColor(0xDEDEFFFF);
        graphics.drawRectangle(mPosition_.getX(), mPosition_.getY(), fondow, fondoh);


        for(int i=0;i< pistas_fila.length;i++){
            graphics.setColor(0x000000FF);
            graphics.drawRectangle(getPosition().getX(), getPosition().getY()+ (i+2) * mHeight_/mCols_,2*(mHeight_)/mCols_,(mHeight_-20)/mCols_);
            graphics.setColor(0xDEDEFFFF);
            String s=pistas_fila[i]+ "";
            s=s.replace('0',' '); // trim all the spaces
            graphics.drawText(s, getPosition().getX()+(mHeight_/mCols_)/2  , getPosition().getY()+ (i+3) * mHeight_/mCols_);
        }
        for(int i=0;i< pistas_columna.length;i++){
            graphics.setColor(0x000000FF);
            graphics.drawRectangle(getPosition().getX() + (i+2) * mHeight_/mCols_, getPosition().getY(),(mHeight_-20)/mCols_,2*mHeight_/mCols_);
            graphics.setColor(0xDEDEFFFF);
            String s=pistas_columna[i]+ "";
            s=s.replace('0',' ');// trim all the spaces
            graphics.drawText(s, getPosition().getX()+ (i+2) * mHeight_/mCols_, getPosition().getY()+(mHeight_/mCols_)/2);
        }

        for(int i=0;i<nRows_;i++){
            for(int j=0;j<mCols_;j++){
                mCasillas_[i][j].render(graphics);
            }

        }
    }

    public boolean handleInput(TouchEvent ev){
            for(int i=0;i<nRows_;i++){
                for(int j=0;j<mCols_;j++){
                    if (mCasillas_[i][j].handleInput(ev)){
                        return true;
                    }
                }
            }
            return false;

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
