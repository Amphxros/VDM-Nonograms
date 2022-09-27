package es.ucm.vdm.logic;

import java.util.ArrayList;

public class Table extends GameObject{
    State[][] levelInfo;
    Cell[][] mCells_;
    ArrayList<Integer>[] hints;
    public Table(int n){
        this.Table(n,n);
    }

    public Table(int n, int m) {

    }
}
