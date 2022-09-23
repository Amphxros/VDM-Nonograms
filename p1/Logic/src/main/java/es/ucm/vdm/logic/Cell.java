

enum State {Empty, Marked, Correct, Wrong } //states of the cells

class Cell extends GameObject{ // elements of the table
    State mState_;

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