package vdm.p1.logic;

import vdm.p1.engine.EventType;
import vdm.p1.engine.IGraphics;
import vdm.p1.engine.TouchEvent;

public final class Cell extends GameObject{

    State currState_;
    State solutionState_;


    public Cell(State st,int x, int y, int w, int h) {
        super(x, y, w, h);
        solutionState_=st;
        currState_=State.Empty;

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
                graphics.setColor(0xFFAAAAAA);
                break;
            case Wrong:
                graphics.setColor(0xFFFF0000);
                break;
            case Marked:
                graphics.setColor(0xFFEEEEEE);
                break;
            case Correct:
                graphics.setColor(0xFFFFAA00);
                break;
        }
        graphics.drawRectangle(getPosition().getX()-1, getPosition().getY()-1, mWidth_-2, mHeight_-2);
    }
    public boolean handleInput(TouchEvent event) {

        if (currState_ == State.Empty) {
            currState_ = State.Marked;
            System.out.println(" touch");
            return true;
        }

        return false;
    }
}
