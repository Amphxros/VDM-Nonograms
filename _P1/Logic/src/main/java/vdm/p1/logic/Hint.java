package vdm.p1.logic;


import java.util.Random;

public class Hint {
    private boolean [] mSolutions;

    Random rnd;

    public Hint(int n){
        rnd= new Random();
        mSolutions= new boolean[n];

        for(int i=0;i<n;i++){
            mSolutions[i]= rnd.nextBoolean();

        }
    }

    public boolean[] getSolutionOnRow(){
        return mSolutions;
    }

}
