package es.ucm.vdm.logic;

class Cell extends GameObject{ // elements of the table
    State mState_;
    State solutionState;

    public Cell(){
        mState_=State.Empty;
    }

    public Cell(State state){
        mState_= state;
    }

    void changeState(State state){
        mState_= state;
    }


}