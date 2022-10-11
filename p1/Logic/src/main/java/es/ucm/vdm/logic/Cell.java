package es.ucm.vdm.logic;

import es.ucm.vdm.engine.Color;
import es.ucm.vdm.engine.Graphics;

public final class Cell extends GameObject{ // elements of the table
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

    boolean isCellCorrect(){
        return state_==solutionState_ || state_==State.Correct;
    }

    public void render(Graphics graphics){ //ESTO ES TEMPORAL LUEGO HAY QUE PONER UN SPRITE O CAMBIAR EL COLOR
        switch (state_){
            case Empty:
                graphics.drawRectangle((int)position_.x,(int)position_.y,1,1,null);
                break;
            case Marked:
                graphics.drawRectangle((int)position_.x,(int)position_.y,1,1,null);
                break;
            case Correct:
                graphics.drawRectangle((int)position_.x,(int)position_.y,1,1,null);
                break;
            case Wrong:
                graphics.drawRectangle((int)position_.x,(int)position_.y,1,1,null);
                break;
        }
    }
}