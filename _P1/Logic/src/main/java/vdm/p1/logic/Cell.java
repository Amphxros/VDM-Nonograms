package vdm.p1.logic;

import vdm.p1.engine.IGraphics;

public class Cell extends GameObject{

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
                break;
            case Wrong:
                break;
            case Marked:
                break;
            case Correct:
                break;
        }
    }
    public void handleInput(){

    }
}
