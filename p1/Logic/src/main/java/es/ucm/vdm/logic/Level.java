package es.ucm.vdm.logic;

import es.ucm.vdm.engine.Graphics;

/*
* Representation of a level of the game with a table fils x cols
*/
public final class Level extends Scene{

    Table table;

    int nRows_;
    int mCols_;

    public Level(Graphics graphics,int rows, int cols){
        super(graphics);
        this.nRows_=rows;
        this.mCols_=cols;

        table= new Table(rows,cols);

    }

    public void checkLevel(){

    }

    //updates all the GO in this Scene with the time since the last update
    void update(double t){

    }

    //render the GO in their positions.
    // Receives an instance of graphics to call their own Graphics handler (Different in android and PC)
    void render(Graphics graphics){

    }

    //handle the input
    void handleInput(){

    }
}