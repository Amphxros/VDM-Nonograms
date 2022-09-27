package es.ucm.vdm.logic;

public class Cell extends GameObject{ // elements of the table
    State state_;
    State solutionState_;

    public Cell(){
        state_ =State.Empty;
    }

    public Cell(State state){
        state_ = state;
    }

    State getState() { return state_; }
    void setState(State state){
        state_ = state;
    }
}