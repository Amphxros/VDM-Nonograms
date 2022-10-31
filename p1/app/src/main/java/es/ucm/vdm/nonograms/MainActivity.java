package es.ucm.vdm.nonograms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import es.ucm.vdm.aengine.AndroidEngine;
import es.ucm.vdm.logic.NonogramLogic;



public class MainActivity extends AppCompatActivity {
    private AndroidEngine mEngine_;
    private NonogramLogic mLogic_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEngine_= new AndroidEngine(this);
        mLogic_= new NonogramLogic(mEngine_);
        mEngine_.setLogic(mLogic_);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}