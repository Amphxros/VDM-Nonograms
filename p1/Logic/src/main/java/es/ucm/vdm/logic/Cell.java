package es.ucm.vdm.logic;

import es.ucm.vdm.engine.Color;
import es.ucm.vdm.engine.Graphics;

public class Cell extends GameObject{ // elements of the table
    State state_;
    State solutionState_;

    public Cell(){
        state_ =State.Empty;
    }

    public Cell(State state){
        state_ = state;
    }

    State getCurrentState() { return state_; }
    void setCurrentState(State state){
        state_ = state;
    }

    State getSolutionState(){return solutionState_;}
    void setSolutionState(State state){solutionState_=state;}

    boolean isCellCorrect(){return state_==solutionState_;}

    public void render(Graphics graphics){
        switch (state_){
            case State.Empty:
                graphics.drawRectangle((int)position_.x,(int)position_.y,1,1,null);
                break;
            case State.Marked:
                graphics.drawRectangle((int)position_.x,(int)position_.y,1,1,null);
                break;
        }
    }
}