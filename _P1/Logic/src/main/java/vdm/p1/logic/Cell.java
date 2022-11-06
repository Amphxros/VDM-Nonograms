package vdm.p1.logic;

import vdm.p1.engine.IGraphics;

public final class Cell extends GameObject{

    State currState_;
    State solutionState_;

    public Cell(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public boolean checkSolution(){
        if(currState_==solutionState_ || currState_==State.Correct){
            currState_=State.Correct;
            return true;
        }
        return false;
    }

    @Override
    public void render(IGraphics graphics) {
        switch (currState_){
            case Empty:
                graphics.setColor(0xFFFFFFFF);
                break;
            case Wrong:
                graphics.setColor(0xFFFF0000);
                break;
            case Marked:
                graphics.setColor(0xFF000000);
                break;
            case Correct:
                graphics.setColor(0xFFFFAA00);
                break;
        }
        graphics.drawRectangle(getPosition().getX(), getPosition().getY(), mWidth_, mHeight_);
    }
    public void handleInput(){

    }
}
