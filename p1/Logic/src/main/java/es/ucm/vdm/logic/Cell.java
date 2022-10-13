package es.ucm.vdm.logic;

import es.ucm.vdm.engine.EventType;
import es.ucm.vdm.engine.IGraphics;
import es.ucm.vdm.engine.TouchEvent;

public class Cell extends GameObject { // elements of the table
    State state_;
    State solutionState_;

    public Cell() {
        state_ = State.Empty;
    }

    public Cell(State state) {
        state_ = state;
    }

    State getCurrentState() {
        return state_;
    }

    void setCurrentState(State state) {
        state_ = state;
    }

    State getSolutionState() {
        return solutionState_;
    }

    void setSolutionState(State state) {
        solutionState_ = state;
    }

    boolean isCellCorrect() {
        return state_ == solutionState_;
    }

    public void render(IGraphics graphics){
        switch (state_){
            case Empty:
                graphics.drawRectangle((int)position_.x, (int)position_.y,1);
                break;
            case Marked:
                graphics.drawRectangle((int)position_.x, (int)position_.y,1);
                break;
            case Correct:
                graphics.drawRectangle((int)position_.x, (int)position_.y,1);
                break;
            case Wrong:
                graphics.drawRectangle((int)position_.x, (int)position_.y,1);
                break;
        }
    }
    public void handleInput(TouchEvent event){
       if(event.getType()== EventType.CLICKED ){
            if(state_==State.Empty){
                state_=State.Marked;
            }
            else if(state_==State.Marked){
                state_=State.Empty;
            }
       }
    }
}