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

    State getCurrentState() { return state_; }
    void setCurrentState(State state){
        state_ = state;
    }

    State getSolutionState(){return solutionState_;}
    void setSolutionState(State state){solutionState_=state;}

    boolean isCellCorrect(){return state_==solutionState_;}
}